package com.liqinglin.www.po;

import java.sql.Timestamp;

public class Store {
	private int storeId;//店铺id
	private String storeName;//店铺名字
	private int status;//店铺状态
	private int shopkeeperId;//拥有者的id
	private String address;//店铺地址
	private String phone;//店铺电话
	private Timestamp createTime;//创建时间
	private String shopkeeperName;//店主姓名
	private String username;//店主的用户名
	private String storeDescription;//店铺描述
	
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStoreDescription() {
		return storeDescription;
	}
	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}
	public String getShopkeeperName() {
		return shopkeeperName;
	}
	public void setShopkeeperName(String shopkeeperName) {
		this.shopkeeperName = shopkeeperName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getShopkeeperId() {
		return shopkeeperId;
	}
	public void setShopkeeperId(int shopkeeperId) {
		this.shopkeeperId = shopkeeperId;
	}
	
}
