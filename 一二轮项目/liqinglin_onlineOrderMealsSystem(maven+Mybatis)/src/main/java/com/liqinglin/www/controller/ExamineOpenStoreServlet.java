package com.liqinglin.www.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;

/**
 * 申请开店控制器
 */
@WebServlet("/ExamineOpenStoreServlet")
public class ExamineOpenStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void listStores(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		List<Store> stores = adminService.examineOpenStore();
		request.getSession().setAttribute("stores", stores);
		try {
			response.sendRedirect("/liqinglin_onlineOrderMealsSystem/admin/examineOpenStore.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listStores(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
