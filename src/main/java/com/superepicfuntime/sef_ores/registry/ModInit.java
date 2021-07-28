package com.superepicfuntime.sef_ores.registry;

import com.superepicfuntime.sef_ores.items.ConversionManual;
import com.superepicfuntime.sef_ores.items.Material;
import com.superepicfuntime.sef_ores.util.RecipeBuilder;
import java.util.AbstractMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.superepicfuntime.sef_ores.SEFOres;
import com.google.gson.*;

// Item/Block imports
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.Item.Settings;
// Worldgen imports
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.heightprovider.*;
import net.minecraft.world.gen.*;
import net.minecraft.util.registry.*;

// ItemGroup imports
import net.minecraft.util.*;

public class ModInit {
	public static final String MOD_ID = "sef_ores";

	public static final ConversionManual CONVERSION_MANUAL = new ConversionManual(new Settings().group(Group.SEF_ORES).maxCount(1));

	public static final Item IRON_DUST = new Item(new Settings().group(Group.SEF_ORES));
	public static final Item GOLD_DUST = new Item(new Settings().group(Group.SEF_ORES));
	public static final Item COPPER_DUST = new Item(new Settings().group(Group.SEF_ORES));
	public static final Item COPPER_NUGGET = new Item(new Settings().group(Group.SEF_ORES));

	public static final Map<String, Material> MATERIALS = Map.ofEntries(
	//													|name					|name			|hardness	|pick	|has ores
	//	new AbstractMap.SimpleEntry<String, Material>(	"", 		new Material("", 			0.0f, 		0, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"aluminum", new Material("aluminum", 	2.5f, 		1, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"brass", 	new Material("brass", 		2.8f, 		2, 		false)),
		new AbstractMap.SimpleEntry<String, Material>(	"bronze", 	new Material("bronze", 		4.0f, 		2, 		false)),
		new AbstractMap.SimpleEntry<String, Material>(	"chromium", new Material("chromium", 	4.0f, 		2, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"electrum", new Material("electrum", 	4.0f, 		2, 		false)),
		new AbstractMap.SimpleEntry<String, Material>(	"invar", 	new Material("invar", 		6.0f, 		2, 		false)),
		new AbstractMap.SimpleEntry<String, Material>(	"iridium", 	new Material("iridium", 	12.0f, 		3, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"lead", 	new Material("lead", 		6.0f, 		2, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"nickel", 	new Material("nickel", 		4.0f, 		2, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"platinum", new Material("platinum", 	7.0f, 		3, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"silver", 	new Material("silver", 		5.0f, 		2, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"steel", 	new Material("steel", 		5.0f, 		2, 		false)),
		new AbstractMap.SimpleEntry<String, Material>(	"tin", 		new Material("tin", 		2.0f, 		1, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"titanium", new Material("titanium", 	10.0f, 		3, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"tungsten", new Material("tungsten", 	9.0f, 		3, 		true)),
		new AbstractMap.SimpleEntry<String, Material>(	"zinc", 	new Material("zinc", 		2.0f, 		2, 		true))
	);

	public static final ArrayList<JsonObject> RECIPES = new ArrayList<JsonObject>();

	// == Worldgen ==
	// Aluminum
	private static ConfiguredFeature<?, ?> ORE_ALUMINUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("aluminum").ORE.getDefaultState(), SEFOres.CONFIG.aluminum_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.aluminum_min_y), YOffset.fixed(SEFOres.CONFIG.aluminum_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.aluminum_vein_frequency);
	// Iridium
	private static ConfiguredFeature<?, ?> ORE_IRIDIUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("iridium").ORE.getDefaultState(), SEFOres.CONFIG.iridium_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.iridium_min_y), YOffset.fixed(SEFOres.CONFIG.iridium_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.iridium_vein_frequency);
	// Nickel
	private static ConfiguredFeature<?, ?> ORE_NICKEL_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("nickel").ORE.getDefaultState(), SEFOres.CONFIG.nickel_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.nickel_min_y), YOffset.fixed(SEFOres.CONFIG.nickel_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.nickel_vein_frequency);
	// Platinum
	private static ConfiguredFeature<?, ?> ORE_PLATINUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("platinum").ORE.getDefaultState(), SEFOres.CONFIG.platinum_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.platinum_min_y), YOffset.fixed(SEFOres.CONFIG.platinum_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.platinum_vein_frequency);
	// Lead
	private static ConfiguredFeature<?, ?> ORE_LEAD_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("lead").ORE.getDefaultState(), SEFOres.CONFIG.lead_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.lead_min_y), YOffset.fixed(SEFOres.CONFIG.lead_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.lead_vein_frequency);
	// Silver
	private static ConfiguredFeature<?, ?> ORE_SILVER_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("silver").ORE.getDefaultState(), SEFOres.CONFIG.silver_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.silver_min_y), YOffset.fixed(SEFOres.CONFIG.silver_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.silver_vein_frequency);
	// Tin
	private static ConfiguredFeature<?, ?> ORE_TIN_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("tin").ORE.getDefaultState(), SEFOres.CONFIG.tin_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.tin_min_y), YOffset.fixed(SEFOres.CONFIG.tin_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.tin_vein_frequency);
	// Chromium
	private static ConfiguredFeature<?, ?> ORE_CHROMIUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("chromium").ORE.getDefaultState(), SEFOres.CONFIG.chromium_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.chromium_min_y), YOffset.fixed(SEFOres.CONFIG.chromium_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.chromium_vein_frequency);
	// Zinc
	private static ConfiguredFeature<?, ?> ORE_ZINC_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("zinc").ORE.getDefaultState(), SEFOres.CONFIG.zinc_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.zinc_min_y), YOffset.fixed(SEFOres.CONFIG.zinc_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.zinc_vein_frequency);
	// Titanium
	private static ConfiguredFeature<?, ?> ORE_TITANIUM_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("titanium").ORE.getDefaultState(), SEFOres.CONFIG.titanium_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.titanium_min_y), YOffset.fixed(SEFOres.CONFIG.titanium_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.titanium_vein_frequency);
	// Tungsten
	private static ConfiguredFeature<?, ?> ORE_TUNGSTEN_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("tungsten").ORE.getDefaultState(), SEFOres.CONFIG.tungsten_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.tungsten_min_y), YOffset.fixed(SEFOres.CONFIG.tungsten_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.tungsten_vein_frequency);

	// == Register functions ==
	// Register template
	public static <T> void register(Registry<T> type, String name, T element) {
		Registry.register(type, new Identifier(MOD_ID, name), element);
	}

	// Register item
	public static void item(String name, Item item) {
		register(Registry.ITEM, name, item);
	}

	// Register block
	public static void block(String name, Block block) {
		register(Registry.BLOCK, name, block);
	}

	// Register block as item
	public static void blockItem(String name, Block item, ItemGroup group) {
		item(name, new BlockItem(item, new Item.Settings().group(group)));
	}

	// == Init functions ==
	// Iterate through and register all ores defined above in MATERIALS
	public static void initOres() {
		for (Map.Entry<String, Material> m : MATERIALS.entrySet()) {
			m.getValue().register();
		}
	}

	@SuppressWarnings("deprecation")
	public static void initWorldGen() {
		if (SEFOres.CONFIG.aluminum) {
			RegistryKey<ConfiguredFeature<?, ?>> oreAluminumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_aluminum_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreAluminumOverworld.getValue(), ORE_ALUMINUM_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreAluminumOverworld);
		}
		if (SEFOres.CONFIG.chromium) {
			RegistryKey<ConfiguredFeature<?, ?>> oreChromiumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_chromium_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreChromiumOverworld.getValue(), ORE_CHROMIUM_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreChromiumOverworld);
		}
		if (SEFOres.CONFIG.lead) {
			RegistryKey<ConfiguredFeature<?, ?>> oreLeadOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_lead_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreLeadOverworld.getValue(), ORE_LEAD_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreLeadOverworld);
		}
		if (SEFOres.CONFIG.platinum) {
			RegistryKey<ConfiguredFeature<?, ?>> orePlatinumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_platinum_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, orePlatinumOverworld.getValue(), ORE_PLATINUM_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, orePlatinumOverworld);
		}
		if (SEFOres.CONFIG.iridium) {
			RegistryKey<ConfiguredFeature<?, ?>> oreIridiumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_iridium_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreIridiumOverworld.getValue(), ORE_IRIDIUM_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreIridiumOverworld);
		}
		if (SEFOres.CONFIG.nickel) {
			RegistryKey<ConfiguredFeature<?, ?>> oreNickelOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_nickel_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreNickelOverworld.getValue(), ORE_NICKEL_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreNickelOverworld);
		}
		if (SEFOres.CONFIG.titanium) {
			RegistryKey<ConfiguredFeature<?, ?>> oreTitaniumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_titanium_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTitaniumOverworld.getValue(), ORE_TITANIUM_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTitaniumOverworld);
		}
		if (SEFOres.CONFIG.tungsten) {
			RegistryKey<ConfiguredFeature<?, ?>> oreTungstenOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_tungsten_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTungstenOverworld.getValue(), ORE_TUNGSTEN_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTungstenOverworld);
		}
		if (SEFOres.CONFIG.silver) {
			RegistryKey<ConfiguredFeature<?, ?>> oreSilverOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_silver_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreSilverOverworld.getValue(), ORE_SILVER_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreSilverOverworld);
		}
		if (SEFOres.CONFIG.tin) {
			RegistryKey<ConfiguredFeature<?, ?>> oreTinOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_tin_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTinOverworld.getValue(), ORE_TIN_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTinOverworld);
		}
		if (SEFOres.CONFIG.zinc) {
			RegistryKey<ConfiguredFeature<?, ?>> oreZincOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
			new Identifier(MOD_ID, "ore_zinc_overworld"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreZincOverworld.getValue(), ORE_ZINC_OVERWORLD);
			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreZincOverworld);
		}
	}

	public void initVanilla() {

	}

	public static void init() {
		initOres();
		initWorldGen();
		
		item("iron_dust", IRON_DUST);
		item("gold_dust", GOLD_DUST);
		item("copper_dust", COPPER_DUST);
		item("copper_nugget", COPPER_NUGGET);

		RECIPES.add(RecipeBuilder.createShaped(new ArrayList<Character>(Arrays.asList('X')), new ArrayList<Identifier>(Arrays.asList(new Identifier("c", "copper_nuggets"))), new ArrayList<String>(Arrays.asList("tag")), new ArrayList<String>(Arrays.asList("XXX", "XXX", "XXX")), new Identifier(ModInit.MOD_ID+":copper_ingot"), 1));
		RECIPES.add(RecipeBuilder.createShapeless(new ArrayList<Identifier>(Arrays.asList(new Identifier("c", "copper_ingot"))), new ArrayList<String>(Arrays.asList("tag")), new Identifier(ModInit.MOD_ID+":copper_nugget"), 9));

		if (SEFOres.CONFIG.conversion_manual) {
			item("conversion_manual", CONVERSION_MANUAL);

			ArrayList<Identifier> items = new ArrayList<Identifier>() {{ add(new Identifier("minecraft:book")); add(new Identifier("minecraft:iron_ingot")); }};
			ArrayList<String> type = new ArrayList<String>() {{ add("item"); add("item"); }};
			RECIPES.add(RecipeBuilder.createShapeless(items, type, new Identifier("sef_ores:conversion_manual"), 1));
		}
	}

}
