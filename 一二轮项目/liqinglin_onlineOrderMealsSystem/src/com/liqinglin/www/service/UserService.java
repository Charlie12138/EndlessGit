package com.liqinglin.www.service;

import java.util.List;
import com.liqinglin.www.dao.UserDao;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.util.Contants;

/**
 * 用户逻辑
 */
public class UserService {
	/**
	 * 注册用户 逻辑判断
	 */
	UserDao userDao = new UserDao();

	public int register(User user) {

		if (userDao.usernameIsExist(user.getUsername())) {
			return Contants.USERNAME_EXIST_CODE; // 该用户已存在
		}

		if (userDao.addUser(user) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}

	/**
	 * 用户登录并与数据库对比
	 */
	public int login(User user) {
		int flag = Contants.FAIL_CODE;
		if (userDao.usernameIsExist(user.getUsername())) {
			flag = userDao.login(user);
		} else {
			flag = Contants.USERNAME_NOT_EXIST_CODE; // 该用户不存在
		}
		return flag;
	}

	/**
	 * 用户申请开店
	 */
	public int openStroe(Store store) {
		if (userDao.storeNameIsExist(store.getStoreName())) {
			return Contants.STORENAME_EXIST_CODE;
		} else if (userDao.openStore(store) == 1) {
			return Contants.SUCCESS_CODE; // 该用户不存在
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 获得用户名下的店铺
	 */
	public List<Store> getUserStore(String username){
		List<Store> stores = userDao.getUserStore(username);
		return stores;
	}
	
	

	/**
	 * 获得用户信息
	 */
	public User getUserInfo(String username) {
		return userDao.getUserInfo(username);
	}
	
	/**
	 * 获得用户信息
	 */
	public User getUserInfo(int userId) {
		return userDao.getUserInfo(userId);
	}
	
	/**
	 * 用户修改个人信息
	 */
	public int modifyUserInfo(User user) {
		if(userDao.modifyUserInfo(user)) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 用户修改密码
	 */
	public int modifyPassword(User user) {
		if(userDao.modifyPassword(user)) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	
	
	
	
	
	
}
