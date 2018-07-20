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
 * 用户注册控制器
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		/**
		 * 用户输入的信息进行检验
		 */
		DataService dataService = new DataService();
		if (dataService.isUserNameRight(username) == Contants.USERNAME_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.USERNAME_FORMAT_ERROR);
			request.getRequestDispatcher("user/userRegister.jsp").forward(request, response);
			return;
		}

		if (dataService.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			request.getRequestDispatcher("/user/userRegister.jsp").forward(request, response);
			return;
		}

		if (dataService.isConfirmRight(password, repassword) == Contants.CHECK_PASSWORD_ERROR_CODE) {
			request.setAttribute("message", Contants.CHECK_PASSWORD_ERROR);
			request.getRequestDispatcher("/user/userRegister.jsp").forward(request, response);
			return;
		}

		/**
		 * 封装user信息
		 */
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		UserService userService = new UserService();
		int result = userService.register(user);
		if (result == Contants.USERNAME_EXIST_CODE) { 
			request.setAttribute("message", Contants.USERNAME_EXIST);
			request.getRequestDispatcher("/user/userRegister.jsp").forward(request, response);
		} else if(result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.WAIT_FOR_EXAMINEING);//提交注册成功等待审核
			request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
		} else {
			request.setAttribute("message", Contants.FAIL);
			request.getRequestDispatcher("/user/UserRegister.jsp").forward(request, response);
		}		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		check(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
