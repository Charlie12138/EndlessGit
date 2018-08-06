package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.serviceImp.StoreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ModifyCuisineInfoHandler {

	@Autowired
	StoreService storeService;

	@RequestMapping(value = "/ModifyCuisinesInfoServlet", method = RequestMethod.POST)
	public String ModifyCuisineInfo(HttpServletRequest request) {
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		Cuisine cuisine = storeService.getCuisineInfo(cuisineId);
		request.getSession().setAttribute("cuisine", cuisine);
		return "store/modifyCuisineInfo";
	}
}
