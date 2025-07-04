package me.cworldstar.highriseSF.impl.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.highriseSF.utils.FormatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

public class ItemStackBuilder {
	
	private static final Pattern REGEXP_PATTERN = Pattern.compile("[&§]+[a-zA-Z1-9]");
	
	protected ItemStack item;
	
	public ItemStackBuilder(Material m) {
		this.item = new CustomItemStack(m, ItemMeta -> {

		});
	}
	
	public ItemStackBuilder(ItemStack i) {
		this.item = i;
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
		
		if(REGEXP_PATTERN.matcher(name).find()) {
			meta.displayName(FormatUtils.format(name));
			setMeta(meta);
			return this;
		}
		
		meta.displayName(MiniMessage.builder().tags(TagResolver.builder().resolver(StandardTags.color()).resolver(StandardTags.decorations()).resolver(StandardTags.gradient()).build()).build().deserialize(name));
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
			if(REGEXP_PATTERN.matcher(line).find()) {
				mlore.add(FormatUtils.format(line));
				continue;
			} else {
				mlore.add(MiniMessage.builder().tags(TagResolver.builder().resolver(StandardTags.color()).resolver(StandardTags.decorations()).resolver(StandardTags.gradient()).build()).build().deserialize(line));
			}
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
