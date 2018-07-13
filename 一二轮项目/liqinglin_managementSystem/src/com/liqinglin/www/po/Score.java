package com.liqinglin.www.po;

public class Score {
	private int studentId;
	private int courseId;
	private String studentName;
	private String courseName;
	private double score;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Score() {
		super();
	}

	public Score(int studentId, int courseId, String studentName, String courseName, double score) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
		this.studentName = studentName;
		this.courseName = courseName;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [studentId=" + studentId + ", courseId=" + courseId + ", score=" + score + "]";
	}

}
