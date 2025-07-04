package me.cworldstar.highriseSF.impl.listeners;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.event.Listener;

import me.cworldstar.highriseSF.HighriseSF;

public abstract class AbstractListener implements Listener {
	
	public AbstractListener() {
		HighriseSF.get().getServer().getPluginManager().registerEvents(this, HighriseSF.get());
	}
	
	public static void register(Class<? extends AbstractListener> clazz) {
		try {
			clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
