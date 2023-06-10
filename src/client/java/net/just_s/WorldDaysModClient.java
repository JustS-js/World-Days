package net.just_s;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldDaysModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("world-days");
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static Config CONFIG;

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::renderText);
        CONFIG = new Config();
        CONFIG.load();
    }

    private static int rgb2argb(int rgb, int alpha) {
        int red = rgb & 255;
        int green = (rgb >> 8) & 255;
        int blue = (rgb >> 16) & 255;
        return (((((alpha << 8) + red) << 8) + green) << 8) + blue;
    }

    private void renderText(DrawContext context, float tickDelta) {
        if (MC.options.debugEnabled || !CONFIG.enable) return;

        // Calculate current day in the world
        long day = MC.world.getTimeOfDay() / 24000L;
        if (!CONFIG.startFromZero) {
            day += 1;
        }

        // Generate style
        Style style = Style.EMPTY
                .withBold(CONFIG.bold)
                .withItalic(CONFIG.italic)
                .withObfuscated(CONFIG.obfuscated)
                .withStrikethrough(CONFIG.strikethrough)
                .withUnderline(CONFIG.underline);

        if (CONFIG.fontIdentifier.contains(":") && Identifier.isValid(CONFIG.fontIdentifier)) {
            style = style.withFont(new Identifier(CONFIG.fontIdentifier));
        }

        // Figure out what to render
        Text text;
        if (CONFIG.shouldUseCustomText) {
            text = Text.literal(String.format(CONFIG.customText, day)).setStyle(style);
        } else {
            text = Text.translatable("hud.world-days.day", day).setStyle(style);
        }

        // Create "layer" for text rendering
        context.getMatrices().push();

        // Move said layer to Text coordinates
        context.getMatrices().translate(CONFIG.hudX, CONFIG.hudY, 0);

        // scale layer to Font Size
        context.getMatrices().scale(CONFIG.fontSize, CONFIG.fontSize, 0.0F);

        // Render Shadow first if needed
        if (CONFIG.shouldDrawShadow) {
            MC.textRenderer.draw(
                    text,
                    CONFIG.shadowRelativeX, CONFIG.shadowRelativeY,
                    rgb2argb(
                            Integer.parseInt(CONFIG.shadowColor.substring(1), 16),
                            CONFIG.alpha
                    ), false,
                    context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(),
                    TextRenderer.TextLayerType.NORMAL, 0, 15728880
            );
        }

        // Render text
        MC.textRenderer.draw(
                text,
                0, 0,
                rgb2argb(
                        Integer.parseInt(CONFIG.hudColor.substring(1), 16),
                        CONFIG.alpha
                ), false,
                context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(),
                TextRenderer.TextLayerType.NORMAL, 0, 15728880
        );

        // Revert all settings, so other can render their things
        context.getMatrices().pop();
    }
}