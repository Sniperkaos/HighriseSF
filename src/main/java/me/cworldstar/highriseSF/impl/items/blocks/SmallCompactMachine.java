package me.cworldstar.highriseSF.impl.items.blocks;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class SmallCompactMachine extends AbstractCompactMachine {
	public SmallCompactMachine(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) 
	{
		super(itemGroup, item, id, recipeType, recipe, 6);
	}

	public SmallCompactMachine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item.asOne(), item.getItemId(), recipeType, recipe, 6);
	}
}