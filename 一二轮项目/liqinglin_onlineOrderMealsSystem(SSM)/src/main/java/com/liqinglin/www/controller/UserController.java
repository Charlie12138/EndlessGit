package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.CartService;
import com.liqinglin.www.service.DataService;
import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	DataService dataService;

	@Autowired
	CartService cartService;

	@Autowired
	DataService datas;

	@Autowired
	UserService userService;

	/**
	 * 修改密码
	 * @return 修改密码
	 */
	@RequestMapping("/modifyPw")
	public String modifyPassword() {
		return "user/modifyPassword";
	}

	/**
	 * 修改密码
	 * @param request 请求
	 * @return 修改密码
	 */
	@RequestMapping(value = "/ModifyPasswordServlet", method = RequestMethod.POST)
	public String modifyPassword(HttpServletRequest request) {
		String password = request.getParameter("password");
		User user = (User)request.getSession().getAttribute("user");
		user.setPassword(password);
		if(datas.isRightPassword(password) == Contants.PASSWORD_FORMAT_RIGHT_CODE) {
			if(userService.modifyPassword(user) == Contants.SUCCESS_CODE) {
				request.setAttribute("message", Contants.SUCCESS);
				return "user/modifyPassword";
			}else {
				request.setAttribute("message", Contants.FAIL);
				return "user/modifyPassword";
			}
		}

		if (datas.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			return "user/modifyPassword";
		}
		return null;
	}

	/**
	 * 修改用户信息
	 * @return 修改用户信息
	 */
	@RequestMapping("/modifyUserInfo")
	public String modifyUserInfo() {
		return "user/modifyUserInfo";
	}

	/**
	 * 提交用户信息
	 * @param request 请求
	 * @return 修改界面
	 */
	@RequestMapping("/SubmitUserInfoServlet")
	public String submitUserInfo(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("user");
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
		return "/user/modifyUserInfo";
	}

	/**
	 * 用户登出
	 * @param request 请求
	 * @param attributes 用于重定向之后还能带参数跳转
	 * @return 主页
	 */
	@RequestMapping(value = "/UserDropOutServlet", method = RequestMethod.GET)
	public String userDropOut(HttpServletRequest request, RedirectAttributes attributes) {
		request.getSession().invalidate(); // 销毁Session
		attributes.addAttribute("pageNum", 1);
		attributes.addAttribute("jump", 1);
		return "redirect:ListAllCuisine";
	}


	/**
	 * 转到登录界面
	 * @return 登录界面
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "user/userLogin";
	}

	/**
	 * 登录
	 * @param user 用户
	 * @param request 请求
	 * @param redirectAttributes 用于重定向之后还能带参数跳转
	 */
	@RequestMapping("/userLogin")
	public String userLogin(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		int role = user.getRole();
		request.getSession().setAttribute("orderStatus0", Contants.ORDER_STATUS_A);
		request.getSession().setAttribute("orderStatus1", Contants.ORDER_STATUS_B);
		request.getSession().setAttribute("orderStatus2", Contants.ORDER_STATUS_C);
		request.getSession().setAttribute("orderStatus3", Contants.ORDER_STATUS_D);

		/*
		  用户输入信息的验证
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

		/*
		  进行登录对比
		 */

		List<Store> stores;
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

	/**
	 * 去注册界面
	 * @return 注册
	 */
	@RequestMapping("toRegister")
	public String toRegister() {
		return "user/userRegister";
	}

	/**
	 * 用户注册
	 * @param request 请求
	 * @return 注册
	 */
	@RequestMapping("/UserRegisterServlet")
	public String userRegister(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		/*
		  用户输入的信息进行检验
		 */
		if (dataService.isUserNameRight(username) == Contants.USERNAME_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.USERNAME_FORMAT_ERROR);
			return "user/userRegister";
		}

		if (dataService.isRightPassword(password) == Contants.PASSWORD_FORMAT_ERROR_CODE) {
			request.setAttribute("message", Contants.PASSWORD_FORMAT_ERROR);
			return "user/userRegister";
		}

		if (dataService.isConfirmRight(password, repassword) == Contants.CHECK_PASSWORD_ERROR_CODE) {
			request.setAttribute("message", Contants.CHECK_PASSWORD_ERROR);
			return "user/userRegister";
		}

		/*
		  封装user信息
		 */
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		int result = userService.register(user);
		if (result == Contants.USERNAME_EXIST_CODE) {
			request.setAttribute("message", Contants.USERNAME_EXIST);
			return "user/userRegister";
		} else if(result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.WAIT_FOR_EXAMINEING);//提交注册成功等待审核
			return "user/userLogin";
		} else {
			request.setAttribute("message", Contants.FAIL);
			return "user/UserRegister";
		}
	}
}
