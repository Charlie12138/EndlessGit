package com.liqinglin.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserDropOutHandler {

	@RequestMapping(value = "/UserDropOutServlet", method = RequestMethod.GET)
	public String userDropOut(HttpServletRequest request, RedirectAttributes attributes) {
		request.getSession().invalidate(); // 销毁Session
		attributes.addAttribute("pageNum", 1);
		attributes.addAttribute("jump", 1);
		return "redirect:ListAllCuisine";
	}
}
