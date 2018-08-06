package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExamineRegisterHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping("/ExamineRegisterServlet")
	public String examineRegister(HttpServletRequest request) {
		List<User> users = adminService.examineRegister();
		request.getSession().setAttribute("users", users);
		return "admin/examineRegister";
	}
}
