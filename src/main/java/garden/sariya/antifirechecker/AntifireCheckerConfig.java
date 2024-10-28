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
}
