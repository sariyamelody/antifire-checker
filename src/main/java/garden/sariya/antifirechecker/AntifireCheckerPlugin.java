package garden.sariya.antifirechecker;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.Notifier;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import java.util.Arrays;

@Slf4j
@PluginDescriptor(
	name = "Antifire Checker"
)
public class AntifireCheckerPlugin extends Plugin
{
	private static final int NO_ANTIFIRE = -1;

	@Inject
	private Client client;

	@Inject
	private AntifireCheckerConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private OverlayManager overlayManager;

	private AntifireCheckerInfobox infoBox;

	@Inject
	private Notifier notifier;

	@Inject
	private AntifireCheckerOverlay overlay;

	private int totalAntifireTicksLeft = NO_ANTIFIRE;
	// -1 = none
	//  1 = antifire (varbit value * 30 game ticks)
	//  2 = super antifire (varbit value * 20 game ticks)
	// source for above: https://github.com/runelite/runelite/blob/6b6f796bfdfdf7572d8d29fcfa5028da580d960e/runelite-api/src/main/java/net/runelite/api/Varbits.java#L57C2-L70C1
	private int antifireType = -1;


	private static final int[] AntifireEvents = new int[]{
			Varbits.ANTIFIRE,
			Varbits.SUPER_ANTIFIRE
	};

	@Override
	protected void startUp() throws Exception
	{
		this.infoBox = new AntifireCheckerInfobox(this);
		this.infoBoxManager.addInfoBox(this.infoBox);

		this.totalAntifireTicksLeft = NO_ANTIFIRE;

		this.infoBox.setImage(itemManager.getImage(ItemID.BOTTLED_DRAGONBREATH));
	}

	@Override
	protected void shutDown() throws Exception
	{
		this.infoBoxManager.removeInfoBox(this.infoBox);
		this.overlayManager.remove(this.overlay);
	}

	@Subscribe
	protected void onGameTick(GameTick tick)
	{
		--this.totalAntifireTicksLeft;
		updateInfoBox();
	}

	@Subscribe
	protected void onVarbitChanged(VarbitChanged change)
	{
		log.info("new varbit change {}", change);
		if (Arrays.stream(AntifireEvents).anyMatch(changedVarbitId -> changedVarbitId == change.getVarbitId()))
		{
			log.info("found varbit change we care about, vid: {}, vval: {}", change.getVarbitId(), change.getValue());
			int value = change.getValue();
			if (change.getVarbitId() == Varbits.SUPER_ANTIFIRE) {
				this.antifireType = 2;
			} else {
				this.antifireType = 1;
			}
			if (value > 0) {
				this.totalAntifireTicksLeft = this.getAntifireTicksPerValue() * change.getValue();
			} else {
				this.totalAntifireTicksLeft = NO_ANTIFIRE;
				this.onAntifirePotionExpired();
			}
		}
	}


	@Subscribe
	protected void onConfigChanged(ConfigChanged event)
	{
		this.updateOverlay();
		this.updateInfoBox();
	}

	private boolean isAntifireActive()
	{
		return this.totalAntifireTicksLeft >= 0;
	}

	private void onAntifirePotionExpired()
	{
		this.updateOverlay();
		if (config.getNotification().isEnabled())
		{
			notifier.notify(config.getNotification(), "Your antifire potion has expired");
		}

	}

	private int getAntifireTicksPerValue()
	{
		switch (antifireType) {
			case -1: // nothing, fallthrough, doesn't matter
			case 1: // antifire
				return 30;
			case 2: // super antifire
				return 20;
            default:
                throw new IllegalStateException("Unexpected value: " + antifireType);
        }
	}

	private void updateOverlay()
	{
		if (!this.isAntifireActive()  || !config.reminderEnabled())
		{
			this.overlayManager.remove(this.overlay);
			return;
		}
		if (!this.overlayManager.anyMatch(o -> o instanceof AntifireCheckerOverlay))
		{
			this.overlayManager.add(this.overlay);
		}
	}

	private void updateInfoBox()
	{
		if (!this.isAntifireActive() || !config.timerEnabled())
		{
			this.infoBoxManager.removeInfoBox(this.infoBox);
			return;
		}
		this.infoBox.setTimer(this.totalAntifireTicksLeft);
		if (!this.infoBoxManager.getInfoBoxes().contains(this.infoBox))
		{
			this.infoBoxManager.addInfoBox(this.infoBox);
		}
	}

	@Provides
	AntifireCheckerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AntifireCheckerConfig.class);
	}
}