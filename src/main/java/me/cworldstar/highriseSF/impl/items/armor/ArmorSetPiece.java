package me.cworldstar.highriseSF.impl.items.armor;

import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.handlers.DurabilityLossHandler;
import me.cworldstar.highriseSF.utils.PDCHelper;

public class ArmorSetPiece extends SlimefunItem {
	
	private void setup(ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		if(meta instanceof Damageable) {
			Damageable dmeta = (Damageable) meta;
			Optional<PersistentDataContainer> option = PDCHelper.getPDC(dmeta);
			if(option.isPresent()) {
				PersistentDataContainer container = option.get();
				container.set(HighriseSF.key("MAX_DURABILITY"), PersistentDataType.INTEGER, (int) i.getType().getMaxDurability());
				container.set(HighriseSF.key("DURABILITY"), PersistentDataType.INTEGER, (int) i.getType().getMaxDurability());
				i.setItemMeta(dmeta);
			}
		}
	}
	
	public ArmorSetPiece setMaxDurability(int number, boolean refreshDurability) {
		ItemStack i = this.getItem();
		ItemMeta meta = i.getItemMeta();
		if(meta instanceof Damageable) {
			Damageable dmeta = (Damageable) meta;
			Optional<PersistentDataContainer> option = PDCHelper.getPDC(dmeta);
			if(option.isPresent()) {
				PersistentDataContainer container = option.get();
				container.set(HighriseSF.key("MAX_DURABILITY"), PersistentDataType.INTEGER, number + i.getType().getMaxDurability());
				if(refreshDurability) {
					container.set(HighriseSF.key("DURABILITY"), PersistentDataType.INTEGER, number + i.getType().getMaxDurability());
				}
				i.setItemMeta(dmeta);
			}
		}
		return this;
	}
	
	public ArmorSetPiece(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, new SlimefunItemStack(id, item), recipeType, recipe);
		addItemHandler(new DurabilityLossHandler() {
			@Override
			public boolean onDurabilityLoss(PlayerItemDamageEvent e, Player p, ItemStack item) {
				ItemMeta meta = item.getItemMeta();
				if(meta instanceof Damageable) {
					Damageable dmeta = (Damageable) meta;
					Optional<PersistentDataContainer> option = PDCHelper.getPDC(dmeta);
					if(option.isPresent()) {
						PersistentDataContainer container = option.get();
						int maxItemDurability = container.get(HighriseSF.key("MAX_DURABILITY"), PersistentDataType.INTEGER);
						int itemDurability = container.get(HighriseSF.key("DURABILITY"), PersistentDataType.INTEGER);
						
						int maxDurability = (item.getType().getMaxDurability() + maxItemDurability);
						
						itemDurability -= e.getDamage();
						dmeta.setDamage(Math.round(item.getType().getMaxDurability() * (itemDurability / maxDurability)));
						container.set(HighriseSF.key("DURABILITY"), PersistentDataType.INTEGER, (int) itemDurability);
						item.setItemMeta(dmeta);
						return true;
					}
				}
				return false;
			}
		});
		setup(this.getItem());
		Registry.registerItem(this);
	}
}
