package com.liqinglin.www.util;

public class ValidateUtil {
	/**
	 * 验证字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInvalidString(String str) {
		if (str == null || str.equals("")) {
			return true;
		}
		return false;
	}
}
