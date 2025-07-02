package me.cworldstar.highriseSF.impl.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.math.RandomUtils;
import me.cworldstar.highriseSF.HighriseSF;

public class BlockBreakListener implements Listener {
	
	private static final List<SlimefunItemStack> DROPPED_DUSTS = new ArrayList<SlimefunItemStack>();
	private static final List<Material> REQUIRED_BROKEN_BLOCKS = new ArrayList<Material>();
	
	public static void addDust(SlimefunItemStack dust) {
		DROPPED_DUSTS.add(dust);
	}
	
	public static void addDroppingBlock(Material blockMaterial) {
		REQUIRED_BROKEN_BLOCKS.add(blockMaterial);
	}
	
	public static void removeDroppingBlock(Material blockMaterial) {
		REQUIRED_BROKEN_BLOCKS.add(blockMaterial);
	}
	
	public static void removeDust(SlimefunItemStack dust) {
		DROPPED_DUSTS.remove(dust);
	}
	
	public static void register() {
		new BlockBreakListener();
	}
	
	public BlockBreakListener() {
		HighriseSF.get().getServer().getPluginManager().registerEvents(this, HighriseSF.get());
		DROPPED_DUSTS.addAll(List.of(
	      SlimefunItems.IRON_DUST,
	      SlimefunItems.GOLD_DUST,
	      SlimefunItems.COPPER_DUST,
	      SlimefunItems.TIN_DUST,
	      SlimefunItems.ZINC_DUST,
	      SlimefunItems.ALUMINUM_DUST,
	      SlimefunItems.MAGNESIUM_DUST,
	      SlimefunItems.LEAD_DUST,
	      SlimefunItems.SILVER_DUST
	    ));
		REQUIRED_BROKEN_BLOCKS.addAll(List.of(Material.STONE, Material.DEEPSLATE));
	}
	
	/**
	 * @return {@link Double} The multiplier the dust stack should have.
	 */
	private double handleFortune(int fortuneLevel) {
		return ((1 * fortuneLevel + 2 + fortuneLevel + 1) / 2);
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		int dustChanceIncrease = 0;
		Player p = e.getPlayer();
		@NotNull Collection<PotionEffect> effects = p.getActivePotionEffects();
		for(PotionEffect effect : effects) {
			if(effect.getType().equals(PotionEffectType.LUCK)) {
				dustChanceIncrease += effect.getAmplifier();
			} else if(effect.getType().equals(PotionEffectType.UNLUCK)) {
				dustChanceIncrease -= effect.getAmplifier();
			}
		}
		
		boolean shouldDropDust = RandomUtils.nextInt(10 + (dustChanceIncrease)) >= 10;
		@Nonnull SlimefunItemStack dustDrop = DROPPED_DUSTS.get(RandomUtils.nextInt(DROPPED_DUSTS.size()));
		if(!shouldDropDust) return;
		
		double drops = 1.0D;
		ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
		if(i.containsEnchantment(Enchantment.FORTUNE)) {
			double multiplier = handleFortune(i.getEnchantmentLevel(Enchantment.FORTUNE));
			drops *= multiplier;
		}
		
		Location loc = e.getBlock().getLocation();
		World world = loc.getWorld();
		world.spawnParticle(Particle.SCULK_SOUL, 0, 0, 0, 8);
		world.dropItem(loc, dustDrop.asQuantity((int) Math.round(drops)));
		
	}
}
