package me.cworldstar.highriseSF.impl;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.events.HighriseSFRegistryEvent;

public class Registry {
	
	private static boolean finalized = false;
	private static boolean closed = false;
	private static List<SlimefunItem> items = new ArrayList<SlimefunItem>();
	private static ItemGroup DEFAULT_ITEM_GROUP = new ItemGroup(HighriseSF.namespacedKey("DEFAULT_ITEM_GROUP"), CustomItemStack.create(new ItemStack(Material.BLUE_CONCRETE_POWDER), "HighriseSF", ""));

	public Registry() {
		throw new Error("This is a static class!");
	}
	
	public static ItemGroup getDefaultItemGroup() {
		if(!DEFAULT_ITEM_GROUP.isRegistered()) {
			DEFAULT_ITEM_GROUP.register(HighriseSF.get());
		}
		return DEFAULT_ITEM_GROUP;
	}
	
	public static void registerItem(SlimefunItem item) {
		if(finalized) {
			throw new Error("The registry has already been finalized! No new items may be added.");
		}
		items.add(item);
	}
	
	public static void finalizeRegistry() {
		if(closed == true) {
			return;
		}
		closed = true; //-- Prevent race condition errors
		items.forEach((SlimefunItem item) -> {
			HighriseSFRegistryEvent event = new HighriseSFRegistryEvent(item);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if(!event.cancelled()) {
				item.register(HighriseSF.get());
			}
		});
		finalized = true;
	}
}
