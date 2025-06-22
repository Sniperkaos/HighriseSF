package me.cworldstar.highriseSF.impl.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.utils.FormatUtils;

public class HighriseSFItemStacks {
	public static ItemStack TEST_ITEM_STACK = CustomItemStack.create(new ItemStack(Material.CHISELED_TUFF_BRICKS), FormatUtils.formatString("&4&lTest Item"), "", "&7> &fThis is a test item.", "", "", "----------------------");
	public static SlimefunItemStack TEST_ITEM_SF_STACK = new SlimefunItemStack("TEST_ITEM", TEST_ITEM_STACK);

}
