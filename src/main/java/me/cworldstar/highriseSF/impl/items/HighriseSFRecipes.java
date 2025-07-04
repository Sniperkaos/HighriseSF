package me.cworldstar.highriseSF.impl.items;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.cworldstar.highriseSF.impl.Registry;

public class HighriseSFRecipes {
	public static void register() {
		RecipeType.ENHANCED_CRAFTING_TABLE.register(new ItemStack[] {
				SlimefunItems.NEPTUNIUM, Registry.getRegistryItemAsItemStack("HRSF_LIVING_ALLOY"), SlimefunItems.NEPTUNIUM,
				SlimefunItems.NEPTUNIUM, SlimefunItems.NEPTUNIUM, SlimefunItems.NEPTUNIUM,
				SlimefunItems.NEPTUNIUM, SlimefunItems.NEPTUNIUM, SlimefunItems.NEPTUNIUM
		}, Registry.getRegistryItem("DRUID_CHESTPLATE").getItem());
	}
}
