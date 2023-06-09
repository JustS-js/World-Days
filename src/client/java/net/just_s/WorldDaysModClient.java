package net.just_s;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
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

    private void renderText(MatrixStack matrices, float v) {
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

        // Font Size
        matrices.push();
        matrices.translate(CONFIG.hudX, CONFIG.hudY, 0);
        matrices.scale(CONFIG.fontSize, CONFIG.fontSize, 0.0F);

        // Render Shadow first if needed
        if (CONFIG.shouldDrawShadow) {
            MC.textRenderer.draw(
                    matrices, text,
                    CONFIG.shadowRelativeX,
                    CONFIG.shadowRelativeY,
                    TextColor.parse(CONFIG.shadowColor).getRgb()
            );
        }

        // Render text
        MC.textRenderer.draw(
                matrices, text,
                0, 0,
                TextColor.parse(CONFIG.hudColor).getRgb()
        );
        matrices.pop();
    }
}