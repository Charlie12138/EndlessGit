package com.liqinglin.www.service;

public interface DataService {
	int isUserNameRight(String username);

	int isRightPassword(String password);

	int isConfirmRight(String password, String repassword);

	boolean isSelected(String role);
}
