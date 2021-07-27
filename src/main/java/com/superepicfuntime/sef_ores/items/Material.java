package com.superepicfuntime.sef_ores.items;

import com.superepicfuntime.sef_ores.SEFOres;
import com.superepicfuntime.sef_ores.registry.ModInit;
import com.superepicfuntime.sef_ores.registry.Group;
import com.superepicfuntime.sef_ores.util.RecipeBuilder;

import java.util.ArrayList;
import com.google.gson.JsonObject;

// Item/Block imports
import net.minecraft.util.Identifier;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.tool.attribute.v1.*;

// Extra items can be added to generate as well
public class Material {
	private String id = "";
	private float hardness = 1f;
	private int pickRequired = 1;
	private boolean hasOre = true;
	public Block ORE;
	public Item RAW;
	public Item INGOT;
	public Item NUGGET;
	public Block BLOCK;
	public String TAG;

	public Material(String id, float hardness, int pickRequired) {
		this.id = id;
		this.hardness = hardness;
		this.pickRequired = pickRequired;
		this.hasOre = true;
		init();
	}

	public Material(String id, float hardness, int pickRequired, boolean hasOre) {
		this.id = id;
		this.hardness = hardness;
		this.pickRequired = pickRequired;
		this.hasOre = hasOre;
		init();
	}

	private void init() {
		ORE = new Block(FabricBlockSettings.of(net.minecraft.block.Material.METAL).strength(hardness).requiresTool().breakByTool(FabricToolTags.PICKAXES, pickRequired));
		RAW = new Item(new FabricItemSettings().group(Group.SEF_ORES));
		INGOT = new Item(new FabricItemSettings().group(Group.SEF_ORES));
		NUGGET = new Item(new FabricItemSettings().group(Group.SEF_ORES));
		BLOCK = new Block(FabricBlockSettings.of(net.minecraft.block.Material.METAL).strength(hardness).requiresTool().breakByTool(FabricToolTags.PICKAXES, pickRequired));
	}

	private JsonObject nuggetToIngot() {
		ArrayList<Character> keys = new ArrayList<Character>();
		ArrayList<Identifier> items = new ArrayList<Identifier>();
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<String> pattern = new ArrayList<String>();
		
		keys.add('X');
		items.add(new Identifier("c", id+"_nuggets"));
		type.add("tag");
		pattern.add("XXX");
		pattern.add("XXX");
		pattern.add("XXX");

		return RecipeBuilder.createShaped(keys, items, type, pattern, new Identifier(ModInit.MOD_ID+":"+id+"_ingot"), 1);
	}

	private JsonObject ingotToBlock() {
		ArrayList<Character> keys = new ArrayList<Character>();
		ArrayList<Identifier> items = new ArrayList<Identifier>();
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<String> pattern = new ArrayList<String>();
		
		keys.add('X');
		items.add(new Identifier("c", id+"_ingots"));
		type.add("tag");
		pattern.add("XXX");
		pattern.add("XXX");
		pattern.add("XXX");

		return RecipeBuilder.createShaped(keys, items, type, pattern, new Identifier(ModInit.MOD_ID+":"+id+"_block"), 1);
	}

	private JsonObject blockToIngot() {
		ArrayList<Identifier> items = new ArrayList<Identifier>();
		ArrayList<String> type = new ArrayList<String>();
		items.add(new Identifier("c", id+"_blocks"));
		type.add("tag");

		return RecipeBuilder.createShapeless(items, type, new Identifier(ModInit.MOD_ID+":"+id+"_ingot"), 9);
	}

	private JsonObject ingotToNugget() {
		ArrayList<Identifier> items = new ArrayList<Identifier>();
		ArrayList<String> type = new ArrayList<String>();
		items.add(new Identifier("c", id+"_ingots"));
		type.add("tag");

		return RecipeBuilder.createShapeless(items, type, new Identifier(ModInit.MOD_ID+":"+id+"_nugget"), 9);
	}

	private JsonObject oreToIngotSmelting() {
		return RecipeBuilder.createSmelting(new Identifier("c", id+"_ores"), "tag", new Identifier(ModInit.MOD_ID+":"+id+"_ingot"));
	}

	private JsonObject oreToIngotBlasting() {
		return RecipeBuilder.createBlasting(new Identifier("c", id+"_ores"), "tag", new Identifier(ModInit.MOD_ID+":"+id+"_ingot"));
	}

	private JsonObject rawToIngotSmelting() {
		return RecipeBuilder.createSmelting(new Identifier("c", "raw_"+id+"_ores"), "tag", new Identifier(ModInit.MOD_ID+":"+id+"_ingot"));
	}

	private JsonObject rawToIngotBlasting() {
		return RecipeBuilder.createBlasting(new Identifier("c", "raw_"+id+"_ores"), "tag", new Identifier(ModInit.MOD_ID+":"+id+"_ingot"));
	}

	private JsonObject oreConversion() {
		return RecipeBuilder.createConversion(new Identifier("c", id+"_ores"), new Identifier("sef_ores", "conversion_manual"), new Identifier(ModInit.MOD_ID+":"+id+"_ore"));
	}

	private JsonObject rawConversion() {
		return RecipeBuilder.createConversion(new Identifier("c", "raw_"+id+"_ores"), new Identifier("sef_ores", "conversion_manual"), new Identifier(ModInit.MOD_ID+":raw_"+id));
	}

	private JsonObject ingotConversion() {
		return RecipeBuilder.createConversion(new Identifier("c", id+"_ingots"), new Identifier("sef_ores", "conversion_manual"), new Identifier(ModInit.MOD_ID+":"+id+"_ingot"));
	}

	private JsonObject blockConversion() {
		return RecipeBuilder.createConversion(new Identifier("c", id+"_blocks"), new Identifier("sef_ores", "conversion_manual"), new Identifier(ModInit.MOD_ID+":"+id+"_block"));
	}

	private JsonObject blockAltConversion() {
		return RecipeBuilder.createConversion(new Identifier("c", id+"_storage_blocks"), new Identifier("sef_ores", "conversion_manual"), new Identifier(ModInit.MOD_ID+":"+id+"_block"));
	}

	private JsonObject nuggetConversion() {
		return RecipeBuilder.createConversion(new Identifier("c", id+"_nuggets"), new Identifier("sef_ores", "conversion_manual"), new Identifier(ModInit.MOD_ID+":"+id+"_nugget"));
	}


	public ArrayList<JsonObject> recipes() {
		ArrayList<JsonObject> list = new ArrayList<JsonObject>();
		list.add(nuggetToIngot());
		list.add(ingotToBlock());
		list.add(blockToIngot());
		list.add(ingotToNugget());
		if (hasOre) {
			list.add(oreToIngotSmelting());
			list.add(oreToIngotBlasting());
			list.add(rawToIngotSmelting());
			list.add(rawToIngotBlasting());
		}
		if (SEFOres.CONFIG.conversion_manual) {
			if (hasOre) {
				list.add(oreConversion());
				list.add(rawConversion());
			}
			list.add(ingotConversion());
			list.add(blockConversion());
			list.add(blockAltConversion());
			list.add(nuggetConversion());
		}
		return list;
	}

	// Ensure all items that should get registered do here
	public void register() {
		if (hasOre) {
			ModInit.block(id+"_ore", ORE);
			ModInit.blockItem(id+"_ore", ORE, Group.SEF_ORES);
			ModInit.item("raw_"+id, RAW);
		}
		ModInit.item(id+"_ingot", INGOT);
		ModInit.item(id+"_nugget", NUGGET);
		ModInit.block(id+"_block", BLOCK);
		ModInit.blockItem(id+"_block", BLOCK, Group.SEF_ORES);
	}
}
