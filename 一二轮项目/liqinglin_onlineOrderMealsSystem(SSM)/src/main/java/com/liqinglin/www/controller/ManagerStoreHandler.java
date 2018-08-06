package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.serviceImp.AdminServiceImp;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ManagerStoreHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/ManagerStoreServlet", method = RequestMethod.POST)
	public String managerStore(HttpServletRequest request, RedirectAttributes attributes) {
		String storeName = request.getParameter("storeName");
		int status = Integer.parseInt(request.getParameter("status"));
		Store store = new Store();
		store.setStoreName(storeName);
		store.setStatus(status);
		if(adminService.operationOpenStore(store) == Contants.SUCCESS_CODE) {
			attributes.addAttribute("pageNum", 1);
			return "redirect:ListAllStoresServlet";
		}
		return null;
	}

}
