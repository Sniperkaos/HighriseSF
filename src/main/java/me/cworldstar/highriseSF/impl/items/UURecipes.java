package me.cworldstar.highriseSF.impl.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.recipeTypes.UURecipe;

public class UURecipes {
	public UURecipes() {
		Registry.registerUURecipe(new UURecipe("UU_DIAMOND", new String[] {"UUU","UU ", "   "}, new ItemStack(Material.DIAMOND, 1)));
		Registry.registerUURecipe(new UURecipe("UU_OAK_LOG", new String[] {" U ","   ", "   "}, new ItemStack(Material.OAK_LOG, 8)));
	}
}
