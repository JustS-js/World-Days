package net.just_s;

import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import com.google.gson.Gson;

import java.io.*;

public class Config {
    public boolean enable;
    public boolean startFromZero;
    public boolean shouldUseCustomText;
    public String customText;

    public boolean bold;
    public boolean italic;
    public boolean obfuscated;
    public boolean strikethrough;
    public boolean underline;
    public float fontSize;
    public String fontIdentifier;

    public float hudX;
    public float hudY;
    public String hudColor;

    public boolean shouldDrawShadow;
    public float shadowRelativeX;
    public float shadowRelativeY;
    public String shadowColor;

    public Config() {}

    public static Screen generateScreen(Screen parent) {
        return null;
    }

    public void save() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(this);

        try {
            FileWriter writer = new FileWriter(
                    FabricLoader.getInstance().getConfigDir().resolve("WorldDays.json").toFile()
            );
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            WorldDaysModClient.LOGGER.error("Error while saving:" + e.getMessage());
        }
    }

    public void load() {
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(
                            FabricLoader.getInstance().getConfigDir().resolve("WorldDays.json").toFile()
                    )
            );

            Gson gson = new Gson();
            Config cfg = gson.fromJson(bufferedReader, Config.class);
            this.enable = cfg.enable;
            this.startFromZero = cfg.startFromZero;
            this.shouldUseCustomText = cfg.shouldUseCustomText;
            this.customText = cfg.customText;

            this.bold = cfg.bold;
            this.italic = cfg.italic;
            this.obfuscated = cfg.obfuscated;
            this.strikethrough = cfg.strikethrough;
            this.underline = cfg.underline;
            this.fontSize = cfg.fontSize;
            this.fontIdentifier = cfg.fontIdentifier;

            this.hudX = cfg.hudX;
            this.hudY = cfg.hudY;
            this.hudColor = cfg.hudColor;

            this.shouldDrawShadow = cfg.shouldDrawShadow;
            this.shadowRelativeX = cfg.shadowRelativeX;
            this.shadowRelativeY = cfg.shadowRelativeY;
            this.shadowColor = cfg.shadowColor;
        } catch (FileNotFoundException e) {
            this.enable = true;
            this.startFromZero = false;
            this.shouldUseCustomText = false;
            this.customText = "Custom Text %d";

            this.bold = true;
            this.italic = false;
            this.obfuscated = false;
            this.strikethrough = false;
            this.underline = false;
            this.fontSize = 1f;
            this.fontIdentifier = "minecraft:default";

            this.hudX = 10f;
            this.hudY = 10f;
            this.hudColor = "#FFFFFF";

            this.shouldDrawShadow = true;
            this.shadowRelativeX = 1f;
            this.shadowRelativeY = 1f;
            this.shadowColor = "#000000";
        }
    }
}
