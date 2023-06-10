package net.just_s.util;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import static net.just_s.WorldDaysModClient.CONFIG;

public class ClothConfigIntegration {
    public static MutableText localize(String type, String id) {
        return Text.translatable(type + ".world-days." + id);
    }

    public static Screen generateScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(localize("config", "title"))
                .setSavingRunnable(CONFIG::save)
                .setTransparentBackground(true);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.getOrCreateCategory(localize("config", "category.general"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "enable"),
                                CONFIG.enable
                        )
                        .setSaveConsumer(value -> CONFIG.enable = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "start-from-zero"),
                                CONFIG.startFromZero
                        )
                        .setSaveConsumer(value -> CONFIG.startFromZero = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "use-custom-text"),
                                CONFIG.shouldUseCustomText
                        )
                        .setSaveConsumer(value -> CONFIG.shouldUseCustomText = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startStrField(
                                localize("config", "custom-text"),
                                CONFIG.customText
                        )
                        .setDefaultValue("Custom Text %d")
                        .setSaveConsumer(value -> CONFIG.customText = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "shadow"),
                                CONFIG.shouldDrawShadow
                        )
                        .setSaveConsumer(value -> CONFIG.shouldDrawShadow = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "text.coords").append(" X"),
                                CONFIG.hudX
                        )
                        .setSaveConsumer(value -> CONFIG.hudX = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "text.coords").append(" Y"),
                                CONFIG.hudY
                        )
                        .setSaveConsumer(value -> CONFIG.hudY = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "shadow.coords").append(" X"),
                                CONFIG.shadowRelativeX
                        )
                        .setSaveConsumer(value -> CONFIG.shadowRelativeX = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "shadow.coords").append(" Y"),
                                CONFIG.shadowRelativeY
                        )
                        .setSaveConsumer(value -> CONFIG.shadowRelativeY = value)
                        .build()
                );

        builder.getOrCreateCategory(localize("config", "category.style"))
                .addEntry(entryBuilder
                        .startStrField(
                                localize("config", "font.id"),
                                CONFIG.fontIdentifier
                        )
                        .setDefaultValue("minecraft:default")
                        .setSaveConsumer(value -> CONFIG.fontIdentifier = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "font.size"),
                                CONFIG.fontSize
                        )
                        .setSaveConsumer(value -> CONFIG.fontSize = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "bold"),
                                CONFIG.bold
                        )
                        .setSaveConsumer(value -> CONFIG.bold = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "italic"),
                                CONFIG.italic
                        )
                        .setSaveConsumer(value -> CONFIG.italic = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "obfuscated"),
                                CONFIG.obfuscated
                        )
                        .setSaveConsumer(value -> CONFIG.obfuscated = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "strikethrough"),
                                CONFIG.strikethrough
                        )
                        .setSaveConsumer(value -> CONFIG.strikethrough = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "underline"),
                                CONFIG.underline
                        )
                        .setSaveConsumer(value -> CONFIG.underline = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startColorField(
                                localize("config", "color.hud"),
                                TextColor.parse(CONFIG.hudColor)
                        )
                        .setSaveConsumer(value -> CONFIG.hudColor = TextColor.fromRgb(value).getHexCode())
                        .build()
                )
                .addEntry(entryBuilder
                        .startColorField(
                                localize("config", "color.shadow"),
                                TextColor.parse(CONFIG.shadowColor)
                        )
                        .setSaveConsumer(value -> CONFIG.shadowColor = TextColor.fromRgb(value).getHexCode())
                        .build()
                )
                .addEntry(entryBuilder
                        .startIntSlider(
                                localize("config", "alpha"),
                                CONFIG.alpha, 0, 255
                        )
                        .setSaveConsumer(value -> CONFIG.alpha = value)
                        .build()
                );

        return builder.build();
    }
}
