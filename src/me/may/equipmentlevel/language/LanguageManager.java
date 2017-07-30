package me.may.equipmentlevel.language;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;

public class LanguageManager {
	
	private Map<String, String> map = new HashMap<String, String>();
	
	public LanguageManager(FileConfiguration config) {
		map.put("IfSenderIsNotPlayer", config.getString("Tips.IfSenderIsNotPlayer").replaceAll("&", "§"));
		map.put("ArgsTooLong", config.getString("Tips.ArgsTooLong").replaceAll("&", "§"));
		map.put("PlayerIsNotOnline", config.getString("Tips.PlayerIsNotOnline").replaceAll("&", "§"));
		map.put("CheckPlayerSelfEquipmentLevel", config.getString("Tips.CheckPlayerSelfEquipmentLevel").replaceAll("&", "§"));
		map.put("CheckAnotherEquipMentLevel", config.getString("Tips.CheckAnotherEquipMentLevel").replaceAll("&", "§"));
	}
	
	/**
	 * 取Key值所对应的语言
	 * 
	 * @param key
	 *            关键字
	 * @return 语句
	 */
	public String getLanguage(String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		}else {
			return "";
		}
	}
}
