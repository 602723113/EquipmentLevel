package me.may.equipmentlevel.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.may.equipmentlevel.Entry;
import me.may.equipmentlevel.api.EquipmentLevelAPI;

public class EquipmentLevelCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("el")) {
			
			if (!(sender instanceof Player)) {
				sender.sendMessage(Entry.getInstance().getLanguageManager().getLanguage("IfSenderIsNotPlayer"));
				return true;
			}
			///el {name} 不填默认自己
			if (args.length > 1) {
				sender.sendMessage(Entry.getInstance().getLanguageManager().getLanguage("ArgsTooLong"));
				return true;
			}
			
			Player player = (Player) sender;
			
			//以下为无任何参数时player.getName()  EquipmentLevelAPI.getPlayerItemsLevel(player)
			if (args.length == 0) {
				player.sendMessage(Entry.getInstance().getLanguageManager().getLanguage("CheckPlayerSelfEquipmentLevel")
					.replaceAll("%player_name%", player.getName())
					.replaceAll("%level%", String.valueOf(EquipmentLevelAPI.getPlayerItemsLevel(player)))
				);
				return true;
			}
			
			//以下为有一个参数时
			String playerName = args[0];
			Player target = Bukkit.getPlayer(playerName);
			if (target == null) {
				player.sendMessage(Entry.getInstance().getLanguageManager().getLanguage("PlayerIsNotOnline"));
				return true;
			}
			player.sendMessage(Entry.getInstance().getLanguageManager().getLanguage("CheckAnotherEquipMentLevel")
				.replaceAll("%target_name%", playerName)
				.replaceAll("level", String.valueOf(EquipmentLevelAPI.getPlayerItemsLevel(target)))
			);
		}
		return false;
	}

}
