package com.superepicfuntime.sef_ores;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import com.superepicfuntime.sef_ores.config.SEFOresConfig;
import com.superepicfuntime.sef_ores.registry.ModInit;
import net.fabricmc.api.ModInitializer;

public class SEFOres implements ModInitializer {
	public static SEFOresConfig CONFIG = new SEFOresConfig();
		

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		AutoConfig.register(SEFOresConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(SEFOresConfig.class).getConfig();
		ModInit.init();
	}
}
