package me.cworldstar.highriseSF.impl.items;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.impl.Registry;

public class HighriseSFItems {
	public static SlimefunItem TestItem = new me.cworldstar.highriseSF.impl.sf.items.TestItem(Registry.getDefaultItemGroup(), HighriseSFItemStacks.TEST_ITEM_SF_STACK, RecipeType.NULL, null);
	
	public HighriseSFItems() {
		new CompressedItem(Registry.getDefaultItemGroup(), Material.DIAMOND_BLOCK).registerAll();
		new CompressedItem(Registry.getDefaultItemGroup(), Material.IRON_BLOCK).registerAll();
		new CompressedItem(Registry.getDefaultItemGroup(), Material.GOLD_BLOCK).registerAll();
		Registry.registerItem(TestItem);
	}
	
}
