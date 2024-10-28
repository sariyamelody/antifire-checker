package garden.sariya.antifirechecker;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("antifirechecker")
public interface AntifireCheckerConfig extends Config
{
	@ConfigSection(name = "Notifications",
			description = "Configuration for notifications",
			position = 1)
	String notificationSection = "notificationSection";

	@ConfigSection(name = "Timers",
			description = "Configuration for timers",
			position = 2)
	String timerSection = "timerSection";

	@ConfigSection(name = "Location",
	description = "Configuration for location",
	position = 3)
	String locationSection = "locationSection";

	@ConfigSection(name = "Overlays",
			description = "Configuration for overlays",
			position = 4)
	String overlaySection = "overlaySection";

	@ConfigItem(
			keyName = "reminderEnabled",
			name = "Enable reminder panel",
			description = "Show an overlay reminding you to sip a Antifire Potion",
			position = 1,
			section = notificationSection
	)
	default boolean reminderEnabled()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
			keyName = "reminderColor",
			name = "Reminder Color",
			description = "The color to use for the infobox.",
			position = 2,
			section = notificationSection
	)
	default Color reminderColor()
	{
		// same color as antifire potion
		return new Color(121, 18, 157, 150);
	}

	@ConfigItem(
			keyName = "notificationEnabled",
			name = "Enable runelite notification",
			description = "Uses notification configured in Runelite settings when antifire effect is close to expiring",
			position = 3,
			section = notificationSection
	)
	default Notification getNotification()
	{
		return Notification.OFF;
	}

	@ConfigItem(
			keyName = "timerEnabled",
			name = "Enable antifire timer",
			description = "Show an infobox with a timer ticking down to the next aggression check",
			section = timerSection
	)
	default boolean timerEnabled()
	{
		return false;
	}

	@ConfigItem(
			keyName = "minimumRemainingAntifireTicks",
			name = "Minimum remaining antifire ticks before notifications begin",
			description = "When the number of antifire ticks remaining are below this number, Begin The Notifying.",
			section = timerSection
	)
	default int minimumRemainingAntifireTicks()
	{
		// It seems like "about 15 seconds" is a reasonable time to start screaming bloody murder.
		// Wouldn't want our users to get smoked because they were across the room.
		return 25;
	}

	@ConfigItem(
			keyName = "enableWarningOverlay",
			name = "Enable warning overlay",
			description = "Enable a big blinky flashing red overlay when you're almost out of antifire ticks.",
			section = overlaySection
	)
	default boolean enableWarningOverlay() { return true; }

	@ConfigItem(
			keyName = "enableTextOverlay",
			name = "Enable text overlay",
			description = "Enable a little textual overlay when you're almost out of antifire ticks.",
			section = overlaySection
	)
	default boolean enableTextOverlay() { return false; }


	@ConfigItem(
			keyName = "onlyDrawWarningWithDragonsAround",
			name = "Only draw warning/text overlays near dragons",
			description = "Only draw warning overlay when dragons requiring antifire potions are nearby.",
			section = overlaySection
	)
	default boolean onlyDrawWarningWithDragonsAround() { return true; }

	@Alpha
	@ConfigItem(
			keyName = "warningColor",
			name = "Warning Color",
			description = "The color to use for the warning overlay.",
			section = overlaySection
	)
	default Color warningColor()
	{
		// same color as antifire potion
		return new Color(121, 18, 157, 150);
	}


}
