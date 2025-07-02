package me.cworldstar.highriseSF.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.groups.MultiGroup;
import me.cworldstar.highriseSF.impl.events.HighriseSFRegistryEvent;
import me.cworldstar.highriseSF.impl.events.HighriseSFRegistryPreFinalizeEvent;
import me.cworldstar.highriseSF.impl.recipeTypes.UURecipe;

public class Registry {
	
	private static boolean finalized = false;
	private static boolean closed = false;
	private static Map<String, SlimefunItem> items = new HashMap<String, SlimefunItem>();
	private static List<UURecipe> recipes = new ArrayList<UURecipe>();
	private static Map<String, ItemGroup> groups = new HashMap<String, ItemGroup>();
	private static final MultiGroup DEFAULT_ITEM_GROUP = new MultiGroup(HighriseSF.namespacedKey("DEFAULT_ITEM_GROUP"), CustomItemStack.create(new ItemStack(Material.BLUE_CONCRETE_POWDER), "HighriseSF", ""), "&c&lHighriseSF");
	
	public Registry() {
		throw new Error("This is a static class!");
	}
	
	public static @Nonnull ItemGroup getDefaultItemGroup() {
		if(!DEFAULT_ITEM_GROUP.isRegistered()) {
			DEFAULT_ITEM_GROUP.register(HighriseSF.get());
		}
		return DEFAULT_ITEM_GROUP;
	}
	
	public static @Nullable ItemGroup getItemGroup(String key) {
		return groups.get(key);
	}
	
	public static ItemGroup registerItemGroup(ItemGroup group) {
		groups.put(group.getKey().getKey(), group);
		DEFAULT_ITEM_GROUP.add(group);
		return group;
	}
	
	public static void registerUURecipe(UURecipe recipe) {
		recipes.add(recipe);
	}
	
	public static void registerItem(SlimefunItem item) {
		if(finalized) {
			throw new Error("The registry has already been finalized! No new items may be added.");
		}
		items.put(item.getId(), item);
	}
	
	public static @Nullable SlimefunItem getRegistryItem(String s) {
		return items.get(s);
	}
	
	public static void finalizeRegistry() {
		if(closed == true) {
			return;
		}
		closed = true; //-- Prevent race condition errors
		HighriseSF.log("Registering all items...");
		items.forEach((String id, SlimefunItem item) -> {
			HighriseSFRegistryEvent event = new HighriseSFRegistryEvent(item);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if(!event.cancelled()) {
				HighriseSF.log("Registering " + item.getId());
				item.register(HighriseSF.get());
			}
		});
		HighriseSF.log("Item registration phase complete. If you see an issue, create an issue report.");

		HighriseSF.log("Registering UU Matter recipes...");
		recipes.forEach((UURecipe item) -> {
			HighriseSF.log("Registering " + item.key());
			item.register();
		});
		HighriseSF.log("UU Matter recipe phase complete. If you see an issue, create an issue report.");

		
		//-- register
		getDefaultItemGroup();
		HighriseSF.log("The registry is now finalized. No new items may be added nor removed.");
		Bukkit.getServer().getPluginManager().callEvent(new HighriseSFRegistryPreFinalizeEvent());
		
		finalized = true;
	}
}
