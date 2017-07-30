package me.may.equipmentlevel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumUtils {
	
	/**
	 * 利用正则表达式判断字符串是否是数字
	 * 
	 * @param str
	 *            字符串
	 * @return 是否为数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
}
