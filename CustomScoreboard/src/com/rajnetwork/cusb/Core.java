package com.rajnetwork.cusb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.rajnetwork.cusb.commands.CsAliasesHelp;
import com.rajnetwork.cusb.commands.CsHelp;
import com.rajnetwork.cusb.commands.DeleteScoreboard;
import com.rajnetwork.cusb.commands.DeselectScoreboard;
import com.rajnetwork.cusb.commands.NewScoreboard;
import com.rajnetwork.cusb.commands.ReloadConfig;
import com.rajnetwork.cusb.commands.SelectScoreboard;
import com.rajnetwork.cusb.commands.Version;
import com.rajnetwork.cusb.commands.editcommands.AddLine;
import com.rajnetwork.cusb.commands.editcommands.AddWorld;
import com.rajnetwork.cusb.commands.editcommands.ChangeLine;
import com.rajnetwork.cusb.commands.editcommands.DeleteLine;
import com.rajnetwork.cusb.commands.editcommands.EditAliasesHelp;
import com.rajnetwork.cusb.commands.editcommands.EditHelp;
import com.rajnetwork.cusb.commands.editcommands.EditLine;
import com.rajnetwork.cusb.commands.editcommands.EditScoreboard;
import com.rajnetwork.cusb.commands.editcommands.RemoveWorld;
import com.rajnetwork.cusb.commands.editcommands.SetTitle;
import com.rajnetwork.cusb.events.OnJoin;
import com.rajnetwork.cusb.events.OnMove;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class Core extends JavaPlugin {

	private static Core plugin;
	private static ScoreboardManager sbm;
	public ScoreboardManager sbmm = null;
	public File configFile;
	public ConsoleCommandSender cs = Bukkit.getServer().getConsoleSender();
	

	public void onEnable() {
		plugin = this;
		if (sbmm == null) {
			sbmm = new ScoreboardManager();
		}
		registerEvents();
		for (Player p : Bukkit.getOnlinePlayers()) {
			String worldname = p.getLocation().getWorld().getName();
			for (String sbname : getConfig().getConfigurationSection("Scoreboards").getKeys(false)) {
				List<String> worlds = getConfig().getStringList("Scoreboards."+sbname+".worlds");
				if (worlds.contains(worldname)) {
					sbmm.selectScoreboardOnReload(p, sbname);
				}
				continue;
			}
		}
	}

	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
		plugin = null;
	}

	public void registerEvents() {
		setupConfig();
		// TODO Normal Commands
		getCmds("csselect",new SelectScoreboard());
		getCmds("csnew",new NewScoreboard());
		getCmds("csdelete",new DeleteScoreboard());
		getCmds("csreload",new ReloadConfig());
		getCmds("csdeselect",new DeselectScoreboard());
		getCmds("csversion",new Version());
		getCmds("cshelp",new CsHelp());
		getCmds("cshelpaliases",new CsAliasesHelp());
		// TODO Edit Commands
		getCmds("cehelpedit",new EditHelp());
		getCmds("cehelpaliases",new EditAliasesHelp());
		getCmds("ceedit",new EditScoreboard());
		getCmds("cesettitle",new SetTitle());
		getCmds("cedelline",new DeleteLine());
		getCmds("ceaddline",new AddLine());
		getCmds("ceeditline",new EditLine());
		getCmds("cechangeline",new ChangeLine());
		getCmds("ceaddworld",new AddWorld());
		getCmds("ceremoveworld",new RemoveWorld());
		// TODO Events
		getLisn(new OnJoin(),this);
		getLisn(new OnMove(),this);
	}

	public void setupConfig() {
		if (!getDataFolder().exists()) {
			 getDataFolder().mkdirs();
		}
		configFile = new File(getDataFolder(), "config.yml");
		loadConfig(configFile,"config.yml");
	}
	public void loadConfig(File f, String filename) {
		InputStream in = getResource(filename);
	    if(f.exists()) {
	    	return;
	    }
	    try {
			Files.copy(in, f.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void getCmds(String command, CommandExecutor cmdclass) {
		getCommand(command).setExecutor(cmdclass);
		return;
	}

	public void getLisn(Listener l, Plugin p) {
		Bukkit.getServer().getPluginManager().registerEvents(l, p);
	}
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&',s);
	}
	public static ScoreboardManager getSbManager() {
		return sbm;
	}
	public static Core getInstance() {
		return plugin;
	}
}
