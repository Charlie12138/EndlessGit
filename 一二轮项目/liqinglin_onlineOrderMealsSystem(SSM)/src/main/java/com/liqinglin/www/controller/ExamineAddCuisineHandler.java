package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.serviceImp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ExamineAddCuisineHandler {

	@Autowired
	AdminService adminService;


	@RequestMapping(value ="/ExamineAddCuisinesServlet", method = RequestMethod.GET)
	public String examineAddCuisine(HttpServletRequest request) {
		List<Cuisine> cuisines = adminService.examineAddCuisines();
		request.getSession().setAttribute("cuisines", cuisines);
		return "admin/examineAddCuisine";
	}

}
