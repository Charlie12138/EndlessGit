package com.liqinglin.www.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
/**
 * 审核注册的控制器
 *
 */
@WebServlet("/ExamineRegisterServlet")
public class ExamineRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void listRegisterUsers(HttpServletRequest request, HttpServletResponse response) {
    	AdminService adminService = new AdminService();  	
    	List<User> users = adminService.examineRegister();
		try {
			request.getSession().setAttribute("users", users);
			response.sendRedirect("/liqinglin_onlineOrderMealsSystem/admin/examineRegister.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listRegisterUsers(request, response);  //用<a></a>超链接访问servlet的话，默认是get方式的所以只能用doget方法

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//listRegisterUsers(request, response);
	}

}
