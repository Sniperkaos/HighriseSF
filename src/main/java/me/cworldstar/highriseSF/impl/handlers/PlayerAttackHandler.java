package me.cworldstar.highriseSF.impl.handlers;

import javax.annotation.Nonnull;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import me.cworldstar.highriseSF.impl.listeners.PlayerAttackHandlerListener;

/**
 * 
 * The {@link PlayerAttackHandler} is called when and only when a {@link Player} attacks a {@link Entity}. 
 * If the item that owns this {@link ItemHandler} is in the {@link Player}'s inventory, it will be called.
 * Unlike {@link io.github.thebusybiscuit.slimefun4.core.handlers.WeaponUseHandler}, this triggers EVERY
 * SlimefunItem in the {@link Player}'s {@link Inventory}, rather than just the weapon used.
 * @author cworldstar
 *
 */
public abstract class PlayerAttackHandler implements ItemHandler {

	public static void registerListener() {
		new PlayerAttackHandlerListener();
	}
	
	/**
	 * @param e The {@link EntityDamageEvent} associated with this attack.
	 * @param entity The {@link Entity} being attacked.
	 * @param player The {@link Player} who is attacking.
	 * @return False if the event should be cancelled, True if not.
	 */
    public abstract boolean onPlayerAttack(@Nonnull EntityDamageEvent e, Player player, @NotNull Entity entity);
	
	@Override
	public Class<? extends ItemHandler> getIdentifier() {
		return PlayerAttackHandler.class;
	}
	
}
