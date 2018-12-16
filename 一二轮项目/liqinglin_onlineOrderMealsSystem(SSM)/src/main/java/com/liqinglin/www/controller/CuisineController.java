package com.liqinglin.www.controller;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.service.OrderService;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CuisineController {
	@Autowired
	StoreService storeService;

	@Autowired
	AdminService adminService;

	@Autowired
	OrderService orderService;

	@RequestMapping("/addCuisine")
	public String add() {
		return "store/addCuisine";
	}

	/**
	 * 添加菜肴
	 */
	@RequestMapping("/AddCuisinesServlet")
	public String addCuisine(HttpServletRequest request, HttpServletResponse response) {
		Store store = (Store)request.getSession().getAttribute("store");
		Cuisine cuisine = UploadUtil.upload(request);
		cuisine.setStore(store);
		if(storeService.imageFormatIsRight(cuisine.getPicturePath()) == Contants.IMAGE_FORMAT_IS_ERROR_CODE) {
			request.setAttribute("message", Contants.IMAGE_FORMAT_IS_ERROR);
			return "store/addGoods";
		}
		int result = storeService.addCuisine(cuisine);
		if(result == Contants.SUCCESS_CODE) {
			request.setAttribute("message", Contants.ADDCUISINE_SUCCEESS);
		} else {
			request.setAttribute("message", Contants.ADDCUISINE_FAIL);
		}
		return "redirect:ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=1";
	}

	/**
	 * 设置菜肴状态为通过
	 */
	@RequestMapping("/AgreeAddCuisinesServlet")
	public String agreeAddCuisine(Cuisine cuisine) {
		cuisine.setStatus(Contants.PASS_EXAMINE);
		if(adminService.operationAdd(cuisine) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineAddCuisinesServlet";
		}
		return null;
	}

	/**
	 * 删除菜肴
	 */
	@RequestMapping(value = "/DeleteCuisineServlet", method = RequestMethod.POST)
	public String deleteCuisine(HttpServletRequest request, RedirectAttributes attributes) {
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		Store store= (Store)request.getSession().getAttribute("store");
		if(adminService.deleteCuisine(cuisineId) == Contants.SUCCESS_CODE) {
			attributes.addAttribute("storeId", store.getStoreId());
			attributes.addAttribute("pageNum", 1);
			attributes.addAttribute("jump", 2);
			return "redirect:ListCuisine";
		}
		return null;
	}

	/**
	 * 罗列菜肴
	 * @param storeId 店铺id
	 * @param pageNum 页数
	 * @param jump 跳转到，1：； 2：；3：
	 */
	@RequestMapping(value = "/ListCuisine", method = RequestMethod.GET)
	public String listCuisine(HttpServletRequest request, @RequestParam("storeId") Integer storeId,
							  @RequestParam("pageNum") Integer pageNum, @RequestParam("jump") Integer jump) {
		Store store = storeService.getStoreInfo(storeId);
		request.getSession().setAttribute("store", store);
		request.getSession().setAttribute("status0", Contants.EXAMINEING);
		request.getSession().setAttribute("status1", Contants.PASS_EXAMINE);
		request.getSession().setAttribute("status2", Contants.REJECT_EXAMINE);
		int pageSize = 5;//每页的数据量
		PageBean<Cuisine> pb = storeService.getPageCuisine(storeId, pageNum, pageSize);
		if (pb.getList().size() == 0) {
			if(jump == 1) {
				return "store/storeIndex";
			} else if(jump == 2) {
				return "store/deleteCuisine";
			} else if(jump == 3) {
				return "store/modifyCuisineInfoIndex";
			}
		}
		request.getSession().setAttribute("cuisines", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		int status = pb.getList().get(0).getStatus();
		request.setAttribute("cuisineStatus", status);
		if(jump == 1) {
			return "store/storeIndex";
		}
		if(jump == 2) {
			return "store/deleteCuisine";
		}
		if(jump == 3) {
			return "store/modifyCuisineInfoIndex";
		}
		return null;
	}


	/**
	 * 罗列审核中的菜肴
	 */
	@RequestMapping(value ="/ExamineAddCuisinesServlet", method = RequestMethod.GET)
	public String examineAddCuisine(HttpServletRequest request) {
		List<Cuisine> cuisines = adminService.examineAddCuisines();
		request.getSession().setAttribute("cuisines", cuisines);
		return "admin/examineAddCuisine";
	}

	/**
	 * 转到管理员主页
	 */
	@RequestMapping("/toAdminIndex")
	public String toAdminIndex() {
		return "admin/adminIndex";
	}

	/**
	 * 罗列所有菜肴
	 * @param pageNum 页数
	 * @param jump 跳转到
	 */
	@RequestMapping(value = "/ListAllCuisine")
	public String listAllCuisine(HttpServletRequest request, @RequestParam("pageNum") Integer pageNum, @RequestParam("jump") Integer jump) {
		int pageSize = 5;//每页的数据量
		PageBean<Cuisine> pb = storeService.getPageCuisine(pageNum, pageSize);
		if (pb.getList().size() == 0) {
			if(jump == 1) {
				return "public/index";
			} else if(jump == 2){
				return "user/userIndex";
			}
		}
		request.getSession().setAttribute("cuisines", pb.getList());
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		int status = pb.getList().get(0).getStatus();
		request.setAttribute("cuisineStatus", status);
		if(jump == 1) {
			return "public/index";
		} else if(jump == 2){
			return "user/userIndex";
		}
		return null;
	}

	/**
	 * 修改菜肴
	 */
	@RequestMapping(value = "/ModifyCuisinesInfoServlet", method = RequestMethod.POST)
	public String ModifyCuisineInfo(HttpServletRequest request) {
		int cuisineId = Integer.parseInt(request.getParameter("cuisineId"));
		Cuisine cuisine = storeService.getCuisineInfo(cuisineId);
		request.getSession().setAttribute("cuisine", cuisine);
		return "store/modifyCuisineInfo";
	}


	/**
	 * 拒绝添加菜肴
	 */
	@RequestMapping(value = "/RejectAddCuisinesServlet", method = RequestMethod.POST)
	public String rejectAddCuisine(HttpServletRequest request) {
		Cuisine cuisine = new Cuisine();
		cuisine.setId(Integer.parseInt(request.getParameter("cuisineId")));
		cuisine.setStatus(Contants.REJECT_EXAMINE);
		if(adminService.operationAdd(cuisine) == Contants.SUCCESS_CODE) {
			return "redirect:ExamineAddCuisinesServlet";
		}
		return null;
	}

	/**
	 * 配送菜肴
	 */
	@RequestMapping(value = "/SendCuisineServlet", method = RequestMethod.POST)
	public String sendCuisine(HttpServletRequest request, RedirectAttributes attributes) {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		orderService.modifyOrderStatus(Contants.ORDER_STATUS_B, orderId);
		attributes.addAttribute("storeId",storeId);
		attributes.addAttribute("pageNum", 1);
		attributes.addAttribute("jump", 1);
		attributes.addAttribute("orderStatus", Contants.ORDER_STATUS_A);
		return "redirect:ListTotalOrderServlet";
	}

	/**
	 * 提交菜肴
	 */
	@RequestMapping(value = "/SubmitCuisineInfoServlet", method = RequestMethod.POST)
	public void submitCuisineInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cuisine cuisine = UploadUtil.upload(request);
		Store store =(Store)request.getSession().getAttribute("store");
		int result = storeService.submitInfo(cuisine);
		if(result == Contants.SUCCESS_CODE) {
			response.setHeader("refresh","2;URL=/ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=3");
			response.getWriter().println("<center><h1>" + Contants.MODIFYCUISINE_SUCCEESS + "</h1></center>");
		} else {
			response.setHeader("refresh","2;URL=/ListCuisine?storeId="+store.getStoreId()+"&pageNum=1&jump=3");
			response.getWriter().println("<center><h1>" + Contants.MODIFYCUISINE_FAIL + "</h1></center>");
		}
	}
}
