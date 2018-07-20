package com.liqinglin.www.dao;


import java.util.List;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.MybatisUtil;
import com.liqinglin.www.util.UserDaoContants;
import org.apache.ibatis.session.SqlSession;

public class UserDao {

	/**
	 * 添加新注册用户
	 * @param user
	 * @return
	 */
	public int addUser(User user) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.ADDUSER;
		int result = session.insert(statement, user);
		session.close();
		return result;
	}


	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	public User getUserInfo(String username) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.GETUSERINFOBYUSERNAME;
		User user = session.selectOne(statement, username);
		session.close();
		return user;
	}


	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public User getUserInfo(int userId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.GETUSERINFOBYID;
		User user = session.selectOne(statement, userId);
		session.close();
		return user;
	}

	/**
	 * 检查用户名是否已经注册
	 * @param username
	 * @return
	 */
	public int usernameIsExist(String username) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.USERNAMEISEXIST;
		int result = session.selectOne(statement, username);
		session.close();
		return result;
	}


	/**
	 * 登录
	 * @param user
	 * @return
	 */

	public int login(User user) {
		if (!checkRole(user)) {
			return Contants.ROLE_SELECT_ERROR_CODE;
		}
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.LOGIN;
		User user1 = session.selectOne(statement, user);
		session.close();
		if(user1 != null){
			if (user1.getStatus() == Contants.PASS_EXAMINE) {
				return Contants.SUCCESS_CODE;
			} else {
				return Contants.NOT_EXAMINE_CODE;
			}
		}
		return  Contants.FAIL_CODE;
	}


	/**
	 * 检查权限
	 * @param user
	 * @return
	 */

	public boolean checkRole(User user) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.CHECKROLE;
		UserService userService = new UserService();
		User user1 = session.selectOne(statement, user);
		statement = UserDaoContants.CHECKROLE2;
		User user2 = session.selectOne(statement, user1);
		boolean result = userService.checkRole(user2.getRole(),user.getRole());
		session.close();
		return result;
	}


	/**
	 * 用户开店信息录入数据库
	 * @param store
	 * @return
	 */

	public int openStore(Store store) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.OPENSTORE;
		User user = session.selectOne(statement, store.getUser().getUsername());
		store.setUser(user);
		statement = UserDaoContants.INSERTSTOREINFO;
		int result = session.insert(statement, store);
		session.close();
		return result;
	}


	/**
	 * 检查店铺名是否已经注册
	 * @param storeName
	 * @return
	 */
	public int storeNameIsExist(String storeName) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.STOREISEXIST;
		int result = session.selectOne(statement, storeName);
		session.close();
		return result;
	}


	/**
	 * 得到用户所拥有的店铺
	 * @param username
	 * @return
	 */
	public List<Store> getUserStore(String username) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.GETUSERINFOBYUSERNAME;
		User user = session.selectOne(statement, username);
		Store store = new Store();
		store.setUser(user);
		store.setStatus(Contants.PASS_EXAMINE);
		statement = UserDaoContants.GETUSERSTORE;
		List<Store> stores = session.selectList(statement, store);
		session.close();
		return stores;
	}


	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int modifyUserInfo(User user) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.MODIFYUSERINFO;
		int result = session.update(statement, user);
		session.close();
		return result;
	}


	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	public int modifyPassword(User user) {
		SqlSession session = MybatisUtil.getSession();
		String statement = UserDaoContants.MODIFYPASSWORD;
		int result = session.update(statement, user);
		session.close();
		return  result;
	}
}
