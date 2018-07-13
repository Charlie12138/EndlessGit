package com.liqinglin.www.service;

import java.util.Map;

import com.liqinglin.www.dao.CommentDao;
import com.liqinglin.www.util.ValidateUtil;

public class CommentService {
	CommentDao commentDao = new CommentDao();

	public Map<String, String> comment(String userName) {
		if (ValidateUtil.isInvalidString(userName)) {
			return null;
		}
		Map<String, String> map = commentDao.comment(userName);
		return map;
	}
}
