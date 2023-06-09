package net.just_s;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class Config {
    public static boolean ENABLE = true;
    public static boolean START_FROM_ZERO = false;
    public static boolean SHOULD_USE_CUSTOM_TEXT = false;
    public static String CUSTOM_TEXT = "Custom Text %d";

    public static boolean BOLD = true;
    public static boolean ITALIC = false;
    public static boolean OBFUSCATED = false;
    public static boolean STRIKETHROUGH = false;
    public static boolean UNDERLINE = false;

    public static float HUD_X = 10f;
    public static float HUD_Y = 10f;
    public static String HUD_COLOR = "#FFFFFF";

    public static boolean SHOULD_DRAW_SHADOW = true;
    public static float SHADOW_RELATIVE_X = 1f;
    public static float SHADOW_RELATIVE_Y = 1f;
    public static String SHADOW_COLOR = "#000000";

    public static Screen generateScreen(Screen parent) {
        return null;
    }

    public static void save() {

    }
}
