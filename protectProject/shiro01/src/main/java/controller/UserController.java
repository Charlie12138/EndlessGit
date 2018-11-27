package controller;

import errorcode.LuoErrorCode;
import exception.BusinessException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import util.DecriptUtil;
import com.alibaba.druid.support.json.JSONUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

	@RequestMapping("/index.jhtml")
	public ModelAndView getIndex(HttpServletRequest request) {
		return new ModelAndView("index");
	}

	@RequestMapping("/exceptionForPageJumps.jhtml")
	public ModelAndView exceptionForPageJumps(HttpServletRequest request) {
		throw new BusinessException(LuoErrorCode.NULL_OBJ);
	}

	@RequestMapping(value = "/bussinessException.json", method = RequestMethod.POST)
	@ResponseBody
	public String bussinessException(HttpServletRequest request) throws Exception {
		throw new Exception();
	}

	//跳转到登录界面
	@RequestMapping("/login.jhtml")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	//跳转到登录成功页面
	@RequestMapping("/loginsuccess.jhtml")
	public ModelAndView loginsuccess() throws Exception {
		return new ModelAndView("loginsuccess");
	}

	@RequestMapping("/newPage.jhtml")
	public ModelAndView newPage() {
		return new ModelAndView("newPage");
	}

	@RequestMapping("/newPageNotAdd.jhtml")
	public ModelAndView newPageNotAdd() {
		return new ModelAndView("newPageNotAdd");
	}

	/**
	 * 验证用户名和密码
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/checkLogin.json", method = RequestMethod.POST)
	@ResponseBody
	public String checkLogin(String username, String password) {
		Map<String, Object> result = new HashMap<>();
		try{
			UsernamePasswordToken token = new UsernamePasswordToken(username, DecriptUtil.MD5(password));
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()) {
				token.setRememberMe(true);
				currentUser.login(token);
			}
		} catch (Exception e) {
			throw new BusinessException(LuoErrorCode.LOGIN_VERIFY_FAILURE);
		}
		result.put("success", true);
		return JSONUtils.toJSONString(result);
	}

	@RequestMapping(value = "/logout.json", method = RequestMethod.POST)
	@ResponseBody
	public String logout() {
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return JSONUtils.toJSONString(result);
	}
}
