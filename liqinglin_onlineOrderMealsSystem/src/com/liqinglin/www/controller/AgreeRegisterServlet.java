package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;

/**
 * 同意通过注册的控制器
 */
@WebServlet("/AgreeRegisterServlet")
public class AgreeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void agreeRegister(HttpServletRequest request, HttpServletResponse response) {
		AdminService adminService = new AdminService();
		User user = new User();
		user.setUsername(request.getParameter("username"));
		if(adminService.agreeRegister(user) == Contants.SUCCESS_CODE) {
			try {
				response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ExamineRegisterServlet");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		agreeRegister(request, response);
	}

}
