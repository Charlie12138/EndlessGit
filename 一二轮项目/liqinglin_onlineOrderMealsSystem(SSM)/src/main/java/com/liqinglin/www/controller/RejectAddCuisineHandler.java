package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RejectAddCuisineHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/RejectAddCuisinesServlet", method = RequestMethod.POST)
	public String rejectAddCuisine(HttpServletRequest request) {
		Cuisine cuisine = new Cuisine();
		cuisine.setId(Integer.parseInt(request.getParameter("cuisineId")));
		cuisine.setStatus(Contants.REJECT_EXAMINE);
		if(adminService.operationAdd(cuisine) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineAddCuisinesServlet";
		}
		return null;
	}
}
