package com.KeyOverlay;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import java.util.stream.Collectors;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
@PluginDescriptor(
        name = "Key Overlay",
        description = "Displays configurable keys horizontally, last key pressed is highlighted",
        tags = {"keys", "overlay"}
)
public class KeyOverlayPlugin extends Plugin implements KeyListener
{
    @Inject private OverlayManager overlayManager;
    @Inject private KeyManager keyManager;
    @Inject private KeyOverlayOverlay overlay;

    @Inject private KeyOverlayConfig config;

    @Getter
    private String lastKeyText = "";

    @Provides
    KeyOverlayConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(KeyOverlayConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
        keyManager.registerKeyListener(this);
        log.info("Key Overlay plugin started");
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        keyManager.unregisterKeyListener(this);
        log.info("Key Overlay plugin stopped");
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        String pressedKey = KeyEvent.getKeyText(e.getKeyCode()).toUpperCase(Locale.ROOT);

        List<String> allowedKeys = Arrays.asList(config.keysToDisplay().split(",")).stream()
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        if (allowedKeys.contains(pressedKey))
        {
            lastKeyText = pressedKey;
            log.info("Last key updated to {}", lastKeyText);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
