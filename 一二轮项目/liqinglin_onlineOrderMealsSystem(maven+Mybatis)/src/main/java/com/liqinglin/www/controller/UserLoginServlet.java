package com.liqinglin.www.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.DataService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;

/**
 * 登录控制器
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserLoginServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().setAttribute("orderStatus0", Contants.ORDER_STATUS_A);
		request.getSession().setAttribute("orderStatus1", Contants.ORDER_STATUS_B);
		request.getSession().setAttribute("orderStatus2", Contants.ORDER_STATUS_C);
		request.getSession().setAttribute("orderStatus3", Contants.ORDER_STATUS_D);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		/**
		 * 用户输入信息的验证
		 */
		DataService dataService = new DataService();
		if (dataService.isUserNameRight(username) == Contants.USERNAME_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.USERNAME_FORMAT_ERROR);
			request.getRequestDispatcher("user/userLogin.jsp").forward(request, response);
			return;
		}

		if (dataService.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
			return;
		}
		
		if(dataService.isSelected(role)) {
			request.setAttribute("message", Contants.ROLE_SELECT);
			request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
			return;
		}
		/**
		 * 封装数据
		 */
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(Integer.parseInt(role));
		/**
		 * 进行登录对比
		 */
		
		UserService userService = new UserService();
		CartService cartService = new CartService();
		List<Store> stores = new ArrayList<Store>();
		int result;
		try {
			result = userService.login(user);
			
			if(result == Contants.SUCCESS_CODE) {  //登录成功     
				user = userService.getUserInfo(username);
				request.getSession().setAttribute("user", user);// 登录的用户信息存入Session,可以直接在jsp用EL表达式变量名配合取值
				cartService.addCart(user.getId());
				request.setAttribute("message", Contants.LOGIN_SUCCEESS);
				
				if(Contants.ROLE_USER == Integer.parseInt(role)) {//判断是不是普通用户身份登录
					stores = userService.getUserStore(username);
					if(stores.size() != 0) {
						request.getSession().setAttribute("stores", stores);											
					}
					response.sendRedirect("/liqinglin_onlineOrderMealsSystem/ListAllCuisinesServlet?pageNum=1&jump=2");	
					
				} else if(Contants.ROLE_ADMIN == Integer.parseInt(role)) {//判断是不是管理员身份登录
					request.getRequestDispatcher("/admin/adminIndex.jsp").forward(request, response);
					
				} else if(Contants.ROLE_MERCHANT == Integer.parseInt(role)) {//判断是不是店家身份登录
					stores = userService.getUserStore(username);
					if(stores.size() != 0) {
						request.getSession().setAttribute("stores", stores);						
					}
					request.getRequestDispatcher("/store/selectStore.jsp").forward(request, response);
				}
				
			} else if(result == Contants.USERNAME_NOT_EXIST_CODE) {//用户不存在
				request.setAttribute("message", Contants.USERNAME_NOT_EXIST);
				request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
			} else if(result == Contants.FAIL_CODE) { //密码错误
				request.setAttribute("message", Contants.PASSWORD_ERROR);
				request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
			} else if(result == Contants.ROLE_SELECT_ERROR_CODE) {
				request.setAttribute("message", Contants.ROLE_SELECT_ERROR);
				request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
			} else if(result == Contants.NOT_EXAMINE_CODE) {
				request.setAttribute("message", Contants.REGISTER_EXAMINEING);
				request.getRequestDispatcher("/user/userLogin.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

}
