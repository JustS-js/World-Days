package net.just_s;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldDaysModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("world-days");
    public static final MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::renderText);
    }

    public static MutableText localize(String type, String id) {
        return Text.translatable(type + ".world-days." + id);
    }

    private void renderText(MatrixStack matrixStack, float v) {
        if (MC.options.debugEnabled || !Config.ENABLE) return;

        // Calculate current day in the world
        long day = MC.world.getTimeOfDay() / 24000L;
        if (!Config.START_FROM_ZERO) {
            day += 1;
        }

        // Generate style
        Style style = Style.EMPTY
                .withBold(Config.BOLD)
                .withItalic(Config.ITALIC)
                .withObfuscated(Config.OBFUSCATED)
                .withStrikethrough(Config.STRIKETHROUGH)
                .withUnderline(Config.UNDERLINE);

        // Figure out what to render
        Text text;
        if (Config.SHOULD_USE_CUSTOM_TEXT) {
            text = Text.literal(String.format(Config.CUSTOM_TEXT, day)).setStyle(style);
        } else {
            text = Text.translatable("hud.world-days.day", day).setStyle(style);
        }

        // Render Shadow first if needed
        if (Config.SHOULD_DRAW_SHADOW) {
            MC.textRenderer.draw(
                    matrixStack, text,
                    Config.HUD_X + Config.SHADOW_RELATIVE_X,
                    Config.HUD_Y + Config.SHADOW_RELATIVE_Y,
                    TextColor.parse(Config.SHADOW_COLOR).getRgb()
            );
        }

        // Render text
        MC.textRenderer.draw(
                matrixStack, text,
                Config.HUD_X, Config.HUD_Y,
                TextColor.parse(Config.HUD_COLOR).getRgb()
        );
    }
}