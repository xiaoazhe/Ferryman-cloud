package com.ferry.common.utils;


/**
 * 字符串工具类
 * @Author: 摆渡人
 * @Date: 2021/4/26
 */
public class StringUtils {

	/**
	 * 判空操作
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
	}

}
