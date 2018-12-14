package com.rajnetwork.cusb.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class NewScoreboard implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Messages.prefixes.command");
	ScoreboardManager sbm = new ScoreboardManager();
	// /csnew <name> <displayname> <displayslot> <firstlinetext> [numberonscoreboard]
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("csnew")) {
			if (!p.hasPermission("customscoreboard.commands.new")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 1) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 2) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 3) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 4) {
				String name = a[0];
				String displayname = a[1];
				String displayslot = a[2];
				String text = a[3];
				String altname = name.toLowerCase();
				String altds = displayslot.toUpperCase();
				if (!sbm.rightDisplaySlot(displayslot)) {
					p.sendMessage(format(prefix+"&e"+displayslot+"&c, is not the correct display slot!"));
					p.sendMessage(format(prefix+"&cDisplay slots are: &esidebar&c,&ebelowname&c,&eplayerlist"));
					return true;
				}
				if (sbm.doesScoreboardExist(altname)) {
					p.sendMessage(format(prefix+"&cThere is already a scoreboard by &e"+altname+"&7."));
					return true;
				}
				if (displayname.length() >= 33) {
					p.sendMessage(format(prefix+"&cTitle can't exceed 32 characters!"));
					return true;
				}
				if (text.length() >= 41) {
					p.sendMessage(format(prefix+"&cText can't exceed 40 characters!"));
					return true;
				}
				sbm.createScoreboard(p, altname, displayname, altds, text);
				return true;
			} else if (length == 5) {
				String name = a[0];
				String displayname = a[1];
				String displayslot = a[2];
				String text = a[3];
				int score = Integer.parseInt(a[4]);
				String altname = name.toLowerCase();
				String altds = displayslot.toUpperCase();
				if (!sbm.rightDisplaySlot(displayslot)) {
					p.sendMessage(format(prefix+"&e"+displayslot+"&c, is not the correct display slot!"));
					p.sendMessage(format(prefix+"&cDisplay slots are: &esidebar&c,&ebelowname&c,&eplayerlist"));
					return true;
				}
				if (sbm.doesScoreboardExist(altname)) {
					p.sendMessage(format(prefix+"&cThere is already a scoreboard by &e"+altname+"&7."));
					return true;
				}
				sbm.createScoreboard(p, altname, displayname, altds, text, score);
				return true;
			} else if (length >= 6) {
				p.sendMessage(format(prefix+Core.getInstance().getConfig().getString("Messages.errors.command.toomanyargs")));
				return true;
			}
		}
		return true;
	}

	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
