package me.cworldstar.highriseSF.impl.sf.items;

import org.bukkit.Color;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.MachineBlock;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.impl.items.HighriseSFItems;
import me.cworldstar.highriseSF.utils.FormatUtils;
import me.cworldstar.highriseSF.utils.ParticleUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import net.kyori.adventure.text.Component;

public class TestItem extends MachineBlock {
	
	public TestItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		
		setPowerLossPerTick(1);
		setPowerLossPerProcess(104);
	}
	
	@Override
	public int getCapacity() {
		return 12800;
	}
	
	@Override 
	public ItemStack getProcessingItem(Block b) {
		String blockStorageInfo = BlockStorage.getBlockInfoAsJson(b);
		JsonObject toJSON = JsonParser.parseString(blockStorageInfo).getAsJsonObject();
		int process_time = toJSON.get("HRSF_process").getAsInt();
		
		ItemStack item = PROCESSING_ITEM;
		ItemMeta meta = item.getItemMeta();
		meta.customName(Component.text(FormatUtils.formatString("&aProcessing...&r &a&l" + String.valueOf(process_time) + "&7/&c&l1024")));
		item.setItemMeta(meta);
		
		return item;
	}
	
	@Override
	public boolean process(Block b, BlockMenu menu) {
		
		if(!(HighriseSF.SFTickCount() % 3 == 0)) {
			return true;
		}
		
		String blockStorageInfo = BlockStorage.getBlockInfoAsJson(b);
		JsonObject toJSON = JsonParser.parseString(blockStorageInfo).getAsJsonObject();
		if(toJSON.get("HRSF_process") == null) {
			BlockStorage.addBlockInfo(b, "HRSF_process", "0");
			blockStorageInfo = BlockStorage.getBlockInfoAsJson(b);
			toJSON = JsonParser.parseString(blockStorageInfo).getAsJsonObject();
		}
		
		if(getCharge(b.getLocation()) < this.getPowerLossPerProcess()) {
			return false;
		}
		
		int process_time = toJSON.get("HRSF_process").getAsInt();
		
		if(process_time >= 1024) {
			menu.pushItem(Registry.getRegistryItem("HRSF_UU_MATTER").getItem(), getOutputSlots());
			new BukkitRunnable() {
				@Override
				public void run() {
					ParticleUtils.spawnCircle(b.getLocation(), new DustOptions(Color.FUCHSIA, 3F), 1.0D, 2, 90);
					b.getWorld().playSound(b.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
				}
			}.runTask(HighriseSF.get());
			BlockStorage.addBlockInfo(b, "HRSF_process", "0");
		} else {
			new BukkitRunnable() {
				@Override
				public void run() {
					ParticleUtils.spawnCircle(b.getLocation(), new DustOptions(Color.PURPLE, 0.55F), 1.0D, 1, 90);
					b.getWorld().playSound(b.getLocation(), Sound.BLOCK_ANVIL_PLACE, 0.1f, 0.2f);
					b.getWorld().playSound(b.getLocation(), Sound.BLOCK_AMETHYST_CLUSTER_FALL, 1f, 1f);
				}
			}.runTask(HighriseSF.get());
			BlockStorage.addBlockInfo(b, "HRSF_process", String.valueOf(process_time + 1));
		}

		return true;
	}

	@Override
	public void powerLoss(Block b, BlockMenu menu) {
		
		if(!(HighriseSF.SFTickCount() % 3 == 0)) {
			return;
		}
		
		ParticleUtils.spawnCircle(
			b.getLocation().add(0d, 0.5d, 0d),
			new DustOptions(Color.RED, 1F),
			1D,
			1,
			90
		);
	}

	@Override
	public void setup(BlockMenuPreset preset) {
		preset.drawBackground(new int[] {
				0,1,2,3,4,5,6,7,8,
				9,10,11,15,16,17,
				18,19,20,22,24,25,26,
				27,28,29,33,34,35,
				36,37,38,39,40,41,42,43,44
		});
	}
	
	@Override
	public int getProcessSlot() {
		return 22;
	}

	@Override
	public int[] getInputSlots() {
		return new int[] {};
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] {
				12,13,14,
				21,23,
				30,31,32,
		};
	}
}
