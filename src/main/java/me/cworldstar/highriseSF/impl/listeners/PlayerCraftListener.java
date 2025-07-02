package me.cworldstar.highriseSF.impl.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.github.thebusybiscuit.slimefun4.api.events.MultiBlockCraftEvent;
import io.github.thebusybiscuit.slimefun4.api.events.SlimefunItemSpawnEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.handlers.ItemCraftHandler;
import me.cworldstar.highriseSF.impl.handlers.NewInstanceHandler;

public class PlayerCraftListener implements Listener {
	
	public static void register() {
		new PlayerCraftListener();
	}
	
	public PlayerCraftListener() {
		HighriseSF.get().getServer().getPluginManager().registerEvents(this, HighriseSF.get());
	}
	
	@EventHandler
	public void onMultiBlockCraftEvent(MultiBlockCraftEvent e) {
		SlimefunItem item = SlimefunItem.getByItem(e.getOutput());
		if(item != null) {
			item.callItemHandler(NewInstanceHandler.class, handler -> handler.onNewInstance(e.getOutput()));
			item.callItemHandler(ItemCraftHandler.class, handler -> handler.onCraft(e, (Player) e.getPlayer(), e.getInput(), e.getOutput()));
		}
	}
	
	@EventHandler
	public void onSlimefunItemSpawnEvent(SlimefunItemSpawnEvent e) {
		SlimefunItem item = SlimefunItem.getByItem(e.getItemStack());
		if(item != null) {
			item.callItemHandler(NewInstanceHandler.class, handler -> handler.onNewInstance(e.getItemStack()));
		}
	}
	
}
