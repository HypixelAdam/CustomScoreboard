package com.rajnetwork.cusb.managers;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.rajnetwork.cusb.Core;

public class ScoreboardManager {
	
	String prefix = Core.getInstance().getConfig().getString("Messages.prefixes.scoreboard");
	Core plugin = Core.getInstance();
	
	// TODO Get Methods
	public void selectScoreboard(Player p, String scoreboardname) {
		String displayName = plugin.getConfig().getString("Scoreboards."+scoreboardname+".displayname");
		String displayslot = plugin.getConfig().getString("Scoreboards."+scoreboardname+".displayslot");
		String altds = displayslot.toLowerCase();
		String altdn = putTogetherDisplayName(displayName);
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sb.registerNewObjective("dummy", "dummy");
		o.setDisplayName(format(altdn));
		o.setDisplaySlot(DisplaySlot.valueOf(displayslot));
		for (String score : plugin.getConfig().getConfigurationSection("Scoreboards."+scoreboardname+".lines").getKeys(false)) {
			int id = Integer.parseInt(score);
			String text = plugin.getConfig().getString("Scoreboards."+scoreboardname+".lines."+id+".text");
			String alttx = putTogetherText(text);
			String addfunctions = addFunctions(p, alttx);
			o.getScore(format(addfunctions)).setScore(id);
		}
		if (p.hasPermission("customscoreboard.message.selected")) {
			p.sendMessage(format(prefix+"&aScoreboard &e"+scoreboardname+"&a, has been set to &e"+altds+"&a."));
		}
		p.setScoreboard(sb);
	}
	public void selectScoreboardOnReload(Player p, String scoreboardname) {
		String displayName = plugin.getConfig().getString("Scoreboards."+scoreboardname+".displayname");
		String displayslot = plugin.getConfig().getString("Scoreboards."+scoreboardname+".displayslot");
		String altdn = putTogetherDisplayName(displayName);
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sb.registerNewObjective("dummy", "dummy");
		o.setDisplayName(format(altdn));
		o.setDisplaySlot(DisplaySlot.valueOf(displayslot));
		for (String score : plugin.getConfig().getConfigurationSection("Scoreboards."+scoreboardname+".lines").getKeys(false)) {
			int id = Integer.parseInt(score);
			String text = plugin.getConfig().getString("Scoreboards."+scoreboardname+".lines."+id+".text");
			String alttx = putTogetherText(text);
			String addfunctions = addFunctions(p, alttx);
			o.getScore(format(addfunctions)).setScore(id);
		}
		p.setScoreboard(sb);
	}
	// TODO Set/Create Methods
	public void addWorld(Player p, String sbname, String world) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		if (doesScoreboardHaveWorld(sbname, world)) {
			p.sendMessage(format(prefix+"&cThat world has already been addded!"));
			return;
		}
		List<String> worlds = plugin.getConfig().getStringList("Scoreboards."+sbname+".worlds");
		worlds.add(world);
		plugin.getConfig().set("Scoreboards."+sbname+".worlds", worlds);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aWorld &e"+world+"&a has been added to scoreboard &e"+sbname+"&a."));
		selectScoreboard(p, sbname);
		return;
	}
	public void removeWorld(Player p, String sbname, String world) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		if (!doesScoreboardHaveWorld(sbname, world)) {
			p.sendMessage(format(prefix+"&cThat world hasn't been added or is already deleted!"));
			return;
		}
		List<String> worlds = plugin.getConfig().getStringList("Scoreboards."+sbname+".worlds");
		worlds.remove(world);
		plugin.getConfig().set("Scoreboards."+sbname+".worlds", worlds);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aWorld &e"+world+"&a has been removed from scoreboard &e"+sbname+"&a."));
		selectScoreboard(p, sbname);
		return;
	}
	public void changeLine(Player p, String name, String now, String changeto) {
		if (!doesScoreboardExist(name)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		if (!doesLineExist(name, now)) {
			p.sendMessage(format(prefix+"&cThat line doesn't exist on this scoreboard!"));
			return;
		}
		String nowtext = plugin.getConfig().getString("Scoreboards."+name+".lines."+now+".text");
		plugin.getConfig().set("Scoreboards."+name+".lines."+changeto+".text", nowtext);
		plugin.getConfig().set("Scoreboards."+name+".lines."+now, null);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aLine &e"+now+"&a has been changed to &e"+changeto+"&a."));
		selectScoreboard(p,name);
		return;
	}
	public void deleteLine(Player p, String sbname, String score) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		if (!doesLineExist(sbname, score)) {
			p.sendMessage(format(prefix+"&cThat line doesn't exist on this scoreboard!"));
			return;
		}
		plugin.getConfig().set("Scoreboards."+sbname+".lines."+score, null);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aLine &e"+score+"&a, has been deleted."));
		selectScoreboard(p,sbname);
		return;
	}
	public void editLine(Player p,String sbname, String score, String text) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		if (!doesLineExist(sbname, score)) {
			p.sendMessage(format(prefix+"&cThat line doesn't exist on this scoreboard!"));
			return;
		}
		String alttx = putTogetherText(text);
		plugin.getConfig().set("Scoreboards."+sbname+".lines."+score+".text", alttx);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aThe line has been edited!"));
		selectScoreboard(p,sbname);
		return;
	}
	
	public void editTitle(Player p, String sbname, String title) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		String displayname = plugin.getConfig().getString("Scoreboards."+sbname+".displayname");
		String altdn = putTogetherDisplayName(title);
		plugin.getConfig().set("Scoreboards."+sbname+".displayname", title);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aDisplay name has been switched from "+displayname+" &ato "+altdn+"&a."));
		selectScoreboard(p,sbname);
		return;
	}
	public void addLine(Player p, String sbname, String text) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		int altid = 0;
		for (String id : plugin.getConfig().getConfigurationSection("Scoreboards."+sbname+".lines").getKeys(false)) {
			if (altid == Integer.parseInt(id)) {
				altid++;
				continue;
			} else {
				return;
			}
		}
		plugin.getConfig().set("Scoreboards."+sbname+".lines."+altid+".text", text);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aThe text has been set to line &e"+altid+"&a."));
		selectScoreboard(p,sbname);
		return;
	}
	public void addLine(Player p, String sbname, String text, String score) {
		if (!doesScoreboardExist(sbname)) {
			p.sendMessage(format(prefix+"&cThis scoreboard doesn't exist!"));
			return;
		}
		if (doesLineExist(sbname, score)) {
			p.sendMessage(format(prefix+"&cThat line already exists, to edit a line do '&e/editline "+sbname+" "+score+" <text>&c'."));
			return;
		}
		plugin.getConfig().set("Scoreboards."+sbname+".lines."+score+".text", text);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&aThe text has been set to line &e"+score+"&a."));
		selectScoreboard(p,sbname);
		return;
	}
	public void createScoreboard(Player p, String scoreboardname, String displayName, String displaySlot, String firstLineText) {
		plugin.getConfig().set("Scoreboards."+scoreboardname+".displayname", displayName);
		plugin.getConfig().set("Scoreboards."+scoreboardname+".displayslot", displaySlot);
		plugin.getConfig().set("Scoreboards."+scoreboardname+".lines.0.text", firstLineText);
		plugin.getConfig().set("Scoreboards."+scoreboardname+".worlds", "");
		plugin.saveConfig();
		String altds = displaySlot.toLowerCase();
		String altdn = putTogetherDisplayName(displayName);
		p.sendMessage(format(prefix+"&aCreated scoreboard with name &e"+scoreboardname+"&a, Display Slot » &e"+altds+"&a, Display Name » &e"+altdn+"&a."));
		selectScoreboard(p,scoreboardname);
		return;
	}
	public void deselectScoreboard(Player p) {
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		return;
	}
	public void createScoreboard(Player p, String scoreboardname,String displayName, String displaySlot, String firstLineText, int score) {
		plugin.getConfig().set("Scoreboards."+scoreboardname+".displayname", displayName);
		plugin.getConfig().set("Scoreboards."+scoreboardname+".displayslot", displaySlot);
		plugin.getConfig().set("Scoreboards."+scoreboardname+".lines."+score+".text", firstLineText);
		plugin.getConfig().set("Scoreboards."+scoreboardname+".worlds", "");
		plugin.saveConfig();
		String altds = displaySlot.toLowerCase();
		String altdn = putTogetherDisplayName(displayName);
		p.sendMessage(format(prefix+"&aCreated scoreboard with name &e"+scoreboardname+"&a, Display Slot » &e"+altds+"&a, Display Name » &e"+altdn+"&a."));
		selectScoreboard(p,scoreboardname);
		return;
	}
	
	public void deleteScoreboard(Player p, String scoreboardname) {
		plugin.getConfig().set("Scoreboards."+scoreboardname, null);
		plugin.saveConfig();
		p.sendMessage(format(prefix+"&e"+scoreboardname+"&a, has been deleted!"));
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		return;
	}
	
	public boolean doesScoreboardExist(String scoreboardname) {
		if (plugin.getConfig().getString("Scoreboards."+scoreboardname) != null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean doesLineExist(String scoreboardname, String score) {
		if (plugin.getConfig().getString("Scoreboards."+scoreboardname+".lines."+score) != null) {
			return true;
		} else {
			return false;
		}
	}
	public boolean rightDisplaySlot(String displaySlot) {
		if (displaySlot.equals("belowname") || displaySlot.equals("sidebar") || displaySlot.equals("playerlist")) {
			return true;
		} else {
			return false;
		}
	}
	public String putTogetherDisplayName(String displayname) {
		if (!displayname.contains(":")) {
			return displayname;
		}
		String[] splitdn = displayname.split(":");
		String altdn = "";
		int size = splitdn.length;
		int id = 0;
		for (id = 0;id<size;id++) {
			if (id == size) {
				altdn += splitdn[id];
			} else {
				altdn += splitdn[id]+" ";
			}
		}
		return altdn.trim();
	}
	public boolean doesScoreboardHaveWorld(String sbname,String world) {
		List<String> worlds = plugin.getConfig().getStringList("Scoreboards."+sbname+".worlds");
		if (worlds.contains(world)) {
			return true;
		} else {
			return false;
		}
	}
	public String putTogetherText(String text) {
		if (text == null) {
			text = "";
		}
		if (!text.contains(":")) {
			return text;
		}
		String[] splittx = text.split(":");
		String alttx = "";
		int size = splittx.length;
		int id = 0;
		for (id = 0;id<size;id++) {
			if (id == size) {
				alttx += splittx[id];
			} else {
				alttx += splittx[id]+" ";
			}
		}
		return alttx.trim();
	}
	// TODO Add functions to scoreboard
	public String addFunctions(Player p, String text) {
		if (text.contains("%player%")) {
			text = text.replace("%player%", p.getName());
		}
		if (text.contains("%location%")) {
			Location loc = p.getLocation();
			int x = loc.getBlockX();
			int y = loc.getBlockY();
			int z = loc.getBlockZ();
			String w = loc.getWorld().getName();
			text = text.replace("%location%", String.format(plugin.getConfig().getString("Messages.locationformat"), x,y,z,w));
		}
		return text;
	}
	//
	
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&',s);
	}
	
	
	

	
}
