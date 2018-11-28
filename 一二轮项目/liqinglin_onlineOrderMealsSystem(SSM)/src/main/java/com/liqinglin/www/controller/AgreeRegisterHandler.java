package com.liqinglin.www.controller;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class AgreeRegisterHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping("/AgreeRegisterServlet")
	public String addRegister(User user ) {
		if (adminService.agreeRegister(user) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineRegisterServlet";
		}
		return null;
	}
}
