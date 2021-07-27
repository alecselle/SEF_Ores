package com.superepicfuntime.sef_ores.items;

import com.superepicfuntime.sef_ores.registry.ModInit;
import java.util.List;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class ConversionManual extends Item {
	public ConversionManual(Settings settings) {
		super(settings.recipeRemainder(ModInit.CONVERSION_MANUAL).rarity(Rarity.EPIC).fireproof());
	}	

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.sef_ores.conversion_manual.tooltip").formatted(Formatting.DARK_GRAY, Formatting.ITALIC));
	}

	@Override
	public boolean hasRecipeRemainder() {
		return true;
	}
}
