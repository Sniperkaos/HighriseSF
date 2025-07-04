package me.cworldstar.highriseSF.impl.items.pets;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import io.github.thebusybiscuit.slimefun4.api.events.MultiBlockCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.math.RandomUtils;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.handlers.NewInstanceHandler;
import me.cworldstar.highriseSF.impl.handlers.PlayerAttackHandler;
import me.cworldstar.highriseSF.utils.FormatUtils;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;

public class SamuraiPet extends AbstractPet implements DataStorageItem {

	private void setupSamuraiPet() {
				
		this.setFood(new ItemStack(Material.IRON_SWORD));
		this.setEatAmount(1);
		this.setSnackAmount(8);
		
		addItemHandler(new BlockPlaceHandler(false) {
			@Override
			public void onPlayerPlace(BlockPlaceEvent e) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(FormatUtils.formatString("&7You cannot place a pet!"));
			}
		});
		
		addItemHandler(new NewInstanceHandler() {
			@Override
			public void onNewInstance(ItemStack item) {
				updateLore(item);
			}
		});
		
		addItemHandler(new PlayerAttackHandler() {
			@Override
			public boolean onPlayerAttack(EntityDamageEvent e, Player player, @NotNull Entity entity) {
				for (ItemStack item : player.getInventory()) {
					SlimefunItem sfItem = SlimefunItem.getByItem(item);
					if(sfItem instanceof SamuraiPet) {
						double food = SamuraiPet.getFood(item).get();
						if(food >= SamuraiPet.this.getSnackAmount()) {
							if(RandomUtils.nextInt(10) >= 8) {
								SamuraiPet.reduceFood(item, getSnackAmount());
								updateLore(item);
								Location loc = entity.getLocation();
								e.setDamage(e.getDamage() * 1.75);
								World w = loc.getWorld();
								w.playSound(loc, Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1, 1);
								w.spawnParticle(Particle.TOTEM, loc, 7, 5, 5, 5);
							}
						} else {
							SamuraiPet.this.feed(item, player, player.getInventory());
						}
					}
				}
				return true;
			}
		});
	}
	
	public SamuraiPet(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, id, recipeType, recipe);
		setupSamuraiPet();
	}
	
	public SamuraiPet(ItemGroup itemGroup, SlimefunItemStack item, String id, RecipeType recipeType,
			ItemStack[] recipe) {
		super(itemGroup, item, id, recipeType, recipe);
		setupSamuraiPet();
	}
	
	@Override
	public void updateLore(ItemStack pet) {
		ItemMeta meta = pet.getItemMeta();
		List<TextComponent> lore = FormatUtils.getLore(Registry.getRegistryItem("SAMURAI_PET").getItem());
		lore.replaceAll(component -> {
			return (TextComponent) component.replaceText(TextReplacementConfig.builder().match("%food%").replacement(String.valueOf(AbstractPet.getFood(pet).get())).build());
		});
		FormatUtils.replaceAll(lore, "%max_food%", String.valueOf(this.getMaxFood()));
		FormatUtils.replaceAll(lore, "%favorite_food%", String.valueOf(this.getFood().getType()));
		meta.lore(lore);
		pet.setItemMeta(meta);
	}

	@Override
	public boolean onPetTrigger(ItemStack i, Player p, Inventory inventory) {
		return true;
	}

	@Override
	public Class<? extends AbstractPet> getIdentifier() {
		return SamuraiPet.class;
	}

}
