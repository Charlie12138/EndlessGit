package com.liqinglin.www.po;

public class Course {
	private int courseId;
	private String courseName;
	private int teacherId;
	private String teacherName;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Course() {
		super();
	}

	public Course(int courseId, String courseName, int teacherId, String teacherName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", teacherId=" + teacherId
				+ ", teacherName=" + teacherName + "]";
	}
}
