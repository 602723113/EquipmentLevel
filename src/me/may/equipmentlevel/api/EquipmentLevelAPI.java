package me.may.equipmentlevel.api;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.may.equipmentlevel.Entry;
import me.may.equipmentlevel.util.NMSUtils;

public class EquipmentLevelAPI {

	private static Pattern numberRegex = Pattern.compile("([\\+|\\-]?\\d+[\\.\\d+]*)");

	/**
	 * 检查物品是否拥有等级
	 * 
	 * @param item
	 *            物品
	 * @return true[有]/false[无]
	 */
	public static boolean hasLevel(ItemStack item) {
		if (item != null && !item.getType().equals(Material.AIR) && item.hasItemMeta()
				&& item.getItemMeta().hasLore()) {
			// 引用物品的Lore
			List<String> lore = item.getItemMeta().getLore();
			for (int i = 0; i < lore.size(); i++) {
				// 检测有无Tag
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

			// 遍历lore
			for (String lore : item.getItemMeta().getLore()) {
				// 删除颜色字符
				lore = ChatColor.stripColor(lore);
				// 包含等级关键字
				if (lore.contains(Entry.getInstance().levelTag)) {
					// 正则匹配数字
					Matcher matcher = numberRegex.matcher(lore);
					if (matcher.find()) {
						level = Double.valueOf(matcher.group());
					}
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
		// 先对其做NPE检查,还有在线检查
		if (player == null || !player.isOnline()) {
			return level;
		}
		// 之后取身上的装备
		List<ItemStack> items = new ArrayList<ItemStack>();
		for (int i = 0; i < player.getEquipment().getArmorContents().length; i++) {
			items.add(player.getEquipment().getArmorContents()[i]);
		}

		// 以下调用了NMSUtils中的getItemInMainHand()做多版本兼容
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
