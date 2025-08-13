package com.keyoverlay;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class keyoverlayoverlay extends Overlay
{
    private final keyoverlayplugin plugin;
    private final keyoverlayconfig config;

    private static final int SHADOW_OFFSET = 2;
    private static final Color SHADOW_COLOR = new Color(0, 0, 0, 150);

    @Inject
    public keyoverlayoverlay(keyoverlayplugin plugin, keyoverlayconfig config)
    {
        this.plugin = plugin;
        this.config = config;

        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setMovable(true);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        // Get keys list from config and split by comma
        String[] keys = config.keysToDisplay().split(",");

        String lastKey = plugin.getLastKeyText();  // You need to add this method returning String in your plugin

        graphics.setFont(new Font("Arial", Font.BOLD, config.fontSize()));

        int x = 10;
        int y = 40;

        for (String key : keys)
        {
            key = key.trim(); // Clean whitespace

            // Draw shadow
            graphics.setColor(SHADOW_COLOR);
            graphics.drawString(key, x + SHADOW_OFFSET, y + SHADOW_OFFSET);

            // Draw main text, highlight if it matches last pressed key
            if (key.equalsIgnoreCase(lastKey))
            {
                graphics.setColor(config.highlightColor());
            }
            else
            {
                graphics.setColor(Color.WHITE);
            }

            graphics.drawString(key, x, y);

            x += config.spacing();
        }

        return new Dimension(config.spacing() * keys.length + 20, y + config.fontSize());
    }

    public keyoverlayconfig getConfig()
    {
        return config;
    }
}
