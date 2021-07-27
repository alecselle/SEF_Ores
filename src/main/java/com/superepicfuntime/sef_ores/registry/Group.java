package com.superepicfuntime.sef_ores.registry;

// ItemGroup imports
import net.minecraft.item.ItemGroup;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.util.*;


public class Group {
	public static final ItemGroup SEF_ORES = FabricItemGroupBuilder.create(new Identifier("sef_ores", "sef_ores_group")).build();
}
