package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;

/**
 * 拒绝开店的控制器
 */
@WebServlet("/RejectOpenStoreServlet")
public class RejectOpenStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminService adminService = new AdminService();
		String storeName = request.getParameter("storeName");
		if(adminService.operationOpenStore(storeName, Contants.REJECT_EXAMINE) == Contants.SUCCESS_CODE) {
			try {
				response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ExamineOpenStoreServlet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
