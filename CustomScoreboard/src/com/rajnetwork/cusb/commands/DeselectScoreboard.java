package com.rajnetwork.cusb.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class DeselectScoreboard implements CommandExecutor {

	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Messages.prefixes.command");
	ScoreboardManager sbm = new ScoreboardManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("csdeselect")) {
			if (!p.hasPermission("customscoreboard.command.deselect")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				sbm.deselectScoreboard(p);
				p.sendMessage(format(prefix+"&aScoreboard deselected."));
				return true;
			} else if (length == 1) {
				String player = a[0];
				@SuppressWarnings("deprecation")
				Player bplayer = Bukkit.getPlayer(player);
				if (bplayer.isOnline()) {
					p.sendMessage(format(prefix+"&aPlayer &e"+bplayer.getName()+"&a's scoreboard has been reset"));
					sbm.deselectScoreboard(bplayer);
					return true;
				}
				p.sendMessage(format(prefix+"&cThat player isn't online!"));
				return true;
			} else if (length >= 2) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.toomanyargs")));
				return true;
			}
		}
		return true;
	}

	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
