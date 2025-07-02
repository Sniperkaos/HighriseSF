package me.cworldstar.highriseSF.impl.items.armor;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.impl.Registry;

public class ArmorSetPiece extends SlimefunItem {
	public ArmorSetPiece(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, new SlimefunItemStack(id, item), recipeType, recipe);
		Registry.registerItem(this);
	}
}
