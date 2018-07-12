package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.DataService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;

/**
 * 修改密码控制器
 */
@WebServlet("/ModifyPasswordServlet")
public class ModifyPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		User user = (User)request.getSession().getAttribute("user");
		user.setPassword(password);
		DataService datas = new DataService();
		UserService userService = new UserService();
		if(datas.isRightPassword(password) == Contants.PASSWORD_FORMAT_RIGHT_CODE) {
			if(userService.modifyPassword(user) == Contants.SUCCESS_CODE) {
				request.setAttribute("message", Contants.SUCCESS);
				request.getRequestDispatcher("/user/modifyPassword.jsp").forward(request, response);
			}else {
				request.setAttribute("message", Contants.FAIL);
				request.getRequestDispatcher("/user/modifyPassword.jsp").forward(request, response);
			}
		}

		if (datas.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			request.getRequestDispatcher("/user/modifyPassword.jsp").forward(request, response);
			return;
		}
	
	}

}
