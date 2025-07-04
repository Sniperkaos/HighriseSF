package me.cworldstar.highriseSF.utils;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.Nullable;

import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;

public class PDCHelper {
	public static @Nullable Optional<PersistentDataContainer> getPDC(@Nonnull ItemMeta meta) {
		Validate.notNull(meta, "The ItemMeta cannot be null.");
		if(meta == null) {
			return Optional.empty();
		}
		return Optional.of(meta.getPersistentDataContainer());
	}
	
	public static void setPDC(ItemMeta meta, ItemStack i) {
		i.setItemMeta(meta);
	}
}
