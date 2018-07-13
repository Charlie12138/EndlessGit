package com.liqinglin.www.util;

import java.util.UUID;

public class OrderNumUtil {
	public static String getOrderNum() {
		int num = UUID.randomUUID().toString().hashCode();
		if(num <0) {
			num = -num;
		}
		 return String.format("%015d", num);
	}
}
