package com.rajnetwork.cusb.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class SelectScoreboard implements CommandExecutor {
	
	Core plugin = Core.getInstance();
	String prefix = Core.getInstance().getConfig().getString("Messages.prefixes.command");
	ScoreboardManager sbm = new ScoreboardManager();

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] a) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a 'Player' to run this command!");
			return true;
		}
		Player p = (Player) sender;
		int length = a.length;
		if (cmd.getName().equalsIgnoreCase("csselect")) {
			if (!p.hasPermission("customscoreboard.command.select")) {
				p.sendMessage(format(prefix + "&cYou don't have permission to run this command."));
				return true;
			}
			if (length == 0) {
				p.sendMessage(format(prefix+plugin.getConfig().getString("Messages.errors.command.missingargs")));
				return true;
			} else if (length == 1) {
				String name = a[0];
				if (!sbm.doesScoreboardExist(name)) {
					p.sendMessage(format(prefix+"&cThat scoreboard doesn't exist!"));
					return true;
				}
				sbm.selectScoreboard(p, name);
				return true;
			} else if (length == 2) {
				String name = a[0];
				String option = a[1];
				if (!sbm.doesScoreboardExist(name)) {
					p.sendMessage(format(prefix+"&cThat scoreboard doesn't exist!"));
					return true;
				}
				if (option.equalsIgnoreCase("all")) {
					for (Player oplayer : Bukkit.getOnlinePlayers()) {
						sbm.selectScoreboardOnReload(oplayer, name);
					}
					p.sendMessage(format(prefix+"&aAll players scoreboards have been set to &e"+name+
							" &ain display slot &e"+plugin.getConfig().getString("Scoreboards."+name+".displayslot")+"&a."));
					return true;
				}
				Player player = Bukkit.getPlayer(option);
				if (player.isOnline()) {
					sbm.selectScoreboard(player, name);
				} else {
			      p.sendMessage(format(prefix+"&cThat player isn't online!"));
				  return true;
				}
			} else if (length >= 3) {
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
