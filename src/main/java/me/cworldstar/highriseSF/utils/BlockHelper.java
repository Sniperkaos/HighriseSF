package me.cworldstar.highriseSF.utils;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.EquipmentSlot;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class BlockHelper {
	
    @ParametersAreNonnullByDefault
    public static void placeSlimefunBlock(Player whoPlaced, SlimefunItem sfItem, Block block) {
    	BlockState state = block.getState();
    	block.setType(sfItem.getItem().getType());
        BlockStorage.store(block, sfItem.getId());

        sfItem.callItemHandler(BlockPlaceHandler.class, (handler -> {
        	handler.onPlayerPlace(new BlockPlaceEvent(block,state,block,sfItem.getItem(), whoPlaced, true, EquipmentSlot.HAND));
        }));
    }
}
