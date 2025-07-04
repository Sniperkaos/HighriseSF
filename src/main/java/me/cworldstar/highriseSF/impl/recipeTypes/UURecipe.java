package me.cworldstar.highriseSF.impl.recipeTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.Registry;

public class UURecipe {
	
	private ItemStack output;
	private ItemStack[] recipe;
	private String recipeID;
	
	/**
	 * @param recipeID The recipe ID.
	 * @param recipe List of any letters in a pattern. They will be replaced with the UU matter ItemStack at runtime.
	 * @param output The output that this recipe should create.
	 */
	
	public UURecipe(String recipeID, String[] recipe, ItemStack output) {
		this.output = output;
		this.recipe = Arrays.asList(recipe).stream().map(str -> str == " " ? null : Registry.getRegistryItem("HRSF_UU_MATTER").getItem()).collect(Collectors.toList()).toArray(new ItemStack[0]);
		this.recipeID = recipeID;
	}
	
	public ItemStack output() {
		return this.output;
	}
	
	public ItemStack[] recipe() {
		return this.recipe;
	}
	
	public void register() {
		HighriseSF.log(List.of(recipe()).toString());
		RecipeType.ENHANCED_CRAFTING_TABLE.register(recipe(), output);
	}

	public String key() {
		return recipeID;
	}
	
}
