package com.rajnetwork.cusb.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.rajnetwork.cusb.Core;

public class HelpManager {
	
	Core plugin = Core.getInstance();
	
	public void sendNormalHelp(Player p) {
		p.sendMessage(format("&7&m---&6[&2Help for CustomScoreboards&6]&7&m---"));
		p.sendMessage(format("&2<> - Required, [] - Optional"));
		p.sendMessage(format("&2/cshelp - &eView help of this plugin."));
		p.sendMessage(format("&2/cehelpedit - &eView the edit scoreboard help."));
		p.sendMessage(format("&2/cshelpaliases - &eView the aliases (alternate) commands."));
		p.sendMessage(format("&2/csdelete <name> - &eDeletes a scoreboard."));
		p.sendMessage(format("&2/csnew <name> <displayname> <displayslot> <text> [score] - &eCreates a new scoreboard."));
		p.sendMessage(format("&2/csreload - &eReloads the config."));
		p.sendMessage(format("&2/csselect <name> [player/all] - &eSelects a scoreboard that is loaded in the config."));
		p.sendMessage(format("&2/csdeselect [player/all] - &eRemoves the active scoreboard."));
		p.sendMessage(format("&2/csversion - &eDisplays the version."));
		p.sendMessage(format("&7&m---&6[&2Help for CustomScoreboards&6]&7&m---"));
		return;
	}
	public void sendNormalAliasesHelp(Player p) {
		p.sendMessage(format("&7&m---&6[&2Aliases Help for CustomScoreboards&6]&7&m---"));
		p.sendMessage(format("&2<> - Required, [] - Optional"));
		p.sendMessage(format("&2/csh - &eView help of this plugin by page."));
		p.sendMessage(format("&2/cshe - &eView the edit scoreboard help."));
		p.sendMessage(format("&2/csha - &eView the aliases (alternate) commands."));
		p.sendMessage(format("&2/csd <name> - &eDeletes a scoreboard."));
		p.sendMessage(format("&2/csn <name> <displayname> <displayslot> <text> [score] - &eCreates a new scoreboard."));
		p.sendMessage(format("&2/csr - &eReloads the config."));
		p.sendMessage(format("&2/css <name> [player/all] - &eSelects a scoreboard that is loaded in the config."));
		p.sendMessage(format("&2/csd [player/all] - &eRemoves the active scoreboard."));
		p.sendMessage(format("&2/csv - &eDisplays the version."));
		p.sendMessage(format("&7&m---&6[&2Aliases Help for CustomScoreboards&6]&7&m---"));
		return;
	}
	public void sendEditHelp(Player p) {
		p.sendMessage(format("&7&m---&6[&2Edit help for CustomScoreboards&6]&7&m---"));
		p.sendMessage(format("&2<> - Required, [] - Optional"));
		p.sendMessage(format("&2/ceedit <scoreboard> - &eDisplays this help with your scoreboard of your choice"));
		p.sendMessage(format("&2/cehelpaliases - &eDisplays the aliases (alternate) edit commands."));
		p.sendMessage(format("&2/cesettitle <scoreboard> <newtitle> - &eEdit the title of your scoreboard."));
		p.sendMessage(format("&2/ceaddline <scoreboard> <text> [score] - &eAdd another line to your scoreboard."));
		p.sendMessage(format("&2/ceeditline <scoreboard> <score/line> <text> - &eEdit a line on your scoreboard."));
		p.sendMessage(format("&2/cedelline <scoreboard> <score/line> - &eDelete a line from your scoreboard."));
		p.sendMessage(format("&2/cechangeline <scoreboard> <linefrom> <lineto> - &eChanges the posistion of a line on your scoreboard."));
		p.sendMessage(format("&2/ceaddfunction <scoreboard> <function> <score/line> - &eAdds a function to your scoreboard."));
		p.sendMessage(format("&2/cedelfunction <scoreboard> <function> <score/line> - &eDeletes a function from your scoreboard."));
		p.sendMessage(format("&2/ceaddworld <scoreboard> <world> - &eAdds a world to the scoreboard to only be active in these worlds."));
		p.sendMessage(format("&2/ceremoveworld <scoreboard> <world> - &eRemoves a world from the scoreboard."));
		p.sendMessage(format("&2/ceworlds <scoreboard> - &eLists the worlds that it will be active in."));
		p.sendMessage(format("&2/cefunctions <scoreboard> - &eDisplays the functions in the scoreboard."));
		p.sendMessage(format("&7&m---&6[&2Edit help for CustomScoreboards&6]&7&m---"));
		return;
	}
	public void sendEditAliasesHelp(Player p) {
		p.sendMessage(format("&7&m---&6[&2Edit help for CustomScoreboards&6]&7&m---"));
		p.sendMessage(format("&2<> - Required, [] - Optional"));
		p.sendMessage(format("&2/cee <scoreboard> - &eDisplays this help with your scoreboard of your choice"));
		p.sendMessage(format("&2/ceha - &eDisplays the aliases (alternate) edit commands."));
		p.sendMessage(format("&2/cest <scoreboard> <newtitle> - &eEdit the title of your scoreboard."));
		p.sendMessage(format("&2/ceal <scoreboard> <text> [score] - &eAdd another line to your scoreboard."));
		p.sendMessage(format("&2/ceel <scoreboard> <score/line> <text> - &eEdit a line on your scoreboard."));
		p.sendMessage(format("&2/cedl <scoreboard> <score/line> - &eDelete a line from your scoreboard."));
		p.sendMessage(format("&2/cecl <scoreboard> <linefrom> <lineto> - &eChanges the posistion of a line on your scoreboard."));
		p.sendMessage(format("&2/ceaf <scoreboard> <function> <score/line> - &eAdds a function to your scoreboard."));
		p.sendMessage(format("&2/cedf <scoreboard> <function> - &eDeletes a function from your scoreboard."));
		p.sendMessage(format("&2/ceaw <scoreboard> <world> - &eAdds a world to the scoreboard to only be active in these worlds."));
		p.sendMessage(format("&2/cerw <scoreboard> <world> - &eRemoves a world from the scoreboard."));
		p.sendMessage(format("&2/cews <scoreboard> - &eLists the worlds that it will be active in."));
		p.sendMessage(format("&2/cefs <scoreboard> - &eDisplays the functions in the scoreboard."));
		p.sendMessage(format("&7&m---&6[&2Edit help for CustomScoreboards&6]&7&m---"));
		return;
	}
	public void sendEditHelpSb(Player p, String sbname) {
		p.sendMessage(format("&7&m---&6[&2Edit help for '"+sbname+"'&6]&7&m---"));
		p.sendMessage(format("&2<> - Required, [] - Optional"));
		p.sendMessage(format("&2/ceedit "+sbname+" - &eDisplays this help with your scoreboard of your choice"));
		p.sendMessage(format("&2/cesettitle "+sbname+" <newtitle> - &eEdit the title of your scoreboard."));
		p.sendMessage(format("&2/ceaddline "+sbname+" <text> - &eAdd another line to your scoreboard."));
		p.sendMessage(format("&2/ceeditline "+sbname+" <score/line> <text> - &eEdit a line on your scoreboard."));
		p.sendMessage(format("&2/cedelline "+sbname+" <score/line> - &eDelete a line from your scoreboard."));
		p.sendMessage(format("&2/ceaddfunction "+sbname+" <function> <score/line> - &eAdds a function to your scoreboard."));
		p.sendMessage(format("&2/cedelfunction "+sbname+" <function> - &eDeletes a function from your scoreboard."));
		p.sendMessage(format("&2/ceaddworld "+sbname+" <world> - &eAdds a world to the scoreboard to only be active in these worlds."));
		p.sendMessage(format("&2/ceremoveworld "+sbname+" <world> - &eRemoves a world from the scoreboard."));
		p.sendMessage(format("&2/ceworlds "+sbname+" - &eLists the worlds that it will be active in."));
		p.sendMessage(format("&2/cefunctions "+sbname+" - &eDisplays the functions in the scoreboard."));
		p.sendMessage(format("&7&m---&6[&2Edit help for "+sbname+"&6]&7&m---"));
	}
	
	public String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
