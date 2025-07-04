package me.cworldstar.highriseSF.impl.items.armor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle.DustOptions;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.builders.LeatherArmorBuilder;
import me.cworldstar.highriseSF.utils.ParticleUtils;

public class NaturalArmorSet extends AbstractArmorSet {
	
	private Map<Player, Boolean> equipped = new HashMap<Player, Boolean>();
	
	public NaturalArmorSet() {
		super(HighriseSF.key("NATURAL_ARMOR_SET"));
		
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_HELMET)
								.setColor(Color.GREEN)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 5)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&a&lDruid's Helmet")
								.setLore(new String[] {
										"&a&n&lDruid Set Abilities:", 
										"&7[&2Nature Resonating&7] - &7Gain &aLife Boost III.", 
										"&7[&2Life Energy II&7] - &7Gain &aRegeneration II.", 
										"",
										"&7[&4Weakness Aura I&7] - &7Nearby enemies are given Weakness I."
										}
								)
								.get(), "DRUID_HELMET", RecipeType.NULL, null
						).setMaxDurability(600, true)
				);
		
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE)
								.setColor(Color.LIME)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 5)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&a&lDruid's Chestplate")
								.setLore(new String[] {
										"&a&n&lDruid Set Abilities:", 
										"&7[&2Nature Resonating&7] - &7Gain &aLife Boost III.", 
										"&7[&2Life Energy II&7] - &7Gain &aRegeneration II.", 
										"",
										"&7[&4Weakness Aura I&7] - &7Nearby enemies are given Weakness I."
										}
								)
								.get(), "DRUID_CHESTPLATE", RecipeType.NULL, null
						).setMaxDurability(600, true)
				);
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_LEGGINGS)
								.setColor(Color.GREEN)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 5)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&a&lDruid's Leggings")
								.setLore(new String[] {
										"&a&n&lDruid Set Abilities:", 
										"&7[&2Nature Resonating&7] - &7Gain &aLife Boost III.", 
										"&7[&2Life Energy II&7] - &7Gain &aRegeneration II.", 
										"",
										"&7[&4Weakness Aura I&7] - &7Nearby enemies are given Weakness I."
										}
								)
								.get(), "DRUID_LEGGINGS", RecipeType.NULL, null
						).setMaxDurability(600, true)
				);
		this.addArmorPiece(
				new ArmorSetPiece(Registry.getItemGroup("armor_group"),
							new LeatherArmorBuilder(Material.LEATHER_BOOTS)
								.setColor(Color.GRAY)
								.setAttributeValue(Attribute.GENERIC_ARMOR, "ARMOR_SET_ARMOR", 5)
								.addFlag(ItemFlag.HIDE_UNBREAKABLE)
								.setName("&a&lDruid's Boots")
								.setLore(new String[] {
										"&a&n&lDruid Set Abilities:", 
										"&7[&2Nature Resonating&7] - &7Gain &aLife Boost III.", 
										"&7[&2Life Energy II&7] - &7Gain &aRegeneration II.", 
										"",
										"&7[&4Weakness Aura I&7] - &7Nearby enemies within 10 blocks are given Weakness I."
										}
								)
								.get(), "DRUID_BOOTS", RecipeType.NULL, null
						).setMaxDurability(600, true)
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
									entity.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 50, 0));
								}
							}
						}
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 50, 1));
					if(!p.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 500000000, 2));
					}
				} else {
					if(this.equipped.get(p) == true) {
						this.equipped.put(p, false);
						p.removePotionEffect(PotionEffectType.REGENERATION);
						p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
					};
				}
			}
		};
	}

}
