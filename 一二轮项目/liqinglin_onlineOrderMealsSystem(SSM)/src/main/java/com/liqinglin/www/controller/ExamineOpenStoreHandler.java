package com.liqinglin.www.controller;

import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.serviceImp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ExamineOpenStoreHandler {

	@Autowired
	AdminService adminService;

	@RequestMapping("/openStore")
	public String openStore() {
		return "store/openStore";
	}

	@RequestMapping(value = "/ExamineOpenStoreServlet", method = RequestMethod.GET)
	public String examineOpenStore(HttpServletRequest request) {
		List<Store> stores = adminService.examineOpenStore();
		request.getSession().setAttribute("stores", stores);
		return "admin/examineOpenStore";
	}
}
