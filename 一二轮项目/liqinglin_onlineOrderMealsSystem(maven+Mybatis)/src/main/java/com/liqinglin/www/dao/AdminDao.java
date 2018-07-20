package com.liqinglin.www.dao;

import java.util.List;

import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.util.AdminDaoContants;
import com.liqinglin.www.util.Contants;
import com.liqinglin.www.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class AdminDao {

	/**
	 * 获得正在注册的人
	 * 
	 * @return
	 */
	public List<User> getRegiserUser() {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.GETREGISTERUSER;
		List<User> users = session.selectList(statement);
		session.close();
		return users;
	}




	/**
	 * 审核通过正在注册的人
	 */

	public int agreeRegister(User user) {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.AGREEREGISTER;
		user.setStatus(1);
		int result = session.update(statement, user);
		session.close();
		return result;
	}



	/**
	 * 获得正在申请开的店铺
	 * 
	 * @return
	 */

	public List<Store> getStore() {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.GETSTORE;
		List<Store> stores = session.selectList(statement, Contants.EXAMINEING);
		session.close();
		return  stores;
	}



	/**
	 * 对开店请求的操作
	 */

	public int operationOpenStore(Store store) {
		SqlSession session = MybatisUtil.getSession();
		/**
		 * 修改店铺状态
		 */
		String statement = AdminDaoContants.OPERATIONOPENSTORE;
		int result = session.update(statement, store);
		/**
		 * 获得用户id
		 */
		statement = AdminDaoContants.GETUSERID;
		Store store1 = session.selectOne(statement, store);
		User user = store1.getUser();
		/**
		 * 设置店家身份
		 */
		statement = AdminDaoContants.MODIFYUSERROLE;
		user.setRole(Contants.ROLE_MERCHANT);
		session.update(statement, user);
		session.close();
		return result;
	}



	/**
	 * 获得正在申请上架的菜
	 * 
	 * @return
	 */

	public List<Cuisine> getCuisine() {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.GETCUISINE;
		List<Cuisine> cuisines = session.selectList(statement, Contants.EXAMINEING);
		session.close();
		return cuisines;
	}



	/**
	 * 审核通过上架请求
	 */

	public int operationAdd(Cuisine cuisine) {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.OPERATIONADD;
		int result = session.update(statement, cuisine);
		session.close();
		return result;
	}


	/**
	 * 下架食品
	 */

	public int deleteCuisine(int cuisineId) {
		SqlSession session = MybatisUtil.getSession();
		String statement= AdminDaoContants.DELETECUISINE;
		int result = session.delete(statement, cuisineId);
		session.close();
		return  result;
	}



	/**
	 * 店铺分页：获得某一页所需要的数据
	 */

	public List<Store> getPageStore(PageBean<Store> pageBean) {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.GETPAGESTORE;
		List<Store> stores = session.selectList(statement, pageBean);
		session.close();
		return stores;
	}



	/**
	 * 得到所有店铺数量
	 */

	public int getAllStoresNum() {
		SqlSession session = MybatisUtil.getSession();
		String statement = AdminDaoContants.GETALLSTORESNUM;
		int totalRecord = session.selectOne(statement, Contants.EXAMINEING);
		session.close();
		return totalRecord;
	}

}
