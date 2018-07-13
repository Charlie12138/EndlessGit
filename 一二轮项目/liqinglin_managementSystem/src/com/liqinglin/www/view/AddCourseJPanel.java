package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

/**
 * 添加科目的JPanel
 */
public class AddCourseJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel courseIdLb = new JLabel("科目ID");
	private JTextField courseIdText = new JTextField();
	private JLabel courseNameLb = new JLabel("科目名称");
	private JTextField courseNameText = new JTextField();
	private JLabel teacherIdLb = new JLabel("教师id");
	private JTextField teacherIdText = new JTextField();
	private JButton btnConfirm = new JButton("确定");
	private JButton btnOff = new JButton("取消");
	private JLabel commentlb = new JLabel("评语:");
	private JTextArea comment = new JTextArea(3, 20);

	public JLabel getCommentlb() {
		return commentlb;
	}

	public void setCommentlb(JLabel commentlb) {
		this.commentlb = commentlb;
	}

	public JTextArea getComment() {
		return comment;
	}

	public void setComment(JTextArea comment) {
		this.comment = comment;
	}

	public JLabel getTeacherIdLb() {
		return teacherIdLb;
	}

	public void setTeacherIdLb(JLabel teacherIdLb) {
		this.teacherIdLb = teacherIdLb;
	}

	public JTextField getTeacherIdText() {
		return teacherIdText;
	}

	public void setTeacherIdText(JTextField teacherIdText) {
		this.teacherIdText = teacherIdText;
	}

	public JLabel getCourseIdLb() {
		return courseIdLb;
	}

	public void setCourseIdLb(JLabel courseIdLb) {
		this.courseIdLb = courseIdLb;
	}

	public JTextField getCourseIdText() {
		return courseIdText;
	}

	public void setCourseIdText(JTextField courseIdText) {
		this.courseIdText = courseIdText;
	}

	public JButton getBtnConfirm() {
		return btnConfirm;
	}

	public void setBtnConfirm(JButton btnConfirm) {
		this.btnConfirm = btnConfirm;
	}

	public JButton getBtnOff() {
		return btnOff;
	}

	public void setBtnOff(JButton btnOff) {
		this.btnOff = btnOff;
	}

	public JLabel getCourseNameLb() {
		return courseNameLb;
	}

	public void setCourseNameLb(JLabel courseNameLb) {
		this.courseNameLb = courseNameLb;
	}

	public JTextField getCourseNameText() {
		return courseNameText;
	}

	public void setCourseNameText(JTextField courseNameText) {
		this.courseNameText = courseNameText;
	}

	public AddCourseJPanel() {
		set();
		add(courseIdLb);
		add(courseIdText);
		add(courseNameLb);
		add(courseNameText);
		add(btnConfirm);
		add(btnOff);
		add(teacherIdLb);
		add(teacherIdText);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		courseIdLb.setBounds(280, 80, 100, 30);
		courseIdLb.setFont(font);
		courseIdText.setBounds(380, 80, 190, 30);
		courseIdText.setFont(font);
		courseNameLb.setBounds(280, 130, 70, 30);
		courseNameLb.setFont(font);
		courseNameText.setBounds(380, 130, 190, 30);
		courseNameText.setFont(font);
		btnConfirm.setBounds(280, 360, 80, 40);
		btnConfirm.setFont(font);
		btnOff.setBounds(500, 360, 80, 40);
		btnOff.setFont(font);
		teacherIdLb.setBounds(280, 180, 70, 30);
		teacherIdLb.setFont(font);
		teacherIdText.setBounds(380, 180, 190, 30);
		teacherIdText.setFont(font);
		comment.setBounds(380, 230, 190, 120);
		comment.setLineWrap(true);
		comment.setFont(font);
		commentlb.setBounds(280, 230, 70, 30);
		commentlb.setFont(font);
	}

}
