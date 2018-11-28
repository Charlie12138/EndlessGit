package com.liqinglin.www.controller;


import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.serviceImp.AdminServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class DeleteCuisineHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/DeleteCuisineServlet", method = RequestMethod.POST)
	public String deleteCuisine(HttpServletRequest request, RedirectAttributes attributes) {
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		Store store= (Store)request.getSession().getAttribute("store");
		if(adminService.deleteCuisine(cuisineId) == Contants.SUCCESS_CODE) {
			attributes.addAttribute("storeId", store.getStoreId());
			attributes.addAttribute("pageNum", 1);
			attributes.addAttribute("jump", 2);
			return "redirect:ListCuisine";
		}
		return null;
	}
}
