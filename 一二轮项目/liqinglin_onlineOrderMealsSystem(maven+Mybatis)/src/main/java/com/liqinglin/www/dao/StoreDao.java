package com.liqinglin.www.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.util.MybatisUtil;
import com.liqinglin.www.util.StoreDaoContants;
import org.apache.ibatis.session.SqlSession;

public class StoreDao {

	/**
	 * 获得某个店铺的信息
	 * @param storeId
	 * @return
	 */
	public Store getStoreInfoById(int storeId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_STOREINFO_BY_ID;
		Store store = session.selectOne(statement, storeId);
		session.close();
		return store;
	}

	public Store getStoreInfoByName(String  storeName) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_STOREINFO_BY_NAME;
		Store store = session.selectOne(statement, storeName);
		session.close();
		return store;
	}


	/**
	 * 上架美食
	 * @param cuisine
	 * @return
	 */

	public int addCuisine(Cuisine cuisine) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.ADDCUISINE;
		int result = session.insert(statement, cuisine);
		session.close();
		return result;
	}


	/**
	 * 得到菜肴信息
	 * @param cuisineId
	 * @return
	 */
	public Cuisine getCuisineInfo(int cuisineId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_CUISINEINFO;
		Cuisine cuisine = session.selectOne(statement, cuisineId);
		session.close();
		return  cuisine;
	}


	/**
	 * 修改菜肴信息
	 * @param cuisine
	 * @return
	 */

	public int updateCuisineInfo(Cuisine cuisine) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.UPDATE_CUISINE;
		int result = session.update(statement, cuisine);
		session.close();
		return result;
	}


	/**
	 * 修改店铺信息
	 * @param store
	 * @return
	 */
	public int updateStoreInfo(Store store) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.UPDATE_STORE;
		int result = session.update(statement, store);
		session.close();
		return result;
	}


	/**
	 * 获得所有菜肴的数量
	 * @return
	 */
	public int getAllCuisinesNum() {
	SqlSession session = MybatisUtil.getSession();
	String statement = StoreDaoContants.GET_CUISINENUM;
	int tatolRecord = session.selectOne(statement);
	session.close();
	return  tatolRecord;
	}


	/**
	 * 获得某一页所需要的数据
	 * @param pageBean
	 * @return
	 */
	public List<Cuisine> getPageCuisines(PageBean<Cuisine> pageBean) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_PAGE_CUISINE;
		List<Cuisine> cuisines = session.selectList(statement, pageBean);
		session.close();
		return cuisines;
	}


	/**
	 * 修改销量
	 * @param cuisineId
	 * @param number
	 * @return
	 */

	public int modifySellCount(int cuisineId, int number) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.SELECT_SELLCOUNT;
		Cuisine cuisine = session.selectOne(statement, cuisineId);
		int sellCount = cuisine.getSellCount() + number;
		statement = StoreDaoContants.MODIFY_SELLCOUNNT;
		int result = session.update(statement, sellCount);
		session.close();
		return  result;
	}


	/**
	 * 获得搜索出的记录数
	 * @param key
	 * @return
	 */
	public int getCuisinesNumByCuisineName(String key) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_CUISINENUM_BY_CUISINENAME;
		int totalRecord = session.selectOne(statement, "%" + key + "%");
		session.close();
		return totalRecord;
	}

	public int getCuisinesNumByStoreName(String key) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_STOREINFO_BY_NAME ;
		Store store = session.selectOne(statement, key);
		int storeId = store.getStoreId();
		statement = StoreDaoContants.GET_CUISINENUM_BY_STOREID;
		int totalRecord = session.selectOne(statement, storeId);
		session.close();
		return  totalRecord;
	}


	/**
	 * 分页准备
	 * @param key
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Cuisine> searchByCuisine(String key, int startIndex, int pageSize) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.SEARCH_BY_CUISINE;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "%" + key + "%");
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		List<Cuisine> cuisines = session.selectList(statement, map);
		session.close();
		return cuisines;
	}



	/**
	 * 分页准备
	 * @param key
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */

	public List<Cuisine> searchByStore(String key, int startIndex, int pageSize) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.SEARCH_BY_STORE;
		int storeId = getStoreInfoByName(key).getStoreId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		List<Cuisine> cuisines = session.selectList(statement, map);
		session.close();
		return cuisines;
	}


	/**
	 * 得到某个店铺的所有菜肴的数量
	 * @param storeId
	 * @return
	 */

	public int getUserAllCuisines(int storeId) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.GET_ALL_CUISINE;
		int result = session.selectOne(statement, storeId);
		session.close();
		return  result;
	}


	/**
	 * 分页：获得某一页所需要的数据
	 * @param storeId
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */

	public List<Cuisine> getPageCuisine(int storeId, int startIndex, int pageSize) {
		SqlSession session = MybatisUtil.getSession();
		String statement = StoreDaoContants.SEARCH_BY_STORE;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		List<Cuisine> cuisines = session.selectList(statement, map);
		session.close();
		return cuisines;
	}
}
