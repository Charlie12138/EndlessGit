package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.DataService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.serviceImp.CartServiceImp;
import com.liqinglin.www.serviceImp.DataServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserLoginHandler {

	@Autowired
	UserService userService;

	@Autowired
	CartService cartService;

	@Autowired
	DataService dataService;

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "user/userLogin";
	}

	@RequestMapping("/userLogin")
	public String userLogin(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int role = user.getRole();
		request.getSession().setAttribute("orderStatus0", Contants.ORDER_STATUS_A);
		request.getSession().setAttribute("orderStatus1", Contants.ORDER_STATUS_B);
		request.getSession().setAttribute("orderStatus2", Contants.ORDER_STATUS_C);
		request.getSession().setAttribute("orderStatus3", Contants.ORDER_STATUS_D);

		/**
		 * 用户输入信息的验证
		 */
		if (dataService.isUserNameRight(user.getUsername()) == Contants.USERNAME_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.USERNAME_FORMAT_ERROR);
			return "user/userLogin";
		}

		if (dataService.isRightPassword(user.getPassword()) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			return "user/userLogin";
		}

		if(dataService.isSelected(""+user.getRole())) {
			request.setAttribute("message", Contants.ROLE_SELECT);
			return "user/userLogin";
		}

		/**
		 * 进行登录对比
		 */

 		List<Store> stores = new ArrayList<Store>();
		int result;
			result = userService.login(user);
			if(result == Contants.SUCCESS_CODE) {  //登录成功
				user = userService.getUserInfo1(user.getUsername());
				user.setRole(role);
				request.getSession().setAttribute("user", user);// 登录的用户信息存入Session,可以直接在jsp用EL表达式变量名配合取值
				cartService.addCart(user.getId());
				request.setAttribute("message", Contants.LOGIN_SUCCEESS);

				if(Contants.ROLE_USER == user.getRole()) {//判断是不是普通用户身份登录
					stores = userService.getUserStore(user.getUsername());
					if(stores.size() != 0) {
						request.getSession().setAttribute("stores", stores);
					}
					redirectAttributes.addAttribute("pageNum", 1);
					redirectAttributes.addAttribute("jump", 2);
					return "redirect:ListAllCuisine";

				} else if(Contants.ROLE_ADMIN == user.getRole()) {//判断是不是管理员身份登录
					return "admin/adminIndex";
				} else if(Contants.ROLE_MERCHANT ==  user.getRole()) {//判断是不是店家身份登录
					stores = userService.getUserStore(user.getUsername());
					if(stores.size() != 0) {
						request.getSession().setAttribute("stores", stores);
					}
					return "store/selectStore";
				}

			} else if(result == Contants.USERNAME_NOT_EXIST_CODE) {//用户不存在
				request.setAttribute("message", Contants.USERNAME_NOT_EXIST);
				return "/user/userLogin";
			} else if(result == Contants.FAIL_CODE) { //密码错误
				request.setAttribute("message", Contants.PASSWORD_ERROR);
				return "user/userLogin";
			} else if(result == Contants.ROLE_SELECT_ERROR_CODE) {
				request.setAttribute("message", Contants.ROLE_SELECT_ERROR);
				return "user/userLogin";
			} else if(result == Contants.NOT_EXAMINE_CODE) {
				request.setAttribute("message", Contants.REGISTER_EXAMINEING);
				return "user/userLogin";
			}
			return "";
	}
}
