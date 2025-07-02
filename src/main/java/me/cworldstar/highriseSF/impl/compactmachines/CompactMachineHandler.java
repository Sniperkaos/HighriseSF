package me.cworldstar.highriseSF.impl.compactmachines;

import java.util.HashMap;

public class CompactMachineHandler {
	
	private static HashMap<String, CompactMachineData> machines = new HashMap<String, CompactMachineData>();
	public static void registerCompactMachineAt(String machineWorldID, CompactMachineData d) {
		machines.put(machineWorldID, d);
	}
	public static CompactMachineData getData(String machineWorldID) {
		return machines.get(machineWorldID);
	}
	
}
