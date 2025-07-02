package me.cworldstar.highriseSF.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.cworldstar.highriseSF.HighriseSF;
import me.cworldstar.highriseSF.impl.items.pets.AbstractPet;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.md_5.bungee.api.ChatColor;

public class FormatUtils {
	public static String formatString(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static Component formatAndCast(String s) {
		return Component.text(ChatColor.translateAlternateColorCodes('&', s));
	}
	
	public static List<TextComponent> getLore(ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		List<TextComponent> lore = meta.lore().stream().map(component->(TextComponent) component).collect(Collectors.toList());
		return lore;
	}
	
	public static TextComponent replace(TextComponent toReplace, String pattern, String newValue) {
		return (TextComponent) toReplace.replaceText(TextReplacementConfig.builder().match(pattern).replacement(newValue).build());
	}
	
	public static List<TextComponent> replaceAll(List<TextComponent> toReplace, String pattern, String newValue) {
		toReplace.replaceAll(textComponent -> {
			 return replace(textComponent, pattern, newValue);
		});
		return toReplace;
	}
	
	
	public static TextComponent asText(String s) {
		return Component.text(ChatColor.translateAlternateColorCodes('&', s));
	}
	
	public static String createBroadcast(String message) {
		return ChatColor.translateAlternateColorCodes('&', HighriseSF.getConfig("main").getString("options.name") + "&7: " + HighriseSF.getConfig("main").getString("options.message-color") + message);
	}
	
	public static TextComponent format(String s) {
		return asText(s);
	}
}
