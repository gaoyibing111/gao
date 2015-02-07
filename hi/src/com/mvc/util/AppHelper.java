package com.mvc.util;

public class AppHelper {
	
	/**
	 * 将密码加密
	 * @param passwordSource
	 * @return
	 */
	public static String encryptPassword(String passwordSource) {
		return EncryptUtils.encryptByMD5(passwordSource);
	}
}
