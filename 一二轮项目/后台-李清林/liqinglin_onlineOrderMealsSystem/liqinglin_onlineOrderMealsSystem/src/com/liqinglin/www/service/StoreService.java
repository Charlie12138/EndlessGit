package com.liqinglin.www.service;




import com.liqinglin.www.dao.StoreDao;
import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.util.Contants;

public class StoreService {
	StoreDao storeDao = new StoreDao();
	/**
	 * 获得某个店铺的信息
	 */
	public Store getStoreInfo(int storeId) {
		return storeDao.getStoreInfoById(storeId);
	}
	
	/**
	 * 上架美食
	 */
	public int addCuisine(Store store, Cuisine cuisine) {
		int result = storeDao.addCuisine(store, cuisine);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 判断图片格式是否正确
	 */
	public int imageFormatIsRight(String filename) {
		if ("".equals(filename) ||filename.endsWith(".jpg") || filename.endsWith(".png")
				|| filename.endsWith(".gif") || filename.endsWith(".jpeg")
				|| filename.endsWith(".JPG") || filename.endsWith(".PNG")
				|| filename.endsWith(".GIF") || filename.endsWith(".JPEG")) {
			return Contants.IMAGE_FORMAT_IS_RIGHT;
		} else {
			return Contants.IMAGE_FORMAT_IS_ERROR_CODE;
		}
	}
	
	/**
	 * 得到菜肴信息
	 */
	public Cuisine getCuisineInfo(int cuisineId) {
		return storeDao.getCuisineInfo(cuisineId);		
	}
	
	
	/**
	 * 提交菜肴的修改信息
	 */
	public int submitInfo (Cuisine cuisine) {
		boolean result =  storeDao.updateCuisineInfo(cuisine);
		if(result) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 提交店铺的修改信息
	 */
	public int submitStoreInfo (Store store) {
		boolean result =  storeDao.updateStoreInfo(store);
		if(result) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 分页：获得某个页面的数据
	 */
	public PageBean<Cuisine> getPageCuisine( int pageNum, int pageSize){
		int totalRecord = storeDao.getAllCuisinesNum();    //获得数据量
		PageBean<Cuisine> pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		pb.setList(storeDao.getPageCuisines(startIndex, pageSize));
		return pb;
	}	
	
	/**
	 * 修改销量
	 */
	public void modifySellCount(int cuisineId, int number) {
		storeDao.modifySellCount(cuisineId, number);
	}
	
	
	
	/**
	 * 检出搜索输入是否为空
	 */
	public boolean check(String key) {
		if("".equals(key) || key == null || key.equals("%")) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	/**
	 * 搜索
	 */
	public PageBean<Cuisine> search(String choose, String key, int pageNum, int pageSize) {
		PageBean<Cuisine> pb = null;
		if(choose.equals(Contants.CUISINE)) {
			int totalRecord = storeDao.getCuisinesNumByCuisineName(key);
			pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
			int startIndex = pb.getStartIndex();  //获得开始的索引
			pb.setList(storeDao.searchByCuisine(key, startIndex, pageSize));
		} else {
			int totalRecord = storeDao.getCuisinesNumByStoreName(key);
			pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
			int startIndex = pb.getStartIndex();  //获得开始的索引
			pb.setList(storeDao.searchByStore(key, startIndex, pageSize));
		}
		return pb;
	}
	
	
	/**
	 * 分页：获得某个页面的数据
	 */
	public PageBean<Cuisine> getPageCuisine(int storeId, int pageNum, int pageSize){
		int totalRecord = storeDao.getUserAllCuisines(storeId);  //获得数据量
		PageBean<Cuisine> pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		pb.setList(storeDao.getPageCuisine(storeId, startIndex, pageSize));
		return pb;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
