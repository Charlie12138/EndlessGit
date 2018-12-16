package com.liqinglin.www.serviceImp;

import java.util.List;

import com.liqinglin.www.mapper.AdminMapper;
import com.liqinglin.www.po.Cuisine;
import com.liqinglin.www.po.PageBean;
import com.liqinglin.www.po.Store;
import com.liqinglin.www.po.User;
import com.liqinglin.www.service.AdminService;
import com.liqinglin.www.util.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImp implements AdminService {

	@Autowired
	AdminMapper adminMapper;
	
	/**
	 * 审核注册
	 * @return
	 */
	@Override
	public List<User> examineRegister(){
		List<User> users = adminMapper.getRegisterUser();
		return users;
	}
	
	/**
	 * 通过注册
	 * @return
	 */
	@Override
	public int agreeRegister(User user) {
		user.setStatus(Contants.PASS_EXAMINE);
		if(adminMapper.agreeRegister(user) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 审核开店请求
	 */
	@Override
	public List<Store> examineOpenStore() {
		List<Store> stores = adminMapper.getApplyingStore(Contants.EXAMINEING);
		return stores;
	}
	
	/**
	 * 通过开店请求
	 */
	@Override
	public int operationOpenStore(Store store) {
		adminMapper.operationOpenStore(store);
		Store store1 = adminMapper.getUserId(store);
		User user = store1.getUser();
		user.setRole(Contants.ROLE_MERCHANT);
		if(adminMapper.modifyUserRole(user) == 1 || adminMapper.getRole(user).getRole() == Contants.ROLE_MERCHANT) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 获得正在请求上架的菜肴
	 */
	@Override
	public List<Cuisine> examineAddCuisines() {
		List<Cuisine> cuisines = adminMapper.getApplyingCuisine(Contants.EXAMINEING);
		return cuisines;
	}
	
	/**
	 * 对上架请求进行操作
	 */
	@Override
	public int operationAdd(Cuisine cuisine) {
		if(adminMapper.operationAdd(cuisine) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	/**
	 * 下架食品
	 */
	@Override
	public int deleteCuisine(int cuisineId) {
		if(adminMapper.deleteCuisine(cuisineId) == 1) {
			return Contants.SUCCESS_CODE;
		}
		return Contants.FAIL_CODE;
	}
	
	
	/**
	 * 获得某个页面的店铺
	 */
	@Override
	public PageBean<Store> getPageStores(int pageNum, int pageSize){
		int totalRecord  = adminMapper.getAllStoresNum( Contants.EXAMINEING); //获得数据量
		PageBean<Store> pb = new PageBean<Store>(pageNum, pageSize, totalRecord);
		int startIndex = pb.getStartIndex();  //获得开始的索引
		pb.setList(adminMapper.getPageStore(pb));
		return pb;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
