package com.liqinglin.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;

/**
 * 获得某个店铺的菜单
 */
@WebServlet("/ListCuisinesServlet")
public class ListCuisinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoreService storeService = new StoreService();
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));//当前页数
		String jump = request.getParameter("jump");//标记跳转到哪
		Store store = storeService.getStoreInfo(storeId);
		request.getSession().setAttribute("store", store);
		request.getSession().setAttribute("status0", Contants.EXAMINEING);
		request.getSession().setAttribute("status1", Contants.PASS_EXAMINE);
		request.getSession().setAttribute("status2", Contants.REJECT_EXAMINE);
		int pageSize = 5;//每页的数据量
		PageBean<Cuisine> pb = storeService.getPageCuisine(storeId, pageNum, pageSize);
		request.getSession().setAttribute("cuisines", pb.getList());		
		request.setAttribute("pageNum", pb.getPageNum());
		request.setAttribute("totalPage", pb.getTotalPage());
		request.setAttribute("totalRecord", pb.getTotalRecord());
		if(jump.equals("1")) {
			request.getRequestDispatcher("/store/storeIndex.jsp").forward(request, response);
		} else if(jump.equals("2")) {
			request.getRequestDispatcher("/store/deleteCuisine.jsp").forward(request, response);
		} else if(jump.equals("3")) {
			request.getRequestDispatcher("/store/modifyCuisineInfoIndex.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
