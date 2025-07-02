package me.cworldstar.highriseSF.impl.compactmachines;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.Registry;
import me.cworldstar.highriseSF.utils.BlockHelper;

public class CompactMachineWorldBuilder {
		
	public CompactMachineWorldBuilder() {
		File file =  new File(HighriseSF.get().getDataFolder() + "/compact_machine_worlds");
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	public static World createCompactMachineWorld(Player who, String uuid, int machine_size) {		
		WorldCreator creator = new WorldCreator(uuid);
		creator.generator(new VoidChunkGenerator());
		creator.generateStructures(false);
	
		//-- create a world and set the world border
		World w = creator.createWorld();
		w.getWorldBorder().setCenter(new Location(w,0,60,0));
		w.getWorldBorder().setSize((machine_size * 2));
		w.setSpawnLocation(0, 62, 0);
		//-- create a bedrock cube
		StructureBuilder b = new StructureBuilder();
			b.at(new Location(w, 0, 60, 0));
			b.setSize(machine_size);
			b.withBlock(Material.BEDROCK);
			b.build();
		
		
			
		//-- place the IO
		BlockHelper.placeSlimefunBlock(who, Registry.getRegistryItem("HRSF_COMPACT_MACHINE_IO"), w.getBlockAt(0, 60, 0));
			
		return w;
	}
}
