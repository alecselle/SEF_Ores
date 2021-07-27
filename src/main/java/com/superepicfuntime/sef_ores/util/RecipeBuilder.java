package com.superepicfuntime.sef_ores.util;

import com.google.gson.*;

import java.util.ArrayList;
import net.minecraft.util.Identifier;

public class RecipeBuilder {
	public static JsonObject createShaped(ArrayList<Character> keys, ArrayList<Identifier> items, ArrayList<String> type, ArrayList<String> pattern, Identifier output, int count) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:crafting_shaped");

        JsonArray jsonArray = new JsonArray();
        jsonArray.add(pattern.get(0));
        jsonArray.add(pattern.get(1));
        jsonArray.add(pattern.get(2));

        json.add("pattern", jsonArray);

        JsonObject individualKey;
        JsonObject keyList = new JsonObject();
 
        for (int i = 0; i < keys.size(); ++i) {
            individualKey = new JsonObject();
            individualKey.addProperty(type.get(i), items.get(i).toString());
            keyList.add(keys.get(i) + "", individualKey);
        }
 
        json.add("key", keyList);

        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);
        json.add("result", result);
 
        return json;
    }

	public static JsonObject createShapeless(ArrayList<Identifier> items, ArrayList<String> type, Identifier output, int count) {
		JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:crafting_shapeless");

		JsonArray itemList = new JsonArray();
 
		for (int i = 0; i < items.size(); ++i) {
			JsonObject iL = new JsonObject();
			iL.addProperty(type.get(i), items.get(i).toString());
			itemList.add(iL);
		}
 
        json.add("ingredients", itemList);

        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);
        json.add("result", result);
 
        return json;
	}

	public static JsonObject createSmelting(Identifier item, String type, Identifier output) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "minecraft:smelting");

		JsonObject itemList = new JsonObject();
		itemList.addProperty(type, item.toString());
		json.add("ingredient", itemList);

		json.addProperty("result", output.toString());
		json.addProperty("experience", 0.8);
		json.addProperty("cookingtime", 200);

		return json;
	}

	public static JsonObject createBlasting(Identifier item, String type, Identifier output) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "minecraft:blasting");

		JsonObject itemList = new JsonObject();
		itemList.addProperty(type, item.toString());
		json.add("ingredient", itemList);

		json.addProperty("result", output.toString());
		json.addProperty("experience", 0.8);
		json.addProperty("cookingtime", 100);

		return json;
	}

	public static JsonObject createConversion(Identifier item, Identifier manual, Identifier output) {
		JsonObject json = new JsonObject();
		json.addProperty("type", "crafting_shapeless");

		JsonArray itemList = new JsonArray();
		JsonObject manualList = new JsonObject();
		JsonObject remainderList = new JsonObject();
		JsonObject inputList = new JsonObject();

		inputList.addProperty("tag", item.toString());
		manualList.addProperty("item", manual.toString());
		remainderList.addProperty("item", manual.toString());
		manualList.add("remainder", remainderList);

		itemList.add(inputList);
		itemList.add(manualList);
		
		json.add("ingredients", itemList);

		JsonObject resultList = new JsonObject();
		resultList.addProperty("item", output.toString());

		json.add("result", resultList);

		return json;
	}
}
