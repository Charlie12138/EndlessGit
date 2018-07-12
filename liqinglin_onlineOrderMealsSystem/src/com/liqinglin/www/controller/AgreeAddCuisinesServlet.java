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
 * 同意上架食品的控制器
 */
@WebServlet("/AgreeAddCuisinesServlet")
public class AgreeAddCuisinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void agreeAdd(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		Cuisine cuisine = new Cuisine();
		cuisine.setId(Integer.parseInt(request.getParameter("cuisineId")));
		if(adminService.operationAdd(cuisine, Contants.PASS_EXAMINE) == Contants.SUCCESS_CODE) {
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
		agreeAdd(request, response);
	}

}
