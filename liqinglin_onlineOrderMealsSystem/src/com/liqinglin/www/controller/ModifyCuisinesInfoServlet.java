package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.StoreService;

/**
 * 修改菜肴信息控制器
 */
@WebServlet("/ModifyCuisinesInfoServlet")
public class ModifyCuisinesInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		StoreService storeService = new StoreService();
		Cuisine cuisine = storeService.getCuisineInfo(cuisineId);
		request.getSession().setAttribute("cuisine", cuisine);	
		response.sendRedirect("/liqinglin_onlineOrderMealsSystem/store/modifyCuisineInfo.jsp");
	}

}
