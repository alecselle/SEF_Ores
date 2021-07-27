package com.superepicfuntime.sef_ores.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "sef_ores")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class SEFOresConfig implements ConfigData {
	@ConfigEntry.Category("items")
	@Comment("Allows converting materials to SEF Ores versions")
	public boolean conversion_manual = true;

	@ConfigEntry.Category("worldgen")
	public boolean aluminum = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int aluminum_vein_size = 12;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int aluminum_vein_frequency = 10;
	@ConfigEntry.Category("worldgen")
	public int aluminum_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int aluminum_max_y = 80;

	@ConfigEntry.Category("worldgen")
	public boolean chromium = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int chromium_vein_size = 6;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int chromium_vein_frequency = 4;
	@ConfigEntry.Category("worldgen")
	public int chromium_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int chromium_max_y = 48;

	@ConfigEntry.Category("worldgen")
	public boolean lead = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int lead_vein_size = 6;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int lead_vein_frequency = 4;
	@ConfigEntry.Category("worldgen")
	public int lead_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int lead_max_y = 40;

	@ConfigEntry.Category("worldgen")
	public boolean nickel = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int nickel_vein_size = 8;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int nickel_vein_frequency = 5;
	@ConfigEntry.Category("worldgen")
	public int nickel_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int nickel_max_y = 24;

	@ConfigEntry.Category("worldgen")
	public boolean tin = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int tin_vein_size = 8;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int tin_vein_frequency = 8;
	@ConfigEntry.Category("worldgen")
	public int tin_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int tin_max_y = 96;

	@ConfigEntry.Category("worldgen")
	public boolean titanium = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int titanium_vein_size = 3;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int titanium_vein_frequency = 2;
	@ConfigEntry.Category("worldgen")
	public int titanium_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int titanium_max_y = 20;

	@ConfigEntry.Category("worldgen")
	public boolean zinc = true;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int zinc_vein_size = 12;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int zinc_vein_frequency = 10;
	@ConfigEntry.Category("worldgen")
	public int zinc_min_y = 0;
	@ConfigEntry.Category("worldgen")
	public int zinc_max_y = 70;

}