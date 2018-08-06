package com.liqinglin.www.dao;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;

import java.util.List;
import java.util.Map;

public interface StoreMapper {
	/**
	 * 获得某个店铺的信息
	 * @param storeId
	 * @return
	 */
	Store getStoreInfoById(int storeId);

	Store getStoreInfoByName(String storeName);

	/**
	 * 上架美食
	 * @param cuisine
	 * @return
	 */
	int addCuisine(Cuisine cuisine);

	/**
	 * 得到菜肴信息
	 * @param cuisineId
	 * @return
	 */
	Cuisine getCuisineInfoById(int cuisineId);

	/**
	 * 修改菜肴信息
	 * @param cuisine
	 * @return
	 */
	int updateCuisineInfo(Cuisine cuisine);

	/**
	 * 修改店铺信息
	 * @param store
	 * @return
	 */
	int updateStoreInfo(Store store);

	/**
	 * 获得所有菜肴的数量
	 * @return
	 */
	int getAllCuisineNum();

	/**
	 * 获得某一页所需要的数据
	 * @param pageBean
	 * @return
	 */
	List<Cuisine> getPageCuisine(PageBean<Cuisine> pageBean);

	/**
	 * 修改销量
	 */
	Cuisine selectSellCount(int cuisineId);

	int modifySellCount(int number);

	/**
	 * 获得搜索出的记录数
	 * @param key
	 * @return
	 */
	Store getCuisinesNumByCuisineName(String key);

	int getCuisinesNumByStoreId(int storeId);

	int getCuisinesNumByStoreName(String key);

	/**
	 * 分页准备
	 */
	List<Cuisine> searchByCuisine(Map<String, Object> map);

	/**
	 * 分页准备
	 */
	List<Cuisine> searchByStore(Map<String, Object> map);

	/**
	 * 得到某个店铺的所有菜肴的数量
	 * @param storeId
	 * @return
	 */
	int getUserAllCuisines(int storeId);

	/**
	 * 分页：获得某一页所需要的数据(同一个店铺)
	 */
	List<Cuisine> getPageCuisineByStore(Map<String, Integer> map);

	/**
	 * 检查店铺名是否已经注册
	 * @param storeName
	 * @return
	 */
	int storeNameIsExist(String storeName);

	/**
	 * 用户开店信息录入数据库
	 * @param store
	 * @return
	 */
	User openStore(Store store);

	int insertStoreInfo(Store store);


}
