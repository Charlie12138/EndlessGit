package com.liqinglin.www.service;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;

import java.util.List;

public interface AdminService {
	List<User> examineRegister();

	int agreeRegister(User user);

	List<Store> examineOpenStore();

	int operationOpenStore(Store store);

	List<Cuisine> examineAddCuisines();

	int operationAdd(Cuisine cuisine);

	int deleteCuisine(int cuisineId);

	PageBean<Store> getPageStores(int pageNum, int pageSize);
}
