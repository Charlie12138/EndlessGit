package com.liqinglin.www.controller;

import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class StoreController {
	@Autowired
	AdminService adminService;

	@Autowired
	StoreService storeService;

	@RequestMapping("/AgreeOpenStoreServlet")
	public String agreeOpenStore(Store store) {
		store.setStoreName(store.getStoreName());
		store.setStatus(Contants.PASS_EXAMINE);
		if(adminService.operationOpenStore(store) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineOpenStoreServlet";
		}
		return null;
	}

	/**
	 * 转到开店界面
	 * @return 开店界面
	 */
	@RequestMapping("/openStore")
	public String openStore() {
		return "store/openStore";
	}

	/**
	 * 审核开店
	 * @param request 请求
	 * @return 开店界面
	 */
	@RequestMapping(value = "/ExamineOpenStoreServlet", method = RequestMethod.GET)
	public String examineOpenStore(HttpServletRequest request) {
		List<Store> stores = adminService.examineOpenStore();
		request.getSession().setAttribute("stores", stores);
		return "admin/examineOpenStore";
	}

	/**
	 * 罗列所有店铺
	 * @param request 请求
	 * @param pageNum 页数
	 * @return 罗列所有店铺
	 */
	@RequestMapping(value = "/ListAllStoresServlet", method = RequestMethod.GET)
	public String listAllStore(HttpServletRequest request, @RequestParam("pageNum") Integer pageNum) {
		int pageSize = 5;    //每页的数据量
		PageBean<Store> pb = adminService.getPageStores(pageNum, pageSize);
		if (pb.getList().size() == 0) {
			return "admin/managerStores";
		}
		request.setAttribute("stores", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		request.getSession().setAttribute("status0", Contants.EXAMINEING);
		request.getSession().setAttribute("status1", Contants.PASS_EXAMINE);
		request.getSession().setAttribute("status2", Contants.REJECT_EXAMINE);
		request.getSession().setAttribute("status3", Contants.FORCE_CLOSE);
		return "admin/managerStores";
	}

	/**
	 * 管理店铺
	 * @param request 请求
	 * @param attributes 用于重定向之后还能带参数跳转
	 * @return 管理店铺
	 */
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

	/**
	 * 拒绝开店
	 * @param request 请求
	 * @return 拒绝开店
	 */
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

	/**
	 * 修改店铺信息
	 * @return 修改店铺信息
	 */
	@RequestMapping("/modifyStoreInfo")
	public String modifyStoreInfo() {
		return "store/modifyStoreInfo";
	}

	/**
	 * 提交店铺信息
	 * @param request 请求
	 * @return 提交店铺信息
	 */
	@RequestMapping(value = "/SubmitStoreInfoServlet", method = RequestMethod.POST)
	public String submitStoreInfo(HttpServletRequest request) {
		String storeName = request.getParameter("storeName");
		String phone = request.getParameter("phone");
		String shopkeeperName = request.getParameter("shopkeeperName");
		String storeDescription = request.getParameter("storeDescription");
		String address = request.getParameter("address");
		int storeId = Integer.parseInt(request.getParameter("storeId"));

		Store store = new Store();
		store.setStoreName(storeName);
		store.setPhone(phone);
		store.setAddress(address);
		store.setStoreDescription(storeDescription);
		store.setShopkeeperName(shopkeeperName);
		store.setStoreId(storeId);

		int result = storeService.submitStoreInfo(store);

		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.MODIFY_STORE_SUCCEESS);// 提交审核成功
			return "store/modifyStoreInfo";
		} else {
			request.setAttribute("message", Contants.MODIFY_STORE_FAIL);
			return "store/modifyStoreInfo";
		}
	}

	/**
	 * 开店
	 * @param request 请求
	 * @return 开店
	 */
	@RequestMapping("/UserOpenStoreServlet")
	public String userOpenStore(HttpServletRequest request) {
		String storeName = request.getParameter("storeName");
		String phone = request.getParameter("phone");
		String shopkeeperName = request.getParameter("shopkeeperName");
		String storeDescription = request.getParameter("storeDescription");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
		User user = new User();
		Store store = new Store();
		store.setStoreName(storeName);
		store.setPhone(phone);
		store.setAddress(address);
		store.setStoreDescription(storeDescription);
		store.setShopkeeperName(shopkeeperName);
		user.setUsername(username);
		store.setUser(user);
		store.setCreateTime(new Timestamp(System.currentTimeMillis()));

		int result = storeService.openStroe(store);
		if (result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.OPENSTORE_EXAMINEING);// 提交审核成功
			return "store/openStore";
		} else if (result == Contants.STORENAME_EXIST_CODE) {
			request.setAttribute("message", Contants.STORENAME_EXIST);
			return "store/openStore";
		} else {
			request.setAttribute("message", Contants.FAIL);
			return "store/openStore";
		}
	}
}
