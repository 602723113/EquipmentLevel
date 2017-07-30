package me.may.equipmentlevel.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NMSUtils {
	
	private static String version;
	static {
		//org.bukkit.craftbukkit.vX_XX_RX;
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
	
	
	/**
	 * 反射: 取玩家手中物品
	 * @param player 玩家
	 * @return 物品
	 */
	@SuppressWarnings("deprecation")
	public static ItemStack getItemInMainHand(Player player) {
		//判断是否为1.9+
		if (Bukkit.getBukkitVersion().split("-")[0].startsWith("1.9")) {
			try {
				//获取obc下的CraftInventoryPlayer
				Class<?> craftinventory = Class.forName("org.bukkit.craftbukkit." + getVersion() + ".inventory.CraftInventoryPlayer");
				//调用其方法getItemInMainHand
				return (ItemStack) craftinventory.getMethod("getItemInMainHand").invoke(player.getInventory());
			} catch (Exception e) {
				System.out.println("错误: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return player.getItemInHand();
	}
	
	/**
	 * 取服务器版本 如v1_10_R1
	 * 
	 * @return 服务器版本
	 */
	public static String getVersion() {
		return version;
	}
}
