package me.cworldstar.highriseSF.utils;

import me.cworldstar.highriseSF.HighriseSF;
import net.md_5.bungee.api.ChatColor;

public class FormatUtils {
	public static String formatString(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	public static String createBroadcast(String message) {
		return ChatColor.translateAlternateColorCodes('&', HighriseSF.getConfig("main").getString("options.name") + "&7: " + HighriseSF.getConfig("main").getString("options.message-color") + message);
	}
}
