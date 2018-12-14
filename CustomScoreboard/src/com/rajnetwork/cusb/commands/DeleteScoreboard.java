package com.rajnetwork.cusb.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class DeleteScoreboard implements CommandExecutor {
	
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
		if (cmd.getName().equalsIgnoreCase("csdelete")) {
			if (!p.hasPermission("customscoreboard.commands.delete")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 1) {
				String name = a[0];
				name.toLowerCase();
				if (!sbm.doesScoreboardExist(name)) {
					p.sendMessage(format(prefix+"&cThat scoreboard has already been deleted or hasn't been created!"));
					return true;
				}
				sbm.deleteScoreboard(p, name);
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
