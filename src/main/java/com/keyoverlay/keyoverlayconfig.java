//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.keyoverlay;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("keyoverlay")
public interface keyoverlayconfig extends Config {
    @ConfigItem(
            keyName = "highlightColor",
            name = "Highlight Color",
            description = "Color for the last key pressed"
    )
    default Color highlightColor() {
        return new Color(16728642);
    }

    @ConfigItem(
            keyName = "spacing",
            name = "Key Spacing",
            description = "Horizontal spacing between each key (in pixels)"
    )
    @Range(
            min = 10,
            max = 100
    )
    default int spacing() {
        return 40;
    }

    @ConfigItem(
            keyName = "fontSize",
            name = "Font Size",
            description = "Size of the keys displayed"
    )
    @Range(
            min = 10,
            max = 60
    )
    default int fontSize() {
        return 20;
    }

    @ConfigItem(
            keyName = "keysToDisplay",
            name = "Keys to Display",
            description = "Comma-separated list of keys to show (e.g., W,A,S,D,1,2,3)"
    )
    default String keysToDisplay() {
        return "1,2,3,4,5,6";
    }
}
