package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

public class TeacherOperateEntranceJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btna = new JButton("添加学生");
	private JButton btnDelete = new JButton("删除学生");
	private JButton btnSearch = new JButton("查找学生");
	private JButton btnModi = new JButton("修改学生信息");
	private JButton btnAddCourse = new JButton("添加科目");
	private JButton btnDeleteCourse = new JButton("删除科目");
	private JButton btnSearchCourse = new JButton("搜索科目");
	private JButton btnModiCourse = new JButton("修改科目");
	private JButton btnSetScore = new JButton("为学生设置成绩");
	private JButton btnDeleteScore = new JButton("删除成绩");
	private JButton btnSearchScore = new JButton("查询成绩");
	private JButton btnModifiScore = new JButton("修改成绩");

	public JButton getBtnModifiScore() {
		return btnModifiScore;
	}

	public void setBtnModifiScore(JButton btnModifiScore) {
		this.btnModifiScore = btnModifiScore;
	}

	public JButton getBtnSearchScore() {
		return btnSearchScore;
	}

	public void setBtnSearchScore(JButton btnSearchScore) {
		this.btnSearchScore = btnSearchScore;
	}

	public JButton getBtnDeleteScore() {
		return btnDeleteScore;
	}

	public void setBtnDeleteScore(JButton btnDeleteScore) {
		this.btnDeleteScore = btnDeleteScore;
	}

	public JButton getBtnSetScore() {
		return btnSetScore;
	}

	public void setBtnSetScore(JButton btnSetScore) {
		this.btnSetScore = btnSetScore;
	}

	public JButton getBtnModiCourse() {
		return btnModiCourse;
	}

	public void setBtnModiCourse(JButton btnModiCourse) {
		this.btnModiCourse = btnModiCourse;
	}

	public JButton getBtnSearchCourse() {
		return btnSearchCourse;
	}

	public void setBtnSearchCourse(JButton btnSearchCourse) {
		this.btnSearchCourse = btnSearchCourse;
	}

	public JButton getBtnDeleteCourse() {
		return btnDeleteCourse;
	}

	public void setBtnDeleteCourse(JButton btnDeleteCourse) {
		this.btnDeleteCourse = btnDeleteCourse;
	}

	public JButton getBtnAddCourse() {
		return btnAddCourse;
	}

	public void setBtnAddCourse(JButton btnAddCourse) {
		this.btnAddCourse = btnAddCourse;
	}

	public JButton getBtnModi() {
		return btnModi;
	}

	public void setBtnModi(JButton btnModi) {
		this.btnModi = btnModi;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public JButton getBtna() {
		return btna;
	}

	public void setBtna(JButton btna) {
		this.btna = btna;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public TeacherOperateEntranceJPanel() {
		super();
		set();
		add(btna);
		add(btnDelete);
		add(btnSearch);
		add(btnModi);
		add(btnAddCourse);
		add(btnDeleteCourse);
		add(btnSearchCourse);
		add(btnModiCourse);
		add(btnSetScore);
		add(btnDeleteScore);
		add(btnSearchScore);
		add(btnModifiScore);
		setVisible(true);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		btna.setBounds(130, 10, 100, 40);
		btna.setFont(font);
		btnDelete.setBounds(270, 10, 100, 40);
		btnDelete.setFont(font);
		btnSearch.setBounds(410, 10, 100, 40);
		btnSearch.setFont(font);
		btnModi.setBounds(550, 10, 150, 40);
		btnModi.setFont(font);
		btnAddCourse.setBounds(130, 70, 100, 40);
		btnAddCourse.setFont(font);
		btnDeleteCourse.setBounds(270, 70, 100, 40);
		btnDeleteCourse.setFont(font);
		btnSearchCourse.setBounds(410, 70, 100, 40);
		btnSearchCourse.setFont(font);
		btnModiCourse.setBounds(550, 70, 100, 40);
		btnModiCourse.setFont(font);
		btnSetScore.setBounds(130, 130, 150, 40);
		btnSetScore.setFont(font);
		btnDeleteScore.setBounds(320, 130, 100, 40);
		btnDeleteScore.setFont(font);
		btnSearchScore.setBounds(460, 130, 100, 40);
		btnSearchScore.setFont(font);
		btnModifiScore.setBounds(600, 130, 100, 40);
		btnModifiScore.setFont(font);
	}
}
