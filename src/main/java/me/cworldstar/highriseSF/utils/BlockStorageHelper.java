package me.cworldstar.highriseSF.utils;

import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.block.Block;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class BlockStorageHelper {
	public static String stringFromBlockStorage(String bs, String pos) {
		return JsonParser.parseString(bs).getAsJsonObject().get(pos).getAsString();
	}
		
	public static int intFromJSON(String bs, String pos) {
		return JsonParser.parseString(bs).getAsJsonObject().get(pos).getAsInt();
	}
	
	public static double doubleFromJSON(String bs, String pos) {
		return JsonParser.parseString(bs).getAsJsonObject().get(pos).getAsDouble();
	}	
	public static JsonObject getBlockStorage(Block b) {
		return (JsonObject) JsonParser.parseString(BlockStorage.getBlockInfoAsJson(b));
	}
	
	public static JsonObject fromString(String s) {
		return (JsonObject) JsonParser.parseString(s);
	}
	
	/** 
	 * @param {@link String} blockstorage
	 * @return A {@link Map} containing every key and value of the given blockstorage.
	 */
	public static Map<String, String> asMap(String blockstorage) {
		JsonObject bs = fromString(blockstorage);
		return bs.asMap().entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), value-> value.getValue().getAsString()));
	}
}
