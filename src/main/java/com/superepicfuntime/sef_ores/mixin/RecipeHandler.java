package com.superepicfuntime.sef_ores.mixin;

import com.superepicfuntime.sef_ores.registry.ModInit;
import com.superepicfuntime.sef_ores.items.Material;

import com.google.gson.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Map;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

@Mixin(RecipeManager.class)
public class RecipeHandler {
	@Inject(method = "apply", at = @At("HEAD"))
	public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
		for (Map.Entry<String, Material> m : ModInit.MATERIALS.entrySet()) {
			ArrayList<JsonObject> recipes = m.getValue().recipes();
			int i = 0;
			for (JsonObject r : recipes) {
				if (r.get("result").isJsonObject()) {
					System.out.println("Generating recipe: " + r);
					map.put(new Identifier("sef_ores", r.getAsJsonObject("result").get("item").toString().replaceAll("sef_ores:", "").replaceAll("\"", "")+i++), r);
				} else {
					System.out.println("Generating recipe: " + r);
					map.put(new Identifier("sef_ores", r.get("result").toString().replaceAll("sef_ores:", "").replaceAll("\"", "")+i++), r);
				}			
			}
		}
		int i = 0;
		for(JsonObject j : ModInit.RECIPES) {
			if (j.get("result").isJsonObject()) {
				System.out.println("Generating recipe: " + j);
				map.put(new Identifier("sef_ores", j.getAsJsonObject("result").get("item").toString().replaceAll("sef_ores:", "").replaceAll("\"", "")+i++), j);
			} else {
				System.out.println("Generating recipe: " + j);
				map.put(new Identifier("sef_ores", j.get("result").toString().replaceAll("sef_ores:", "").replaceAll("\"", "")+i++), j);
			}			
		}
	}
}