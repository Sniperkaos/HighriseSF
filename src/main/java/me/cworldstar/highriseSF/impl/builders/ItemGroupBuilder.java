package me.cworldstar.highriseSF.impl.builders;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.groups.InvisibleGroup;

public class ItemGroupBuilder {
	
	private ItemStack item;
	private String id;
	private int tier;
	
	public ItemGroupBuilder() {
		
	}
	
	public ItemGroupBuilder setItem(ItemStack item) {
		this.item = item;
		return this;
	}
	
	public ItemGroupBuilder setTier(int tier) {
		this.tier = tier;
		return this;
	}
	
	public ItemGroupBuilder setID(String id) {
		this.id = id;
		return this;
	}
	
	public ItemGroup build() {
		return new InvisibleGroup(HighriseSF.namespacedKey(this.id), item, tier);
	}
	
}
