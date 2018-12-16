package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;

	/**
	 * 注册通过
	 * @param user 用户
	 * @return 审核注册
	 */
	@RequestMapping("/AgreeRegisterServlet")
	public String addRegister(User user ) {
		if (adminService.agreeRegister(user) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineRegisterServlet";
		}
		return null;
	}

	/**
	 * 审核注册
	 * @param request 请求
	 * @return 审核注册
	 */
	@RequestMapping("/ExamineRegisterServlet")
	public String examineRegister(HttpServletRequest request) {
		List<User> users = adminService.examineRegister();
		request.getSession().setAttribute("users", users);
		return "admin/examineRegister";
	}

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpSession session) {
		session.setAttribute("name", "LQL");
		return "index";
	}
}
