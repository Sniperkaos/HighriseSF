package me.cworldstar.highriseSF.impl.presets;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.cworldstar.highriseSF.impl.abstracts.AbstractMenuBlock;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

@ParametersAreNonnullByDefault
public class MenuBlockPreset extends BlockMenuPreset {
    private final AbstractMenuBlock menuBlock;

    public MenuBlockPreset(AbstractMenuBlock menuBlock) {
        super(menuBlock.getId(), menuBlock.getItemName());
        this.menuBlock = menuBlock;
        menuBlock.setup(this);
    }

    @Override
    public void newInstance(BlockMenu menu, Block b) {
        menuBlock.onNewInstance(menu, b);
    }
    
	@Override
	public void init() {
		
	}

	@Override
	public boolean canOpen(Block b, Player p) {
		return Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK) && menuBlock.canUse(p, false);
	}

	@Override
	public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
		return menuBlock.getTransportSlots(menu, flow, item);
	}

	@Override
	public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
		return new int[0];
	}
}
