package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AgreeAddCuisineHandler {
	@Autowired
	AdminService adminService;

	@RequestMapping("/AgreeAddCuisinesServlet")
	public String agreeAddCuisine(Cuisine cuisine) {
			cuisine.setStatus(Contants.PASS_EXAMINE);
			if(adminService.operationAdd(cuisine) == Contants.SUCCESS_CODE) {
				return "redirect:ExamineAddCuisinesServlet";
			}
		return null;
	}
}
