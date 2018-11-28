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
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ModifyPasswordHandler {

	@Autowired
	DataService datas;

	@Autowired
	UserService userService;

	@RequestMapping("/modifyPw")
	public String modifyPassword() {
		return "user/modifyPassword";
	}



	@RequestMapping(value = "/ModifyPasswordServlet", method = RequestMethod.POST)
	public String modifyPassword(HttpServletRequest request) {
		String password = request.getParameter("password");
		User user = (User)request.getSession().getAttribute("user");
		user.setPassword(password);
		if(datas.isRightPassword(password) == Contants.PASSWORD_FORMAT_RIGHT_CODE) {
			if(userService.modifyPassword(user) == Contants.SUCCESS_CODE) {
				request.setAttribute("message", Contants.SUCCESS);
				return "user/modifyPassword";
			}else {
				request.setAttribute("message", Contants.FAIL);
				return "user/modifyPassword";
			}
		}

		if (datas.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			return "user/modifyPassword";
		}
		return null;
	}
}
