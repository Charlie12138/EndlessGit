package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteCourseJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel courseNameLb = new JLabel("请输入要删除的科目名称");
	private JTextField courseNameText = new JTextField();
	private JButton btnConfirm = new JButton("确定");
	private JButton btnOff = new JButton("取消");

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

	public DeleteCourseJPanel() {
		set();
		add(courseNameLb);
		add(courseNameText);
		add(btnConfirm);
		add(btnOff);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		courseNameLb.setBounds(200, 180, 200, 30);
		courseNameLb.setFont(font);
		courseNameText.setBounds(380, 180, 190, 30);
		courseNameText.setFont(font);
		btnConfirm.setBounds(300, 280, 80, 40);
		btnConfirm.setFont(font);
		btnOff.setBounds(500, 280, 80, 40);
		btnOff.setFont(font);
	}

};
