package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AgreeOpenStoreHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping("/AgreeOpenStoreServlet")
	public String agreeOpenStore(Store store) {
		store.setStoreName(store.getStoreName());
		store.setStatus(Contants.PASS_EXAMINE);
		if(adminService.operationOpenStore(store) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineOpenStoreServlet";
		}
		return null;
	}
}
