package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.DataService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.serviceImp.DataServiceImp;
import com.liqinglin.www.serviceImp.UserServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserRegisterHandler {

	@Autowired
	DataService dataService;

	@Autowired
	UserService userService;

	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/userRegister";
	}

	@RequestMapping("/UserRegisterServlet")
	public String userRegister(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		/**
		 * 用户输入的信息进行检验
		 */
		if (dataService.isUserNameRight(username) == Contants.USERNAME_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.USERNAME_FORMAT_ERROR);
			return "user/userRegister";
		}

		if (dataService.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			return "user/userRegister";
		}

		if (dataService.isConfirmRight(password, repassword) == Contants.CHECK_PASSWORD_ERROR_CODE) {
			request.setAttribute("message", Contants.CHECK_PASSWORD_ERROR);
			return "user/userRegister";
		}

		/**
		 * 封装user信息
		 */
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		int result = userService.register(user);
		if (result == Contants.USERNAME_EXIST_CODE) {
			request.setAttribute("message", Contants.USERNAME_EXIST);
			return "user/userRegister";
		} else if(result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.WAIT_FOR_EXAMINEING);//提交注册成功等待审核
			return "user/userLogin";
		} else {
			request.setAttribute("message", Contants.FAIL);
			return "user/UserRegister";
		}
	}

}
