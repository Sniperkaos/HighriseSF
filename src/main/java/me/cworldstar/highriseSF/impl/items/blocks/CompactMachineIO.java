package me.cworldstar.highriseSF.impl.items.blocks;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.cworldstar.highriseSF.impl.abstracts.AbstractTickingMenuBlock;
import me.cworldstar.highriseSF.impl.builders.ItemStackBuilder;
import me.cworldstar.highriseSF.impl.compactmachines.CompactMachineData;
import me.cworldstar.highriseSF.impl.compactmachines.CompactMachineHandler;
import me.cworldstar.highriseSF.utils.FormatUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class CompactMachineIO extends AbstractTickingMenuBlock  {
	
	private void start() {
		addItemHandler(new BlockPlaceHandler(false) {

			@Override
			public void onPlayerPlace(BlockPlaceEvent e) {
				if(!e.getBlock().getWorld().getName().contains("compact_machine")) {
					e.getPlayer().sendMessage(FormatUtils.formatAndCast("&6> This block can only be placed in a compact machine world."));
					e.setCancelled(true);
					return;
				}	
				
				CompactMachineData data = CompactMachineHandler.getData(e.getBlock().getWorld().getName());
				JsonObject element = JsonParser.parseString(BlockStorage.getBlockInfoAsJson(data.getBlock())).getAsJsonObject();
				JsonElement io = element.get("IO");
				if(!(io == null) && !io.isJsonNull() && io.getAsBoolean() == true) {
					e.getPlayer().sendMessage(FormatUtils.formatAndCast("&6> You already have an IO in this compact machine!"));
					e.setCancelled(true);
					return;
				}
				BlockStorage.addBlockInfo(data.getBlock(), "IO", Boolean.toString(true));
			}
		});
		
		addItemHandler(new BlockBreakHandler(false, false) {

			@Override
			public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
				CompactMachineData data = CompactMachineHandler.getData(e.getBlock().getWorld().getName());
				BlockStorage.addBlockInfo(data.getBlock(), "IO", Boolean.toString(false));
			}
			
		});
	}
	
	public CompactMachineIO(ItemGroup category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
		start();
	}

	public CompactMachineIO(ItemGroup defaultItemGroup, SlimefunItemStack compactMachineIoItem, RecipeType recipeType,
			ItemStack[] items) {
		super(defaultItemGroup, compactMachineIoItem.asOne(), compactMachineIoItem.getItemId(), recipeType, items);
		start();
	}

	@Override
	public void onNewInstance(BlockMenu menu, Block b) {
		CompactMachineData data = CompactMachineHandler.getData(b.getWorld().getName());
		ItemStack IOItem = new ItemStackBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("&6> Click to teleport!").setLore(new String[] {"", "&e<- Compact Machine Input", "&6Compact Machine Output ->"}).get();
		menu.replaceExistingItem(4, IOItem);
		menu.addMenuClickHandler(4, new MenuClickHandler() {
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				Location l = data.getBlock().getLocation();
				p.teleport(l.add(new Location(data.getWorld(), 0, 2, 0)));
				return false;
			}
		});
	}
	
	@Override
	public void tick(Block b, BlockMenu menu) {
		CompactMachineData data = CompactMachineHandler.getData(b.getWorld().getName());
		for(int slot : getOutputSlots()) {
			if(menu.getItemInSlot(slot) != null) {
				if(data.getMenu().fits(menu.getItemInSlot(slot), getInputSlots())) {
					data.getMenu().pushItem(menu.getItemInSlot(slot), getInputSlots());
					data.getMenu().consumeItem(slot, menu.getItemInSlot(slot).getAmount());
				}
			}
		}

		for(int slot : getInputSlots()) {
			if(data.getMenu().getItemInSlot(slot) != null) {
				if(menu.fits(data.getMenu().getItemInSlot(slot), getInputSlots())) {
					menu.pushItem(data.getMenu().getItemInSlot(slot), getInputSlots());
					data.getMenu().consumeItem(slot, data.getMenu().getItemInSlot(slot).getAmount());
				}
			}
		}
	}

	@Override
	public void setup(BlockMenuPreset preset) {
		preset.drawBackground(new int[] {0,8});
		preset.addItem(4, BACKGROUND_ITEM);
	}

	@Override
	public int[] getInputSlots() {
		return new int[] {1,2,3};
	}

	@Override
	public int[] getOutputSlots() {
		return new int[] {5,6,7};
	}
}
