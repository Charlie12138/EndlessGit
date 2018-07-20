package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.User;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;

/**
 * 修改个人信息控制器
 */
@WebServlet("/SubmitUserInfoServlet")
public class SubmitUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		UserService userService = new UserService();
		String realname = request.getParameter("realname");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		user.setEmail(email);
		user.setPhone(phone);
		user.setRealname(realname);
		int result = userService.modifyUserInfo(user);
		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.MODIFY_STORE_SUCCEESS);// 提交审核成功			
		}  else {
			request.setAttribute("message", Contants.MODIFY_STORE_FAIL);
		}
		request.getRequestDispatcher("/user/modifyUserInfo.jsp").forward(request, response);
	}

}
