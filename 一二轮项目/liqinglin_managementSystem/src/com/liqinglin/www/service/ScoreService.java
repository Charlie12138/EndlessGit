package com.liqinglin.www.service;

import com.liqinglin.www.dao.ScoreDao;
import com.liqinglin.www.po.Score;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;
import com.liqinglin.www.util.ValidateUtil;

public class ScoreService {
	private ScoreDao scoreDao = new ScoreDao();

	/**
	 * 设置成绩
	 * 
	 * @param name
	 * @param courseName
	 * @param score
	 * @param comment
	 * @return
	 */
	public Msg setScore(String name, String courseName, double score, String comment) {
		if (ValidateUtil.isInvalidString(name)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(courseName)) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString("" + score)) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(comment)) {
			comment = null;
		}
		return scoreDao.setScore(name, courseName, score, comment);
	}

	/**
	 * 删除成绩
	 * 
	 * @param name
	 * @param courseName
	 * @return
	 */
	public Msg deleteScore(String name, String courseName) {
		if (ValidateUtil.isInvalidString(name)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(courseName)) {
			return new Msg(Constants.NULL, null);
		}
		return scoreDao.deleteScore(name, courseName);
	}

	/**
	 * 搜索成绩
	 * 
	 * @param name
	 * @param courseName
	 * @return
	 */
	public Msg searchScore(String name, String courseName) {
		if (ValidateUtil.isInvalidString(name)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(courseName)) {
			return new Msg(Constants.NULL, null);
		}
		return scoreDao.searchScore(name, courseName);
	}

	/**
	 * 修改成绩
	 * 
	 * @param score
	 * @return
	 */
	public Msg modifyScore(Score score) {
		if (ValidateUtil.isInvalidString("" + score)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		}
		return scoreDao.modifyScore(score);
	}
}
