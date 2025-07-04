package me.cworldstar.highriseSF.impl.builders;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.groups.InvisibleGroup;

public class ItemGroupBuilder {
	
	private CustomItemStack item;
	private String id;
	private int tier;
	
	public ItemGroupBuilder() {
		
	}
	
	public ItemGroupBuilder setItem(CustomItemStack item) {
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
