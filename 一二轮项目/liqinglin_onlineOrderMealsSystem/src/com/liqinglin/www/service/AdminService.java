package com.liqinglin.www.service;

import java.util.List;

import com.liqinglin.www.dao.AdminDao;
import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.util.Contants;

public class AdminService {
	AdminDao adminDao = new AdminDao();
	
	/**
	 * 审核注册
	 * @return
	 */
	public List<User> examineRegister(){
		List<User> users = adminDao.getRegiserUser();
		return users;
	}
	
	/**
	 * 通过注册
	 * @return
	 */
	public int agreeRegister(User user) {
		if(adminDao.agreeRegister(user) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 审核开店请求
	 */
	public List<Store> examineOpenStore() {
		List<Store> stores = adminDao.getStores();
		return stores;
	}
	
	/**
	 * 通过开店请求
	 */
	public int operationOpenStore(String storeName, int status) {
		if(adminDao.operationOpenStore(storeName, status) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 获得正在请求上架的菜肴
	 */
	public List<Cuisine> examineAddCuisines() {
		List<Cuisine> cuisines = adminDao.getCuisines();
		return cuisines;
	}
	
	/**
	 * 对上架请求进行操作
	 */
	public int operationAdd(Cuisine cuisine, int status) {
		if(adminDao.operationAdd(cuisine, status) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 下架食品
	 */
	public int deleteCuisine(int cuisineId) {
		if(adminDao.deleteCuisine(cuisineId) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	
	/**
	 * 获得某个页面的店铺
	 */
	public PageBean<Store> getPageStores(int pageNum, int pageSize){
		int totalRecord  = adminDao.getAllStoresNum(); //获得数据量
		PageBean<Store> pb = new PageBean<Store>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		pb.setList(adminDao.getPageStores(startIndex, pageSize));
		return pb;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
