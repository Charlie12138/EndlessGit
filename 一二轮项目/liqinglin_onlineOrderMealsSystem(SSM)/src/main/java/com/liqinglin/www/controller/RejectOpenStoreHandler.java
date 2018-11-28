package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RejectOpenStoreHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping("/RejectOpenStoreServlet")
	public String rejectOpenStore(HttpServletRequest request) {
		String storeName = request.getParameter("storeName");
		Store store = new Store();
		store.setStoreName(storeName);
		store.setStatus(Contants.REJECT_EXAMINE);
		if(adminService.operationOpenStore(store) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineOpenStoreServlet";
		}
		return null;
	}


}
