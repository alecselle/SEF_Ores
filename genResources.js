var fs = require('fs');
const ORES = ["aluminum", "chromium", "iridium", "lead", "nickel", "platinum", "silver", "tin", "titanium", "tungsten", "zinc"];
const ALLOYS = ["brass", "bronze", "electrum", "invar", "steel"];

const BLOCKSTATE_PATH = "./src/main/resources/assets/sef_ores/blockstates/";
const MODEL_PATH = "./src/main/resources/assets/sef_ores/models/";
const LOOT_PATH = "./src/main/resources/data/sef_ores/loot_tables/";
const TAG_PATH = "./src/main/resources/data/c/tags/items/";
const LANG_PATH = "./src/main/resources/assets/sef_ores/lang/en_us.json";

function blockstateJson(material, type) {
    return `
	{
    	"variants": {
        	"": { "model": "sef_ores:block/` + material + `_` + type + `" }
    	}
	}`
}

function itemModelJson(material, type) {
    if (type == "raw") return `
	{
		"parent": "item/generated",
		"textures": {
			"layer0": "sef_ores:item/` + type + `_` + material + `"
		}
	}`
    else return `
	{
		"parent": "item/generated",
		"textures": {
			"layer0": "sef_ores:item/` + material + `_` + type + `"
		}
	}`
}

function blockModelJson(material, type) {
    return `
	{
		"parent": "sef_ores:block/` + material + `_` + type + `"
	}`
}

function blockItemModelJson(material, type) {
    if (type == "raw") return `
	{
		"parent": "sef_ores:block/` + type + `_` + material + `"
	}`
    else return `
	{
		"parent": "sef_ores:block/` + material + `_` + type + `"
	}`
}

function tagJson(material, type) {
    if (type == "raw") return `
	{
		"replace": false,
		"values": [
			"sef_ores:` + type + `_` + material + `"
		]
	}`
    return `
	{
		"replace": false,
		"values": [
			"sef_ores:` + material + `_` + type + `"
		]
	}`
}

function loot_tableJson(material) {
    return `
	{
		"type": "minecraft:block",
		"pools": [{
			"rolls": 1.0,
			"bonus_rolls": 0.0,
			"entries": [{
				"type": "minecraft:alternatives",
				"children": [{
						"type": "minecraft:item",
						"conditions": [{
							"condition": "minecraft:match_tool",
							"predicate": {
								"enchantments": [{
									"enchantment": "minecraft:silk_touch",
									"levels": {
										"min": 1
									}
								}]
							}
						}],
						"name": "sef_ores:` + material + `_ore"
					},
					{
						"type": "minecraft:item",
						"functions": [{
								"function": "minecraft:apply_bonus",
								"enchantment": "minecraft:fortune",
								"formula": "minecraft:ore_drops"
							},
							{
								"function": "minecraft:explosion_decay"
							}
						],
						"name": "sef_ores:raw_` + material + `"
					}
				]
			}]
		}]
	}`
}

function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

// Add new files (ore and block)
function genBlockstate(material, type) {
    fs.writeFile(BLOCKSTATE_PATH + material + "_" + type + ".json", blockstateJson(material, type), function(err) {
        if (err) throw err;
        console.log("Blockstate created: " + material + "_" + type);
    })
}

function genBlockstates(material, alloy = false) {
    if (!alloy) genBlockstate(material, "ore");
    genBlockstate(material, "block");
}

// Add new files (ore, block, raw, nugget, ingot, dust)
function genModel(material, type) {
    if (type == "ore" || type == "block") {
        fs.writeFile(MODEL_PATH + "block/" + material + "_" + type + ".json", blockModelJson(material, type), function(err) {
            if (err) throw err;
            console.log("Block model created: " + material + "_" + type);
        });
        fs.writeFile(MODEL_PATH + "item/" + material + "_" + type + ".json", blockItemModelJson(material, type), function(err) {
            if (err) throw err;
            console.log("Block item model created: " + material + "_" + type);
        });
    } else if (type == "raw") {
        fs.writeFile(MODEL_PATH + "item/" + type + "_" + material + ".json", itemModelJson(material, type), function(err) {
            if (err) throw err;
            console.log("Item model created: " + type + "_" + material);
        });
    } else {
        fs.writeFile(MODEL_PATH + "item/" + material + "_" + type + ".json", itemModelJson(material, type), function(err) {
            if (err) throw err;
            console.log("Item model created: " + material + "_" + type);
        });
    }
}

function genModels(material, alloy = false) {
    if (!alloy) {
        genModel(material, "ore");
        genModel(material, "raw");
    }
    genModel(material, "ingot");
    genModel(material, "nugget");
    genModel(material, "block");
    genModel(material, "dust");
}

// Add new files (ore)
function genLootTable(material) {
    fs.writeFile(LOOT_PATH + "blocks/" + material + "_ore.json", loot_tableJson(material), function(err) {
        if (err) throw err;
        console.log("Loot table created: " + material + "_ore");
    });
}

// Add new files (ore, block, raw, nugget, ingot, dust)
function genTag(material, type) {
    if (type == "raw") {
        fs.writeFile(TAG_PATH + type + "_" + material + "_ores.json", tagJson(material, type), function(err) {
            if (err) throw err;
            console.log("Tag created: " + type + "_" + material + "_ores");
        });
    } else {
        fs.writeFile(TAG_PATH + material + "_" + type + "s.json", tagJson(material, type), function(err) {
            if (err) throw err;
            console.log("Tag created: " + material + "_" + type + "s");
        });
        if (type == "block") {
            fs.writeFile(TAG_PATH + material + "_storage_" + type + "s.json", tagJson(material, type), function(err) {
                if (err) throw err;
                console.log("Tag created: " + material + "_storage_" + type + "s");
            });
        }
    }
}

function genTags(material, alloy = false) {
    if (!alloy) {
        genTag(material, "ore");
        genTag(material, "raw");
    }
    genTag(material, "ingot");
    genTag(material, "nugget");
    genTag(material, "block");
    genTag(material, "dust");
}

// Append to json (ore, block, raw, nugget, ingot, dust)
function genLang(material, type) {
    let raw = fs.readFileSync(LANG_PATH);
    let json = JSON.parse(raw);
    if (type == "block" || type == "ore") {
        if (!json.hasOwnProperty("block.sef_ores." + type + "_" + material)) {
            json["block.sef_ores." + type + "_" + material] = capitalize(type) + " " + capitalize(material);
        }
        if (!json.hasOwnProperty("item.sef_ores." + type + "_" + material)) {
            json["item.sef_ores." + type + "_" + material] = capitalize(type) + " " + capitalize(material);
        }
    } else if (type == "raw") {
        if (!json.hasOwnProperty("item.sef_ores." + type + "_" + material)) {
            json["item.sef_ores." + type + "_" + material] = capitalize(type) + " " + capitalize(material);
        }
    } else {
        if (!json.hasOwnProperty("item.sef_ores." + material + "_" + type)) {
            json["item.sef_ores." + material + "_" + type] = capitalize(material) + " " + capitalize(type);
        }
    }
    let data = JSON.stringify(json, null, "\n");
    fs.writeFileSync(LANG_PATH, data);
}

function genGenerationLangs(material) {
    let raw = fs.readFileSync(LANG_PATH);
    let json = JSON.parse(raw);
    if (!json.hasOwnProperty("text.autoconfig.sef_ores.option." + material)) {
        json["text.autoconfig.sef_ores.option." + material] = "Generate " + capitalize(material);
    }
    if (!json.hasOwnProperty("text.autoconfig.sef_ores.option." + material + "_vein_size")) {
        json["text.autoconfig.sef_ores.option." + material + "_vein_size"] = capitalize(material) + " Vein Size";
    }
    if (!json.hasOwnProperty("text.autoconfig.sef_ores.option." + material + "_vein_size")) {
        json["text.autoconfig.sef_ores.option." + material + "_vein_size"] = capitalize(material) + " Vein Frequency";
    }
    if (!json.hasOwnProperty("text.autoconfig.sef_ores.option." + material + "_min_y")) {
        json["text.autoconfig.sef_ores.option." + material + "_min_y"] = capitalize(material) + " Min Y";
    }
    if (!json.hasOwnProperty("text.autoconfig.sef_ores.option." + material + "_max_y")) {
        json["text.autoconfig.sef_ores.option." + material + "_max_y"] = capitalize(material) + " Max Y";
    }
    let data = JSON.stringify(json, null, "\n");
    fs.writeFileSync(LANG_PATH, data);
}

function genLangs(material, alloy = false) {
    if (!alloy) {
        genLang(material, "ore");
        genLang(material, "raw");
    }
    genLang(material, "ingot");
    genLang(material, "nugget");
    genLang(material, "block");
    genLang(material, "dust");
    if (!alloy) genGenerationLangs(material);
}

// Generate a specific material
function genResource(material, alloy = false) {
    genBlockstates(material, alloy);
    genModels(material, alloy);
    if (!alloy) genLootTable(material);
    genTags(material, alloy);
    genLangs(material, alloy);
}

// Generate all materials
function genAll() {
    ORES.forEach(e => {
        genResource(e, false)
    });
    ALLOYS.forEach(e => {
        genResource(e, true);
    });
}

genAll();