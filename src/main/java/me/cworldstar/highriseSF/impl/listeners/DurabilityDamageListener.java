package me.cworldstar.highriseSF.impl.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemDamageEvent;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.highriseSF.impl.handlers.DurabilityLossHandler;

/**
 * 
 * This {@link AbstractListener} is what makes the {@link DurabilityLossHandler} work.
 * 
 * @author cworldstar
 * @date 7/4/2025
 */
public class DurabilityDamageListener extends AbstractListener {
	@EventHandler
	public void onDurabilityDamage(PlayerItemDamageEvent e) {
		SlimefunItem item = SlimefunItem.getByItem(e.getItem());
		if(item == null) {
			return;
		}
		item.callItemHandler(DurabilityLossHandler.class, handler -> handler.onDurabilityLoss(e, e.getPlayer(), e.getItem()));
	}
}
