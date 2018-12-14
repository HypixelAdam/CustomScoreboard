package com.rajnetwork.cusb.commands.editcommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.HelpManager;

public class EditAliasesHelp implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Messages.prefixes.command");
	HelpManager hm = new HelpManager();

	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("cehelpaliases")) {
			if (!p.hasPermission("customscoreboard.editcommand.aliasesedithelp")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				hm.sendEditAliasesHelp(p);
				return true;
			} else if (length >= 1) {
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
