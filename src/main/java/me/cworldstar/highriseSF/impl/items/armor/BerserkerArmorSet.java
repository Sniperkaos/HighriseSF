package me.cworldstar.highriseSF.impl.items.armor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.builders.LeatherArmorBuilder;
import me.cworldstar.highriseSF.impl.handlers.PlayerAttackHandler;
import me.cworldstar.highriseSF.utils.ParticleUtils;

public class BerserkerArmorSet extends AbstractArmorSet {
	
	private Map<Player, Boolean> equipped = new HashMap<Player, Boolean>();
	
	public BerserkerArmorSet() {
		super(HighriseSF.key("BERSERKER_ARMOR_SET"));
		
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_HELMET)
								.setColor(Color.GREEN)
								.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 7)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&c&lBerserker's Helmet")
								.setLore(new String[] {
										"&c&l&nBerserker Set Abilities:", 
										"&7 [&6Domination&7] - &7Deal up to an extra 100% damage based on missing health.", 
										"&7 [&6Blood Rite&7] - &7You will heal 5% of damage dealt.", 
										"",
										"&7[&4Fear Aura II&7] - &7Nearby enemies are given Slowness II."
										}
								)
								.get(), "BERSERKER_HELMET", RecipeType.NULL, null
						).setMaxDurability(750, true)
				);
		
		
		ArmorSetPiece piece = new ArmorSetPiece(Registry.getItemGroup("armor_group"),
				new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE)
				.setColor(Color.ORANGE)
				.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
				.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 7)
				.addFlag(ItemFlag.HIDE_UNBREAKABLE)
				.setName("&c&lBerserker's Chestplate")
				.setLore(new String[] {
						"&c&l&nBerserker Set Abilities:", 
						"&7 [&6Domination&7] - &7Deal up to an extra 100% damage based on missing health.", 
						"&7 [&6Blood Rite&7] - &7You will heal 5% of damage dealt.", 
						"",
						"&7[&4Fear Aura II&7] - &7Nearby enemies are given Slowness II."
						}
				)
				.get(), "BERSERKER_CHESTPLATE", RecipeType.NULL, null
		).setMaxDurability(800, true);
		
		piece.addItemHandler(new PlayerAttackHandler() {
			@Override
			public boolean onPlayerAttack(EntityDamageEvent e, Player player, @NotNull Entity entity) {
				if(BerserkerArmorSet.this.active(player)) {
					double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					double damageIncrease = (maxHealth - player.getHealth());
					e.setDamage(e.getDamage() + (e.getDamage() * (damageIncrease/maxHealth)));
					if(damageIncrease > 50) {
						Location entityloc = entity.getLocation();
						entityloc.getWorld().spawnParticle(Particle.DRIP_LAVA, 3, 3, 3, 8);
						entityloc.getWorld().playSound(entityloc, Sound.ENTITY_WARDEN_ATTACK_IMPACT, 1, (float) 0.1);
					}
					player.setHealth(Math.max(0, Math.min(maxHealth, player.getHealth() + (e.getDamage() * 0.05))));
				}
				return false;
			}
		});
		
		this.addArmorPiece(piece);
				
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_LEGGINGS)
								.setColor(Color.RED)
								.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 7)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&c&lBerserker's Leggings")
								.setLore(new String[] {
										"&c&l&nBerserker Set Abilities:", 
										"&7 [&6Domination&7] - &7Deal up to an extra 100% damage based on missing health.", 
										"&7 [&6Blood Rite&7] - &7You will heal 5% of damage dealt.", 
										"",
										"&7 [&4Fear Aura II&7] - &7Nearby enemies are given Slowness II."
										}
								)
								.get(), "BERSERKER_LEGGINGS", RecipeType.NULL, null
						).setMaxDurability(800, true)
				);
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_BOOTS)
								.setColor(Color.RED)
								.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 7)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&c&lBerserker's Boots")
								.setLore(new String[] {
										"&c&l&nBerserker Set Abilities:", 
										"&7 [&6Domination&7] - &7Deal up to an extra 100% damage based on missing health.", 
										"&7 [&6Blood Rite&7] - &7You will heal 5% of damage dealt.", 
										"",
										"&7 [&4Fear Aura II&7] - &7Nearby enemies are given Slowness II."
										}
								)
								.get(), "BERSERKER_BOOTS", RecipeType.NULL, null
						).setMaxDurability(750, true)
				);
	}

	@Override
	public Consumer<AbstractArmorSet> armorTick() {
		return (AbstractArmorSet set) -> {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(!equipped.containsKey(p)) {
					equipped.put(p, false);
				}
				
				boolean active = set.active(p);
				if(active) {
					this.equipped.put(p, true);
					if(HighriseSF.get().getLastSFTick() % 10 == 0) {
						ParticleUtils.spawnCircle(p.getLocation(), new DustOptions(Color.GREEN, 0.5F), 4F, 1, 90);
						for(Entity e : p.getLocation().getNearbyEntities(10, 10, 10)) {
							if(e instanceof LivingEntity) {
								LivingEntity entity = (LivingEntity) e;
								if((e.equals(p))) continue;
								if(!entity.hasPotionEffect(PotionEffectType.WEAKNESS)) {
									entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 1));
								}
							}
						}
					}
				} else {
					if(this.equipped.get(p) == true) {
						this.equipped.put(p, false);
					};
				}
			}
		};
	}

}
