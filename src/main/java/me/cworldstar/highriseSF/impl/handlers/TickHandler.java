package me.cworldstar.highriseSF.impl.handlers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;


/**
 * This {@link ItemHandler} is called when the HighriseSF ticker is ticked.
 * @see me.cworldstar.highriseSF.impl.listeners.HighriseSFTickListener
 * @author cworldstar
 *
 */
public abstract class TickHandler implements ItemHandler {

	public abstract void onTick(SlimefunItem thisItem, Player p, ItemStack item);
	
	@Override
	public Class<? extends ItemHandler> getIdentifier() {
		return TickHandler.class;
	}
	
}
