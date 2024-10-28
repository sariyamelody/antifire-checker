package garden.sariya.antifirechecker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AntifireCheckerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AntifireCheckerPlugin.class);
		RuneLite.main(args);
	}
}