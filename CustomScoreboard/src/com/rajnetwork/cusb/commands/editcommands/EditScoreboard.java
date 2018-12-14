package com.rajnetwork.cusb.commands.editcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.HelpManager;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class EditScoreboard implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Messages.prefixes.command");
	HelpManager hm = new HelpManager();
	ScoreboardManager sbm = new ScoreboardManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("ceedit")) {
			if (!p.hasPermission("customscoreboard.editcommand.edit")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 1) {
				String sbname = a[0];
				if (!sbm.doesScoreboardExist(sbname)) {
					p.sendMessage(format(prefix+"&cThat scoreboard doesn't exist!"));
					return true;
				}
				hm.sendEditHelpSb(p, sbname);
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
