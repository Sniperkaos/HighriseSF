package me.cworldstar.highriseSF.impl.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.events.HighriseSFTickEvent;
import me.cworldstar.highriseSF.impl.handlers.TickHandler;
import me.cworldstar.highriseSF.impl.items.armor.AbstractArmorSet;

public class HighriseSFTickListener implements Listener {
	public HighriseSFTickListener() {
		HighriseSF.get().getServer().getPluginManager().registerEvents(this, HighriseSF.get());
	}
	
	public static void register() {
		new HighriseSFTickListener();
	}
	
	@EventHandler
	public void onHighriseSFTick(HighriseSFTickEvent e) {
		AbstractArmorSet.onHighriseTick();
		for(Player p : Bukkit.getOnlinePlayers()) {
			for(ItemStack item : p.getInventory().getContents()) {
				SlimefunItem sfItem = SlimefunItem.getByItem(item);
				if(sfItem != null) {
					sfItem.callItemHandler(TickHandler.class, handler -> handler.onTick(sfItem, p, item));
				}
			}
		}
	}
}
