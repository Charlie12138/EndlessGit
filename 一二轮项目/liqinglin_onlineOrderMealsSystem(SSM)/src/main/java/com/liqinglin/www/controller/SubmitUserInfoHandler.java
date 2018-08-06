package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.serviceImp.UserServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubmitUserInfoHandler {

	@Autowired
	UserService userService;

	@RequestMapping("/modifyUserInfo")
	public String modifyUserInfo() {
		return "user/modifyUserInfo";
	}

	@RequestMapping("/SubmitUserInfoServlet")
	public String submitUserInfo(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
		String realname = request.getParameter("realname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		user.setEmail(email);
		user.setPhone(phone);
		user.setRealname(realname);
		int result = userService.modifyUserInfo(user);
		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.MODIFY_STORE_SUCCEESS);// 提交审核成功
		}  else {
			request.setAttribute("message", Contants.MODIFY_STORE_FAIL);
		}
		return "/user/modifyUserInfo";
	}

}
