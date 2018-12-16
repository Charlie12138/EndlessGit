package com.liqinglin.www.mapper;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;

import java.util.List;

public interface AdminMapper {
	/**
	 * 获得正在注册的人
	 * @return
	 */
	List<User> getRegisterUser();

	/**
	 * 审核通过正在注册的人
	 */
	int agreeRegister(User user);

	/**
	 * 获得正在申请开的店铺
	 *
	 * @return
	 */
	List<Store> getApplyingStore(int status);

	/**
	 * 对开店请求的操作
	 * 修改店铺状态
	 */
	void operationOpenStore(Store store);
	/**
	 * 对开店请求的操作
	 * 获得用户id
	 */
	Store getUserId(Store store);
	/**
	 * 对开店请求的操作
	 * 设置店家身份
	 */
	int modifyUserRole(User user);

	/**
	 * 获得正在申请上架的菜
	 *
	 * @return
	 */
	List<Cuisine> getApplyingCuisine(int status);

	/**
	 * 审核通过上架请求
	 */
	int operationAdd(Cuisine cuisine);


	/**
	 * 下架食品
	 */
	int deleteCuisine(int cuisineId);

	/**
	 * 店铺分页：获得某一页所需要的数据
	 */
	List<Store> getPageStore(PageBean<Store> pageBean);

	/**
	 * 得到所有店铺数量
	 */
	int getAllStoresNum(int status);

	/**
	 * 查找这个用户是否有某权限
	 * @param user
	 * @return
	 */
	User getRole(User user);
}
