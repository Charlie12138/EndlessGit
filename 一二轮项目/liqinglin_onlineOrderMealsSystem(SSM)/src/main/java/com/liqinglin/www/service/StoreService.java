package com.liqinglin.www.service;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;

public interface StoreService {
	Store getStoreInfo(int storeId);

	int addCuisine(Cuisine cuisine);

	int imageFormatIsRight(String filename);

	Cuisine getCuisineInfo(int cuisineId);

	int submitInfo(Cuisine cuisine);

	int submitStoreInfo(Store store);

	PageBean<Cuisine> getPageCuisine(int pageNum, int pageSize);

	void modifySellCount(int cuisineId, int number);

	boolean check(String key);

	PageBean<Cuisine> search(String choose, String key, int pageNum, int pageSize);

	PageBean<Cuisine> getPageCuisine(int storeId, int pageNum, int pageSize);

	int openStroe(Store store);
}
