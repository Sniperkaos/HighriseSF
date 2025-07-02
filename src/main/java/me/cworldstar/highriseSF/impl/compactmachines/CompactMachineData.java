package me.cworldstar.highriseSF.impl.compactmachines;

import org.bukkit.World;
import org.bukkit.block.Block;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public class CompactMachineData {

	private World w;
	private BlockMenu menu;
	private Block b;
	private String id;
	
	public CompactMachineData(World w, BlockMenu menu, Block b, String id) {
		this.w = w;
		this.menu = menu;
		this.b = b;
		this.id = id;
	}
	
	public CompactMachineData(BlockMenu menu, String id) {
		this.w = menu.getBlock().getWorld();
		this.menu = menu;
		this.b = menu.getBlock();
		this.id = id;
	}
	
	public World getWorld() {
		return w;
	}
	
	public BlockMenu getMenu() {
		return menu;
	}
	
	public Block getBlock() {
		return b;
	}
	
	public String getID() {
		return id;
	}
	
}
