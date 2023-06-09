package net.just_s.util;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.just_s.Config;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TextColor;

import static net.just_s.WorldDaysModClient.localize;

public class ClothConfigIntegration {
    public static Screen generateScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(localize("config", "title"))
                .setSavingRunnable(Config::save)
                .setTransparentBackground(true);
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.getOrCreateCategory(localize("config", "category.general"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "enable"),
                                Config.ENABLE
                        )
                        .setSaveConsumer(value -> Config.ENABLE = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "start-from-zero"),
                                Config.START_FROM_ZERO
                        )
                        .setSaveConsumer(value -> Config.START_FROM_ZERO = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "use-custom-text"),
                                Config.SHOULD_USE_CUSTOM_TEXT
                        )
                        .setSaveConsumer(value -> Config.SHOULD_USE_CUSTOM_TEXT = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startStrField(
                                localize("config", "custom-text"),
                                Config.CUSTOM_TEXT
                        )
                        .setDefaultValue("Custom Text %d")
                        .setSaveConsumer(value -> Config.CUSTOM_TEXT = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "shadow"),
                                Config.SHOULD_DRAW_SHADOW
                        )
                        .setSaveConsumer(value -> Config.SHOULD_DRAW_SHADOW = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "text.coords").append(" X"),
                                Config.HUD_X
                        )
                        .setSaveConsumer(value -> Config.HUD_X = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "text.coords").append(" Y"),
                                Config.HUD_Y
                        )
                        .setSaveConsumer(value -> Config.HUD_Y = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "shadow.coords").append(" X"),
                                Config.SHADOW_RELATIVE_X
                        )
                        .setSaveConsumer(value -> Config.SHADOW_RELATIVE_X = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startFloatField(
                                localize("config", "shadow.coords").append(" Y"),
                                Config.SHADOW_RELATIVE_Y
                        )
                        .setSaveConsumer(value -> Config.SHADOW_RELATIVE_Y = value)
                        .build()
                );

        builder.getOrCreateCategory(localize("config", "category.style"))
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "bold"),
                                Config.BOLD
                        )
                        .setSaveConsumer(value -> Config.BOLD = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "italic"),
                                Config.ITALIC
                        )
                        .setSaveConsumer(value -> Config.ITALIC = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "obfuscated"),
                                Config.OBFUSCATED
                        )
                        .setSaveConsumer(value -> Config.OBFUSCATED = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "strikethrough"),
                                Config.STRIKETHROUGH
                        )
                        .setSaveConsumer(value -> Config.STRIKETHROUGH = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startBooleanToggle(
                                localize("config", "underline"),
                                Config.UNDERLINE
                        )
                        .setSaveConsumer(value -> Config.UNDERLINE = value)
                        .build()
                )
                .addEntry(entryBuilder
                        .startColorField(
                                localize("config", "color.hud"),
                                TextColor.parse(Config.HUD_COLOR)
                        )
                        .setSaveConsumer(value -> Config.HUD_COLOR = TextColor.fromRgb(value).getHexCode())
                        .build()
                )
                .addEntry(entryBuilder
                        .startColorField(
                                localize("config", "color.shadow"),
                                TextColor.parse(Config.SHADOW_COLOR)
                        )
                        .setSaveConsumer(value -> Config.SHADOW_COLOR = TextColor.fromRgb(value).getHexCode())
                        .build()
                );

        return builder.build();
    }
}
