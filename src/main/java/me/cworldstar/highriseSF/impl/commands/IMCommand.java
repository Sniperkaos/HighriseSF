package me.cworldstar.highriseSF.impl.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.cworldstar.highriseSF.HighriseSF;

public class IMCommand implements CommandExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
			@NotNull String[] args) {
		
		if(!(sender instanceof Player)) {
			return false;
		}
		
		Player player = (Player) sender;
		HighriseSF.log(player.getInventory().getItemInMainHand().getItemMeta().getAsString());
		
		return true;
	}

}
