package garden.sariya.antifirechecker;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.*;

public class AntifireCheckerWarningOverlay extends OverlayPanel
{

    private final AntifireCheckerConfig config;
    private final Client client;

    @Inject
    private AntifireCheckerWarningOverlay(AntifireCheckerConfig config, AntifireCheckerPlugin plugin, Client client)
    {
        super(plugin);
        this.config = config;
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        final String text = "You need to sip a Antifire Potion";

        Color color = graphics.getColor();
        graphics.setColor(config.warningColor());
        graphics.fill(new Rectangle(client.getCanvas().getSize()));
        graphics.setColor(color);
        return null;
    }

}