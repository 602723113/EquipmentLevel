package me.may.equipmentlevel.api;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.may.equipmentlevel.Entry;
import me.may.equipmentlevel.util.NMSUtils;
import me.may.equipmentlevel.util.NumUtils;

public class EquipmentLevelAPI {
	
	/**
	 * 检查物品是否拥有等级
	 * 
	 * @param item
	 *            物品
	 * @return true[有]/false[无]
	 */
	public static boolean hasLevel(ItemStack item) {
		if (item != null && !item.getType().equals(Material.AIR) && item.hasItemMeta() && item.getItemMeta().hasLore()) {
			//引用物品的Lore
			List<String> lore = item.getItemMeta().getLore();
			for (int i = 0; i < lore.size(); i++) {
				//检测有无Tag
				if (lore.get(i).indexOf(Entry.getInstance().levelTag) != -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 取武器的等级
	 * 
	 * @param item
	 *            武器
	 * @return 等级
	 */
	public static double getItemLevel(ItemStack item) {
		double level = 0D;
		if (hasLevel(item)) {
			List<String> lore = item.getItemMeta().getLore();
			//遍历Lore
			for (int i = 0; i < lore.size(); i++) {
				//例如 ---> 装备等级: 5
				if (lore.get(i).indexOf(Entry.getInstance().levelTag) != -1) {
					String temp = lore.get(i);
					//此处的replaceAll是为了去除空格
					String levelTemp = temp.split(":")[1].replaceAll(" ", "");
					//去除给定消息的所有颜色代码
					levelTemp = ChatColor.stripColor(levelTemp);
					//此处判断是否为数字
					if (!NumUtils.isNumeric(levelTemp)) {
						return level;
					}
					//字符串对象转换为Double类型的对象
					level = Double.valueOf(levelTemp);
				}
			}
		}
		return level;
	}
	
	/**
	 * 取玩家的所有装备等级
	 * 
	 * @param plyer
	 *            玩家
	 * @return 所有装备的等级
	 */
	public static double getPlayerItemsLevel(Player player) {
		double level = 0D;
		//先对其做NPE检查,还有在线检查
		if (player == null || !player.isOnline()) {
			return level;
		}
		//之后取身上的装备
		List<ItemStack> items = new ArrayList<ItemStack>();
		for (int i = 0; i < player.getEquipment().getArmorContents().length; i++) {
			items.add(player.getEquipment().getArmorContents()[i]);
		}
		
		//以下调用了NMSUtils中的getItemInMainHand()做多版本兼容
		items.add(NMSUtils.getItemInMainHand(player));
		
		for (int i = 0; i < items.size(); i++) {
			ItemStack itemStack = items.get(i);
			if (hasLevel(itemStack)) {
				level = level + getItemLevel(itemStack);
			}
		}
		return level;
		
	}
}
