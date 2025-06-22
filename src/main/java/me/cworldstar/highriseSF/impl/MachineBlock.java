package me.cworldstar.highriseSF.impl;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.cworldstar.highriseSF.impl.abstracts.AbstractMachineBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;

public class MachineBlock extends AbstractMachineBlock {
	
	public MachineBlock(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, id, recipeType, recipe);
	}

	public MachineBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item.asOne(), item.getItemId(), recipeType, recipe);
	}

	@Override
	public EnergyNetComponentType getEnergyComponentType() {
		return EnergyNetComponentType.CONSUMER;
	}

	@Override
	public int getCapacity() {
		return 0;
	}

	@Override
	public boolean process(Block b, BlockMenu menu) {
		return false;
	}

	@Override
	public void powerLoss(Block b, BlockMenu menu) {
		
	}

	public void onSetup(BlockMenuPreset preset) {};
	
    @Override
	public void setup(BlockMenuPreset preset) {
        preset.drawBackground(OUTPUT_BORDER, new int[] {42});
        preset.drawBackground(INPUT_BORDER, new int[] {38});
        preset.addItem(getProcessSlot(), IDLE_ITEM, ChestMenuUtils.getEmptyClickHandler());
        onSetup(preset);
    }

	@Override
	public int[] getInputSlots() {
		return new int[0];
	}

	@Override
	public int[] getOutputSlots() {
		return new int[0];
	}

	@Override
	public String getMachineIdentifier() {
		return "UNSET";
	}

	@Override
	public int getProcessSlot() {
		return 0;
	}

}
