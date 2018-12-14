package com.rajnetwork.cusb.events;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.rajnetwork.cusb.Core;
import com.rajnetwork.cusb.managers.ScoreboardManager;

public class OnJoin implements Listener {
	
	Core plugin = Core.getInstance();
	ScoreboardManager sbm = new ScoreboardManager();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (sbm == null) {
			sbm = new ScoreboardManager();
		}
		Player p = e.getPlayer();
		String worldname = p.getLocation().getWorld().getName();
		for (String sbname : plugin.getConfig().getConfigurationSection("Scoreboards").getKeys(false)) {
			List<String> worlds = plugin.getConfig().getStringList("Scoreboards."+sbname+".worlds");
			if (worlds.contains(worldname)) {
				sbm.selectScoreboardOnReload(p, sbname);
			}
			continue;
		}
	}
}
