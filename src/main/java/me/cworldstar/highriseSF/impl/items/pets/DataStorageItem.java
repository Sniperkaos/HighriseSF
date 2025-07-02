package me.cworldstar.highriseSF.impl.items.pets;

import java.util.Optional;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.cworldstar.highriseSF.HighriseSF;

public interface DataStorageItem {
	
	@ParametersAreNonnullByDefault
	public default void store(ItemStack item, String key, String data) {
		if(item.hasItemMeta()) {
			item.getItemMeta().getPersistentDataContainer().set(HighriseSF.key(key), PersistentDataType.STRING, data);
		}
	}
	public default @Nullable Optional<String> load(ItemStack item, String key) {
		if(item.hasItemMeta()) {
			return Optional.ofNullable( item.getItemMeta().getPersistentDataContainer().get(HighriseSF.key(key), PersistentDataType.STRING) );
		}
		return Optional.empty();
	}
}
