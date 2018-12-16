package com.liqinglin.www.service;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;

import java.util.List;

public interface UserService {

	int register(User user);

	int login(User user);

	boolean checkRole(int trueRole, int role);

	List<Store> getUserStore(String username);

	User getUserInfo1(String username);

	User getUserInfo2(int userId);

	int modifyUserInfo(User user);

	int modifyPassword(User user);


}
