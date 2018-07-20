package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;

/**
 * 拒绝上架食品的控制器
 */
@WebServlet("/RejectAddCuisinesServlet")
public class RejectAddCuisinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void rejectAdd(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		Cuisine cuisine = new Cuisine();
		cuisine.setId(Integer.parseInt(request.getParameter("cuisineId")));
		cuisine.setStatus(Contants.REJECT_EXAMINE);
		if(adminService.operationAdd(cuisine) == Contants.SUCCESS_CODE) {
			try {
				response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ExamineAddCuisinesServlet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rejectAdd(request, response);
	}

}
