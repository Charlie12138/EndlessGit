package com.liqinglin.www.service;

import com.liqinglin.www.util.Contants;

/**
 * 用户注册数据逻辑判断
 */
public class DataService {
	/**
	 * 判断用户名格式是否正确
	 * 
	 * @param userName
	 * @return
	 */
	public int isUserNameRight(String username) {
		if (username == null || !username.matches("^1[34578]\\d{9}$")) {
			return Contants.USERNAME_FORMAT_ERROR_CODE;
		} else {
			return Contants.USERNAME_FORMAT_RIGHT_CODE;
		}
	}
	/**
	 * 判断用户输入的密码格式正确
	 * 
	 * @param password
	 * @return
	 */
	public int isRightPassword(String password) {
		if (password == null || !password.matches("^[0-9a-zA-Z]{6,16}$")) {
			return Contants.PASSWORD_FORMAT_ERROR_CODE;
		} else {
			return Contants.PASSWORD_FORMAT_RIGHT_CODE;
		}
	}

	/**
	 * 判断两次输入的密码是否一致
	 * 
	 * @param password
	 * @param repassword
	 * @return
	 */
	public int isConfirmRight(String password, String repassword) {
		if (password.equals(repassword)) {
			return Contants.CHECK_PASSWORD_RIGHT_CODE;
		} else {
			return Contants.CHECK_PASSWORD_ERROR_CODE;
		}
	}
	
	/**
	 * 判断角色radio有没有选择
	 */
	public boolean isSelected(String role) {
		if(null != role) {
			return false;
		}
		return true;
	}
}
