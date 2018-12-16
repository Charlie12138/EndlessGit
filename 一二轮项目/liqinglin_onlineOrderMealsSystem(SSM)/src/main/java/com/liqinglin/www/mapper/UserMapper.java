package com.liqinglin.www.mapper;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;

import java.util.List;

public interface UserMapper {
	/**
	 * 添加新注册用户
	 * @param user
	 * @return
	 */
	int addUser(User user);

	int addRole(int userId);

	/**
	 * 获取用户信息
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);

	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	User getUserById(int userId);

	/**
	 * 检查用户名是否已经注册
	 * @param username
	 * @return
	 */
	int usernameIsExist(String username);

	/**
	 * 登录
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * 检查权限
	 * @param user
	 * @return
	 */
	User checkRole(User user);

	User getRole(User user);


	/**
	 * 得到用户所拥有的店铺
	 * @param store
	 * @return
	 */
	List<Store> getUserStore(Store store);

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	int modifyUserInfo(User user);

	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	int modifyPassword(User user);

}
