package me.may.equipmentlevel.hook;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.clip.placeholderapi.external.EZPlaceholderHook;
import me.may.equipmentlevel.api.EquipmentLevelAPI;

public class PlaceHoderAPIHook extends EZPlaceholderHook{

	public PlaceHoderAPIHook(Plugin plugin) {
		super(plugin, "equip");
	}

	@Override
	public String onPlaceholderRequest(Player player, String identifier) {
		if (identifier.equalsIgnoreCase("level")){
			return String.valueOf(EquipmentLevelAPI.getPlayerItemsLevel(player));
		}
		return null;
	}

}
