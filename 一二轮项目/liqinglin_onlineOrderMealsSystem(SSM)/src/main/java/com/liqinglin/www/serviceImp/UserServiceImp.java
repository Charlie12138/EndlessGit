package com.liqinglin.www.serviceImp;

import com.liqinglin.www.mapper.UserMapper;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * SqlSessionDaoSupport是一个抽象的支持类，用来为你提供SqlSession。
 * 调用getSqlSession()方法你会得到一个SqlSessionTemplate，这然后可以用于执行SQL方法
 */
@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	UserMapper userMapper;

	/**
	 * 注册用户 逻辑判断
	 */
	@Override
	public int register(User user) {
		if (userMapper.usernameIsExist(user.getUsername()) != 0) {
			return Contants.USERNAME_EXIST_CODE; // 该用户已存在
		} else if (userMapper.addUser(user) == 1 && userMapper.addRole(user.getId()) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}

	/**
	 * 用户登录并与数据库对比
	 */
	@Override
	public int login(User user) {
		User user1 = userMapper.checkRole(user);
		User user2 = userMapper.getRole(user1);
		boolean result = checkRole(user2.getRole(), user.getRole());

		if (!result) {
			return Contants.ROLE_SELECT_ERROR_CODE;
		}

		if (userMapper.usernameIsExist(user.getUsername()) != 0) {
			user1 = userMapper.login(user);
			if(user1 != null){
				if (user1.getStatus() == Contants.PASS_EXAMINE) {
					return Contants.SUCCESS_CODE;
				} else {
					return Contants.NOT_EXAMINE_CODE;
				}
			}
			return  Contants.FAIL_CODE;
		} else {
			return Contants.USERNAME_NOT_EXIST_CODE; // 该用户不存在
		}
	}

	/**
	 * 检查身份
	 */
	@Override
	public boolean checkRole(int trueRole, int role) {
		if (trueRole == role ||
				(trueRole == Contants.ROLE_MERCHANT&&
						role == Contants.ROLE_USER)) {
			return true;
		}
		return false;
	}

	/**
	 * 获得用户名下的店铺
	 */
	@Override
	public List<Store> getUserStore(String username) {
		User user = userMapper.getUserByUsername(username);
		Store store = new Store();
		store.setUser(user);
		store.setStatus(Contants.PASS_EXAMINE);
		return userMapper.getUserStore(store);
	}

	/**
	 * 获得用户信息
	 */
	@Override
	public User getUserInfo1(String username) {
		return userMapper.getUserByUsername(username);
	}

	/**
	 * 获得用户信息
	 */
	@Override
	public User getUserInfo2(int userId) {
		return userMapper.getUserById(userId);
	}

	/**
	 * 用户修改个人信息
	 */
	@Override
	public int modifyUserInfo(User user) {
		if(userMapper.modifyUserInfo(user) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}

	/**
	 * 用户修改密码
	 */
	@Override
	public int modifyPassword(User user) {
		if(userMapper.modifyPassword(user) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
}
