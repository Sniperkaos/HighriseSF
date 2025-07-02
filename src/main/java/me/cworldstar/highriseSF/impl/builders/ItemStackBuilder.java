package me.cworldstar.highriseSF.impl.builders;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.utils.FormatUtils;
import net.kyori.adventure.text.Component;

public class ItemStackBuilder {
	
	protected ItemStack item;
	
	public ItemStackBuilder(Material m) {
		this.item = CustomItemStack.create(m, ItemMeta -> {

		});
	}
	
	public @Nonnull ItemMeta getMeta() {
		return this.item.getItemMeta();
	}
	
	private void setMeta(@Nonnull ItemMeta meta) {
		this.item.setItemMeta(meta);
	}
	
	public ItemStackBuilder setAmount(int amount) {
		this.item.setAmount(amount);
		return this;
	}
	
	public ItemStackBuilder setName(String name) {
		ItemMeta meta = getMeta();
		meta.itemName(Component.text(FormatUtils.formatString(name)));
		setMeta(meta);
		return this;
	}
	
	public ItemStackBuilder setLore(String[] lore) {
		ItemMeta meta = getMeta();
		List<Component> mlore;
		if(meta.hasLore()) {
			mlore = meta.lore();
		} else {
			mlore = new ArrayList<Component>();
		}
		for(String line : lore) {
			mlore.add(Component.text(FormatUtils.formatString(line)));
		}
		meta.lore(mlore);
		setMeta(meta);
		return this;
	}
	
	public ItemStack get() {
		return this.item;
	}
	
	public ItemStack item() {
		return get();
	}
	
}
