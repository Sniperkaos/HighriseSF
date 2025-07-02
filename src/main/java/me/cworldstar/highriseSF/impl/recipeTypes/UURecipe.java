package me.cworldstar.highriseSF.impl.recipeTypes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.server.RecipeManager;

public class UURecipe {
	
	private final Map<Character, Object> UU_RECIPE_KEY = new HashMap<Character, Object>();
	
	private ItemStack output;
	private String[] recipe;
	private String recipeID;
	
	/**
	 * @param recipeID The recipe ID.
	 * @param recipe List of any letters in a pattern. They will be replaced with the UU matter ItemStack at runtime.
	 * @param output The output that this recipe should create.
	 */
	public UURecipe(String recipeID, String[] recipe, ItemStack output) {
		this.output = output;
		this.recipe = recipe;
		this.recipeID = recipeID;
	}
	
	public ItemStack output() {
		return this.output;
	}
	
	public String[] recipe() {
		return this.recipe;
	}
	
	public void register() {
		UU_RECIPE_KEY.put('U', RecipeManager.createExactChoice(Registry.getRegistryItem("HRSF_UU_MATTER").getItem()));
		RecipeManager.add(RecipeManager.createShapedRecipe(recipeID, output, recipe, UU_RECIPE_KEY));
	}

	public String key() {
		return recipeID;
	}
	
}
