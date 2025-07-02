package me.cworldstar.highriseSF.impl.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.handlers.PlayerAttackHandler;
import me.cworldstar.highriseSF.impl.items.pets.AbstractPet;
/**
 * This will handle the PlayerAttackHandlers. Has special implementations
 * for pet logic.
 * 
 * @author cworldstar
 *
 */
public class PlayerAttackHandlerListener implements Listener {

	public PlayerAttackHandlerListener() {
		HighriseSF.get().getServer().getPluginManager().registerEvents(this, HighriseSF.get());
	}
	
	@EventHandler
	public void onPlayerAttack(EntityDamageByEntityEvent e) {
		Entity player = e.getDamager();
		if(player instanceof Player) {
			List<Class<? extends AbstractPet>> triggeredPets = new ArrayList<>();
			//-- this will prevent the pets from triggering twice from the same PlayerAttackHandler.
	        for(ItemStack item : ((Player) player).getInventory()) {
	        	if(SlimefunItem.getByItem(item) != null) {
	        		SlimefunItem sfitem = SlimefunItem.getByItem(item);
	        		boolean cancel = false;
	        		for(Class<? extends AbstractPet> claz : triggeredPets) {
	        			if(claz.isInstance(sfitem)) {
	        				cancel = true;
	        				break;
	        			}
	        		}
	        		
	        		if(cancel) {
	        			continue;
	        		}
	        		
	        		if(sfitem instanceof AbstractPet) {
	        			AbstractPet pet = (AbstractPet) sfitem;
						Class<? extends AbstractPet> clazz = pet.getIdentifier();
						triggeredPets.add(clazz);
	        		}
	        		
	        		sfitem.callItemHandler(PlayerAttackHandler.class, handler -> handler.onPlayerAttack(e, (Player) player, e.getEntity()));
	        	}
	        }
		}
	}
}
