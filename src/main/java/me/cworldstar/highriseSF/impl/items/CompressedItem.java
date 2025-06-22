package me.cworldstar.highriseSF.impl.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.impl.Registry;
import net.kyori.adventure.text.Component;

public class CompressedItem {

	private List<SlimefunItem> items = new ArrayList<SlimefunItem>();
	
	private SlimefunItem fromItemStack(ItemGroup group, ItemStack i, String id, RecipeType type, ItemStack[] recipe) {
		return new SlimefunItem(group, new SlimefunItemStack(id, i), type, recipe);
	}
	
	public CompressedItem(ItemGroup itemGroup, Material m) {
		ItemStack materialItemStack = new ItemStack(m);
		@NotNull Component materialName = materialItemStack.getItemMeta().itemName();
		
		ItemStack COMPRESSED_ITEM = CustomItemStack.create(materialItemStack, "&cCompressed " + materialName.examinableName());
		ItemStack DOUBLE_COMPRESSED_ITEM = CustomItemStack.create(materialItemStack, "&4Double Compressed " + materialName.examinableName());
		ItemStack TRIPLE_COMPRESSED_ITEM = CustomItemStack.create(materialItemStack, "&6Triple Compressed " + materialName.examinableName());
		
		items.add(fromItemStack(itemGroup, COMPRESSED_ITEM, "COMPRESSED_"+ materialName.examinableName().toUpperCase(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				materialItemStack, materialItemStack, materialItemStack,
				materialItemStack, materialItemStack, materialItemStack,
				materialItemStack, materialItemStack, materialItemStack
		}));
		
		items.add(fromItemStack(itemGroup, DOUBLE_COMPRESSED_ITEM, "DCOMPRESSED_"+ materialName.examinableName().toUpperCase(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				COMPRESSED_ITEM, COMPRESSED_ITEM, COMPRESSED_ITEM,
				COMPRESSED_ITEM, COMPRESSED_ITEM, COMPRESSED_ITEM,
				COMPRESSED_ITEM, COMPRESSED_ITEM, COMPRESSED_ITEM
		}));
		
		items.add(fromItemStack(itemGroup, TRIPLE_COMPRESSED_ITEM, "TCOMPRESSED_"+ materialName.examinableName().toUpperCase(), RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {
				DOUBLE_COMPRESSED_ITEM, DOUBLE_COMPRESSED_ITEM, DOUBLE_COMPRESSED_ITEM,
				DOUBLE_COMPRESSED_ITEM, DOUBLE_COMPRESSED_ITEM, DOUBLE_COMPRESSED_ITEM,
				DOUBLE_COMPRESSED_ITEM, DOUBLE_COMPRESSED_ITEM, DOUBLE_COMPRESSED_ITEM
		}));
	}
	
	public void registerAll() {
		items.forEach((SlimefunItem item) -> {
			Registry.registerItem(item);
		});
	}
}
