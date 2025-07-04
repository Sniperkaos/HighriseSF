package me.cworldstar.highriseSF.groups;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

public class InvisibleGroup extends ItemGroup {

	public InvisibleGroup(NamespacedKey key, CustomItemStack item, int tier) {
		super(key, item.asOne(), tier);
	}
	
	@Override
	public boolean isVisible(Player p) {
		return false;
	}
}
