package me.cworldstar.highriseSF.impl.events;

import javax.annotation.Nonnull;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class HighriseSFRegistryPreFinalizeEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	
	public static @Nonnull HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public @NotNull HandlerList getHandlers() {
		return getHandlerList();
	}
	
	public HighriseSFRegistryPreFinalizeEvent() {
		
	}
}
