package me.may.equipmentlevel;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.may.equipmentlevel.command.EquipmentLevelCommand;
import me.may.equipmentlevel.hook.PlaceHoderAPIHook;
import me.may.equipmentlevel.language.LanguageManager;

public class Entry extends JavaPlugin {
	
	private LanguageManager manager;
	private static Entry instance;
	public String levelTag;
	
	@Override
	public void onEnable() {
		instance = this;
		//检测config.yml
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		//注册指令
		Bukkit.getPluginCommand("el").setExecutor(new EquipmentLevelCommand());
		//检测Tag丢入内存中
		levelTag = getConfig().getString("Equipment.Tag").replaceAll("&", "§");
		
		manager = new LanguageManager(getConfig());
		
		//挂钩PlaceholderAPI
		if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null){
			new PlaceHoderAPIHook(instance).hook();
		}
	}
	
	/**
	 * 获取主类实例
	 * 
	 * @return {@link Entry}
	 */
	public static Entry getInstance() {
		return instance;
	}

	/**
	 * 取语言管理工具
	 * @return {@link LanguageManager}
	 */
	public LanguageManager getLanguageManager() {
		return manager;
	}
}
