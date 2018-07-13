package com.liqinglin.www.service;

import com.liqinglin.www.dao.UserDao;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;
import com.liqinglin.www.util.ValidateUtil;

public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * 学生登录
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Msg studentLogin(String userName, String passWord) {
		if (ValidateUtil.isInvalidString(userName)) { // 判断用户名是否为空
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(passWord)) { // 判断密码是否为空
			return new Msg(Constants.NULL, null);
		}
		Msg msg = userDao.studentLogin(userName);
		if (msg.getResult().equals(passWord)) {
			return new Msg(Constants.LOGINSUCCEED, null);
		} else if (msg.getResult().equals(Constants.NOUSER)) {
			return msg;
		}
		return new Msg(Constants.PASSWORDERROR, null);
	}

	/**
	 * 教师登录
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	public Msg teacherLogin(String userName, String passWord) throws Exception {
		if (ValidateUtil.isInvalidString(userName)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(passWord)) { // 判断密码是否为空
			return new Msg(Constants.NULL, null);
		}
		Msg msg = userDao.teacherLogin(userName);
		if (msg.getResult().equals(passWord)) {
			return new Msg(Constants.LOGINSUCCEED, null);
		} else if (msg.getResult().equals(Constants.NOUSER)) {
			return msg;
		}
		return new Msg(Constants.PASSWORDERROR, null);
	}
}
