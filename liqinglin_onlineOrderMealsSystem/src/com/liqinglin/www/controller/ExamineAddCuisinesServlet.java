package com.liqinglin.www.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.service.AdminService;

/**
 * 审核上架菜肴控制器
 */
@WebServlet("/ExamineAddCuisinesServlet")
public class ExamineAddCuisinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void listCuisines(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		List<Cuisine> cuisines = adminService.examineAddCuisines();
		request.getSession().setAttribute("cuisines", cuisines);
		try {
			response.sendRedirect("/liqinglin_onlineOrderMealsSystem/admin/examineAddCuisine.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listCuisines(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
