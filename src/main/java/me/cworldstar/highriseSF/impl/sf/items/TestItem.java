package me.cworldstar.highriseSF.impl.sf.items;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.HologramOwner;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.MachineBlock;
import me.cworldstar.highriseSF.utils.ParticleUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class TestItem extends MachineBlock implements HologramOwner  {
	
	public TestItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		
		setPowerLossPerTick(1);
		setPowerLossPerProcess(1024);
		
		addItemHandler(new BlockPlaceHandler(false) {
			@Override
			public void onPlayerPlace(BlockPlaceEvent e) {
				TestItem.this.updateHologram(e.getBlock(), "Progress: 0/32");
			}
		});
		
		addItemHandler(new BlockBreakHandler(false, false) {
			@Override
			public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
				TestItem.this.removeHologram(e.getBlock());
			}
		});
	}
	
	@Override
	public int getCapacity() {
		return 12800;
	}
	
	@Override
	public boolean process(Block b, BlockMenu menu) {
		String blockStorageInfo = BlockStorage.getBlockInfoAsJson(b);
		JsonObject toJSON = JsonParser.parseString(blockStorageInfo).getAsJsonObject();
		if(toJSON.get("HRSF_process") == null) {
			BlockStorage.addBlockInfo(b, "HRSF_process", "0");
			blockStorageInfo = BlockStorage.getBlockInfoAsJson(b);
			toJSON = JsonParser.parseString(blockStorageInfo).getAsJsonObject();
		}
		
		if(getCharge(b.getLocation()) < this.getPowerLossPerProcess()) {
			this.updateHologram(b, "No power!");
			return false;
		}
		
		int process_time = toJSON.get("HRSF_process").getAsInt();
		this.updateHologram(b, "Progress: " + String.valueOf(process_time) + "/1024");
		if(process_time >= 1024) {
			menu.pushItem(new ItemStack(Material.DIAMOND, 1), getOutputSlots());
			BlockStorage.addBlockInfo(b, "HRSF_process", "0");
		} else {
			new BukkitRunnable() {
				@Override
				public void run() {
					b.getWorld().playSound(b.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.2f, 1.0f);
				}
			}.runTask(HighriseSF.get());
			BlockStorage.addBlockInfo(b, "HRSF_process", String.valueOf(process_time + 1));
		}

		return true;
	}

	@Override
	public void powerLoss(Block b, BlockMenu menu) {
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
				9,10,11,12,13,17,
				18,22,26,
				27,28,29,30,31,35,
				36,37,38,39,40,41,42,43,44
		});
	}
	
	@Override
	public int getProcessSlot() {
		return 22;
	}

	@Override
	public int[] getInputSlots() {
		return new int[] {
				19,20,21
		};
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] {
				14,15,16,
				23,24,25,
				32,33,34,
		};
	}
}
