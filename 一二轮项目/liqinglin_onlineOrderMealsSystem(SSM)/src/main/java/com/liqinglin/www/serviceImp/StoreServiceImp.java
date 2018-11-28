package com.liqinglin.www.serviceImp;



import com.liqinglin.www.dao.StoreMapper;
import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.StoreService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("storeService")
public class StoreServiceImp implements StoreService {

	@Autowired
	StoreMapper storeMapper;
	/**
	 * 获得某个店铺的信息
	 */
	@Override
	public Store getStoreInfo(int storeId) {
		return storeMapper.getStoreInfoById(storeId);
	}
	
	/**
	 * 上架美食
	 */
	@Override
	public int addCuisine(Cuisine cuisine) {
		int result = storeMapper.addCuisine(cuisine);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 判断图片格式是否正确
	 */
	@Override
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
	@Override
	public Cuisine getCuisineInfo(int cuisineId) {
		return storeMapper.getCuisineInfoById(cuisineId);
	}
	
	
	/**
	 * 提交菜肴的修改信息
	 */
	@Override
	public int submitInfo(Cuisine cuisine) {
		int result =  storeMapper.updateCuisineInfo(cuisine);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 提交店铺的修改信息
	 */
	@Override
	public int submitStoreInfo(Store store) {
		int result =  storeMapper.updateStoreInfo(store);
		if(result == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 分页：获得某个页面的数据
	 */
	@Override
	public PageBean<Cuisine> getPageCuisine(int pageNum, int pageSize){
		int totalRecord = storeMapper.getAllCuisineNum();    //获得数据量
		PageBean<Cuisine> pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
		pb.setList(storeMapper.getPageCuisine(pb));
		return pb;
	}	
	
	/**
	 * 修改销量
	 */
	@Override
	public void modifySellCount(int cuisineId, int number) {
		Cuisine cuisnine = storeMapper.selectSellCount(cuisineId);
		int sellCount = cuisnine.getSellCount() + number;
		storeMapper.modifySellCount(sellCount);
	}
	
	
	
	/**
	 * 检出搜索输入是否为空
	 */
	@Override
	public boolean check(String key) {
		if("".equals(key) || key == null || key.equals("%")) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	/**
	 * 搜索
	 */
	@Override
	public PageBean<Cuisine> search(String choose, String key, int pageNum, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageBean<Cuisine> pb = null;
		map.put("key", key);
		map.put("pageSize", pageSize);
		if(choose.equals(Contants.CUISINE)) {
			Store store = storeMapper.getCuisinesNumByCuisineName(key);
			int totalRecord = storeMapper.getCuisinesNumByStoreId(store.getStoreId());
			pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
			int startIndex = pb.getStartIndex();  //获得开始的索引
			map.put("startIndex", startIndex);
			pb.setList(storeMapper.searchByCuisine(map));
		} else {
			int totalRecord = storeMapper.getCuisinesNumByStoreName(key);
			pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
			int startIndex = pb.getStartIndex();  //获得开始的索引
			map.put("startIndex", startIndex);
			pb.setList(storeMapper.searchByStore(map));
		}
		return pb;
	}
	
	
	/**
	 * 分页：获得某个页面的数据
	 */
	@Override
	public PageBean<Cuisine> getPageCuisine(int storeId, int pageNum, int pageSize){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("storeId", storeId);
		map.put("pageSize", pageSize);
		int totalRecord = storeMapper.getUserAllCuisines(storeId);  //获得数据量
		PageBean<Cuisine> pb = new PageBean<Cuisine>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		map.put("startIndex", startIndex);
		pb.setList(storeMapper.getPageCuisineByStore(map));
		return pb;
	}

	/**
	 * 用户申请开店
	 */
	@Override
	public int openStroe(Store store) {
		User user = storeMapper.openStore(store);
		store.setUser(user);
		int result = storeMapper.insertStoreInfo(store);
		if (storeMapper.storeNameIsExist(store.getStoreName()) != 0) {
			return Contants.STORENAME_EXIST_CODE;
		} else if (result == 1) {
			return Contants.SUCCESS_CODE; // 该用户不存在
		}
		return Contants.FAIL_CODE;
	}














}
