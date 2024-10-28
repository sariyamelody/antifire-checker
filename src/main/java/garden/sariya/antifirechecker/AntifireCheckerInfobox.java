package garden.sariya.antifirechecker;

import lombok.Getter;
import lombok.Setter;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import java.awt.*;

public class AntifireCheckerInfobox extends InfoBox
{

    @Getter
    @Setter
    private int timer = -1;
    private int threshold = 25;
    @Setter
    private String debugInfo = "";

    public AntifireCheckerInfobox(Plugin plugin)
    {
        super(null, plugin);
    }

    @Override
    public String getText()
    {
        // count 6 -> 1 instead of 5 -> 0
        return Integer.toString(timer + 1);
    }

    public void setThreshold(int t)
    {
        threshold = t;
    }

    @Override
    public Color getTextColor()
    {
        return timer < threshold ? Color.RED : Color.WHITE;
    }

    @Override
    public String getTooltip()
    {

        return String.format("remaining ticks of antifire effect (will start warning below %d)", threshold);
    }
}