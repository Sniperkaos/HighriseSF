package me.cworldstar.highriseSF.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;

public class InvisibleGroup extends ItemGroup {

	public InvisibleGroup(NamespacedKey key, ItemStack item, int tier) {
		super(key, item, tier);
	}
	
	@Override
	public boolean isVisible(Player p) {
		return false;
	}
}
