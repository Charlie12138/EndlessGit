package com.liqinglin.www.service;

import java.util.List;

import com.liqinglin.www.dao.CourseDao;
import com.liqinglin.www.po.Course;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;
import com.liqinglin.www.util.ValidateUtil;

public class CourseService {
	private CourseDao courseDao = new CourseDao();

	/**
	 * 添加科目
	 * 
	 * @param course
	 * @return
	 */
	public Msg addCourse(Course course) {
		if (ValidateUtil.isInvalidString("" + course.getCourseId())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString(course.getCourseName())) {
			return new Msg(Constants.NULL, null);
		} else if (ValidateUtil.isInvalidString("" + course.getTeacherId())) {
			return new Msg(Constants.NULL, null);
		}
		Msg msg = courseDao.addCourse(course);
		return msg;
	}

	/**
	 * 删除科目
	 * 
	 * @param courseName
	 * @return
	 */
	public Msg deleteCourse(String courseName) {
		if (ValidateUtil.isInvalidString(courseName)) {
			return new Msg(Constants.NULL, null);
		}
		Msg msg = courseDao.deleteCourse(courseName);
		return msg;
	}

	/**
	 * 搜索科目
	 * 
	 * @param name
	 * @return
	 */
	public List<Course> searchCourse(String name) {

		if (ValidateUtil.isInvalidString(name)) { // 判断是否为空
			return null;
		} else if (name.equals("%"))
			name = "不能输入%"; // 避免因为输入%而输出所有数据
		List<Course> list = (List<Course>) courseDao.searchCourse(name);
		/**
		 * 防止list是空的
		 */
		if (list.toString() != "[]") {
			return list;
		}
		return null;
	}

	/**
	 * 修改科目
	 * 
	 * @param name
	 * @return
	 */
	public Msg modifyCouseStep1(String name) {
		if (ValidateUtil.isInvalidString(name)) { // 判断是否为空
			return new Msg(Constants.NULL, null);
		}
		Msg msg = courseDao.modifyCourseStep1(name);
		return msg;
	}

	public Msg modifyCourseStep2(Course course) {
		return courseDao.modifyCourseStep2(course);
	}

}
