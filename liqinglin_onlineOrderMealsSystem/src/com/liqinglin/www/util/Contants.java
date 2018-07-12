package com.liqinglin.www.util;

public class Contants {
	public final static int PASSWORD_FORMAT_RIGHT_CODE = 1; // 密码格式正确
	public final static int PASSWORD_FORMAT_ERROR_CODE = 0; // 密码格式错误
	public final static int CHECK_PASSWORD_RIGHT_CODE = 1; // 两次密码一致
	public final static int CHECK_PASSWORD_ERROR_CODE = 0; // 两次密码不一致
	public final static int EMAIL_FORMAT_RIGHT_CODE = 1; // 邮箱格式正确
	public final static int EMAIL_FORMAT_ERROR_CODE = 0; // 邮箱格式错误
	public final static int USERNAME_FORMAT_RIGHT_CODE = 1; // 用户名格式正确
	public final static int USERNAME_FORMAT_ERROR_CODE = 0; // 用户名格式错误
	public final static int PHONE_FORMAT_RIGHT_CODE = 1; // 用户名格式正确
	public final static int PHONE_FORMAT_ERROR_CODE = 0; // 用户名格式错误
	
	public final static String PASSWORD_FORMAT_RIGHT = " 密码格式正确"; 
	public final static String PASSWORD_FORMAT_ERROR = "密码格式错误"; 
	public final static String CHECK_PASSWORD_RIGHT = "两次密码一致";
	public final static String CHECK_PASSWORD_ERROR = "密码两次不一致";
	public final static String USERNAME_FORMAT_RIGHT = "用户名格式正确";	
	public final static String USERNAME_FORMAT_ERROR = "用户名格式错误";
	
	public final static int USERNAME_EXIST_CODE = 0; // 用户存在
	public final static int USERNAME_NOT_EXIST_CODE = 2; // 用户不存在
	public final static int SUCCESS_CODE = 1; // 操作成功
	public final static int FAIL_CODE = 3; // 操作失败
	
	public final static String USERNAME_EXIST = "该用户名已被注册";
	public final static String USERNAME_NOT_EXIST = "用户不存在";
	public final static String WAIT_FOR_EXAMINEING = "等待管理员审核";
	public final static String SUCCESS= "操作成功";
	public final static String FAIL = "操作失败";
	
	public final static String LOGIN_SUCCEESS = "成功登录";
	public final static String LOGIN_FAIL = "登录失败";
	public final static String PASSWORD_ERROR = "密码错误";
	public final static int NOT_EXAMINE_CODE= 4; //审核中
	public final static String REGISTER_EXAMINEING = "请等待审核通过";
	
	public final static int ROLE_SELECT_ERROR_CODE = 5; //登录时选择了错误权限
	public final static String ROLE_SELECT_ERROR = "您登录角色选错咯";
	public final static String ROLE_SELECT = "请选择角色";
	
	public final static String ROLE_USER = "1";//普通用户
	public final static String ROLE_MERCHANT = "2";//店家
	public final static String ROLE_ADMIN = "3";//管理员
	
	public final static int STORENAME_EXIST_CODE = 0; // 店铺名已经存在
	public final static String STORENAME_EXIST = "该店铺名已被注册";
	public final static String OPENSTORE_EXAMINEING = "申请已提交，请等待审核通过";
	public final static int EXAMINEING = 0;        //审核中
	public final static int PASS_EXAMINE = 1;	   //审核通过
	public final static int REJECT_EXAMINE = 2;   //没通过审核
	public final static int FORCE_CLOSE = 3;       //被管理员强制关闭

	public final static int IMAGE_FORMAT_IS_RIGHT = 1; // 图片格式正确
	public final static int IMAGE_FORMAT_IS_ERROR_CODE = 0; // 图片格式不正确
	public final static String IMAGE_FORMAT_IS_ERROR = "图片格式不正确";
	public final static String ADDCUISINE_SUCCEESS = "上架商品成功，2秒后跳转到主页";   //上架商品成功
	public final static String ADDCUISINE_FAIL = "上架商品失败，2秒后跳转到主页";      //上架商品失败

	public final static String MODIFYCUISINE_SUCCEESS = "修改商品成功，2秒后跳转到主页";   //修改商品成功
	public final static String MODIFYCUISINE_FAIL = "修改商品失败，2秒后跳转到主页";      //修改商品失败

	public final static String MODIFY_STORE_SUCCEESS = "修改信息成功";   //修改店铺成功
	public final static String MODIFY_STORE_FAIL = "修改信息失败";      //修改店铺失败
	
	public final static int ORDER_STATUS_A = 0;//订单状态：未发货
	public final static int ORDER_STATUS_B = 1;//订单状态：已发货，未收货
	public final static int ORDER_STATUS_C = 2;//订单状态：已收货
	public final static int ORDER_STATUS_D = 3;//订单状态：已取消
	
	public final static int STORE_ORDER = 1;//返回商店的订单
	public final static int USER_ORDER = 2;//返回用户的订单
	
	public final static String ADD_TO_CART_SUCCESS = "成功加入购物车";
	
	public final static String CUISINE = "cuisine";//搜索选中的时美食
	public final static String STORE = "store";//搜索选中的时店铺

}
