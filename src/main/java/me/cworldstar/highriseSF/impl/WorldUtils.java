package me.cworldstar.highriseSF.impl;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldUtils {
	public static World loadWorld(String worldName) {
		World world = Bukkit.getWorld(worldName);
		if(world == null) {
			world = Bukkit.createWorld(new WorldCreator(worldName));
		}
		return world;
	}
}
