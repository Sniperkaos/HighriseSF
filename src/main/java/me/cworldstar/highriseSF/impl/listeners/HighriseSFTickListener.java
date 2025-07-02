package me.cworldstar.highriseSF.impl.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.events.HighriseSFTickEvent;
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
	}
}
