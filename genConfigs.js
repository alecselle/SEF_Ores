var fs = require('fs');
const ORES = ["aluminum", "chromium", "iridium", "lead", "nickel", "platinum", "silver", "tin", "titanium", "tungsten", "zinc"];

// order must match ORES
const DEFAULTS = [
    [12, 10, 0, 80], //aluminum
    [6, 6, 0, 48], //chromium
    [1, 1, 0, 16], //iridium
    [6, 4, 0, 30], //lead
    [8, 6, 0, 24], //nickel
    [3, 2, 0, 20], //platinum
    [8, 4, 0, 30], //silver
    [8, 8, 0, 96], //tin
    [3, 2, 0, 20], //titanium
    [4, 2, 0, 16], //tungsten
    [12, 8, 0, 70] //zinc
];

function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function genConfig(material, gen = true, size = 12, frequency = 10, min = 0, max = 80) {
    return `
	// ` + capitalize(material) + `
	@ConfigEntry.Category("worldgen")
	public boolean ` + material + ` = ` + gen + `;
	@ConfigEntry.Category("worldgen")
	@Comment("Ores per vein")
	public int ` + material + `_vein_size = ` + size + `;
	@ConfigEntry.Category("worldgen")
	@Comment("Veins per chunk")
	public int ` + material + `_vein_frequency = ` + frequency + `;
	@ConfigEntry.Category("worldgen")
	public int ` + material + `_min_y = ` + min + `;
	@ConfigEntry.Category("worldgen")
	public int ` + material + `_max_y = ` + max + `;`
}

function genConfigs() {
    ORES.forEach((e, i) => {
        fs.appendFile("./configs.txt", genConfig(e, true, DEFAULTS[i][0], DEFAULTS[i][1], DEFAULTS[i][2], DEFAULTS[i][3]), function(err) {
            if (err) throw err;
            console.log("Configs written");
        })
    });
}

function genFeature(material) {
    return `
	// ` + capitalize(material) + `
	private static ConfiguredFeature<?, ?> ORE_` + material.toUpperCase() + `_OVERWORLD = Feature.ORE
	.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, MATERIALS.get("` + material + `").ORE.getDefaultState(), SEFOres.CONFIG.` + material + `_vein_size))
	.range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(SEFOres.CONFIG.` + material + `_min_y), YOffset.fixed(SEFOres.CONFIG.` + material + `_max_y)))).spreadHorizontally().repeat(SEFOres.CONFIG.` + material + `_vein_frequency);`
}

function genFeatures() {
    ORES.forEach((e, i) => {
        fs.appendFile("./features.txt", genFeature(e), function(err) {
            if (err) throw err;
            console.log("Features written");
        })
    });
}

function genRegister(material) {
    return `
	if (SEFOres.CONFIG.` + material + `) {
		RegistryKey<ConfiguredFeature<?, ?>> ore` + capitalize(material) + `Overworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
		new Identifier(MOD_ID, "ore_` + material + `_overworld"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ore` + capitalize(material) + `Overworld.getValue(), ORE_` + material.toUpperCase() + `_OVERWORLD);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ore` + capitalize(material) + `Overworld);
	}`
}

function genRegisters() {
    ORES.forEach((e, i) => {
        fs.appendFile("./registers.txt", genRegister(e), function(err) {
            if (err) throw err;
            console.log("Registers written");
        })
    });
}

function genAll() {
    genConfigs();
    genFeatures();
    genRegisters();
}

genAll();