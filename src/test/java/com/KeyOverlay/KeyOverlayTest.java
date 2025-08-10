package com.KeyOverlay;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class KeyOverlayTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(com.KeyOverlay.KeyOverlayPlugin.class);
        RuneLite.main(args);
    }
}