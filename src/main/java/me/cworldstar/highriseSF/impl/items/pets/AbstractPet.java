package me.cworldstar.highriseSF.impl.items.pets;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.handlers.NewInstanceHandler;
import me.cworldstar.highriseSF.utils.FormatUtils;

public abstract class AbstractPet extends SlimefunItem implements NotPlaceable {
	
	private ItemStack food;
	private int snackAmount = 1;
	private int maxFood = 100;
	
	public int getSnackAmount() {
		return snackAmount;
	}
	
	public void setSnackAmount(int amount) {
		this.snackAmount = amount;
	}
	
	public abstract Class<? extends AbstractPet> getIdentifier();
	
	public AbstractPet(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, id, recipeType, recipe);
	}
	
	public AbstractPet(ItemGroup itemGroup, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
	}

	public int getMaxFood() {
		return this.maxFood;
	}
	
	public void setMaxFood(int maxFood) {
		this.maxFood = maxFood;
	}
	
	public void setFood(ItemStack item) {
		this.food = item;
	}
	
	public void setEatAmount(int amount) {
		this.snackAmount = amount;
	}
	
	public ItemStack getFood() {
		return this.food;
	}
	
	public boolean onPetTrigger(ItemStack i, Player p, Inventory inventory) {
		return true;
	}
	
	public static void updateFood(ItemStack petItemStack, double amount) {
		ItemMeta meta = petItemStack.getItemMeta();
		if(meta == null) {
			return;
		}
		
		meta.getPersistentDataContainer().set(HighriseSF.key("FOOD"), PersistentDataType.DOUBLE, amount);
		petItemStack.setItemMeta(meta);
	}
	
	public boolean hasFood(ItemStack item) {
		Optional<Double> petIntegrity = getFood(item);
		if(petIntegrity.isPresent() && petIntegrity.get() > 0) {
			return true;
		}
		return false;
	}
	
	public static @Nullable Optional<Double> getFood(@Nonnull ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if(meta == null) {
			return Optional.empty();
		}
		PersistentDataContainer container = meta.getPersistentDataContainer();
		if(!container.has(HighriseSF.key("FOOD"))) {
			updateFood(item, 100D);
			return getFood(item);
		}
		
		return Optional.of(meta.getPersistentDataContainer().get(HighriseSF.key("FOOD"), PersistentDataType.DOUBLE));
	}
	
	public static void reduceFood(ItemStack petItemStack, int amount) {
		ItemMeta meta = petItemStack.getItemMeta();
		if(meta == null) {
			return;
		}
		
		double petIntegrity = meta.getPersistentDataContainer().get(HighriseSF.key("FOOD"), PersistentDataType.DOUBLE);
		
		if(petIntegrity < amount) {
			updateFood(petItemStack, 0.0);
		} else {
			updateFood(petItemStack, petIntegrity - (double) (amount));
		}
	}
	
	/** 
	 * @param pet The ItemStack of this pet. It will only be passed if the given itemstack is an SF item, and only if the SF item is an
	 * instance of X, where X is a class that extends {@link AbstractPet}.
	 * @param player The {@link Player} owner of this pet, whose pet is ticking.
	 * @param i The given {@link Inventory} where this pet is located.
	 */
	public boolean feed(ItemStack pet, Player player, Inventory i) {
		ItemStack food = this.food;
		for(ItemStack item : i.getContents()) {
			if(item == null) {
				continue;
			}
			
			if (item.isSimilar(food)) {
				player.playSound(player, Sound.ENTITY_CAMEL_EAT, 1, 1);
				item.setAmount(item.getAmount() - this.snackAmount);
				updateFood(pet, 100);
				updateLore(pet);
				return true;
			}
		}
		return false;
	}

	public abstract void updateLore(ItemStack pet);
}
