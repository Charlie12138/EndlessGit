package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

public class DeleteStudentJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel deLb = new JLabel("请输入需要删除的学生姓名：");
	private JTextField nameText = new JTextField();
	private JButton btnConfirm = new JButton("确认删除");
	private JButton btnOff = new JButton("取消");

	public JLabel getDeLb() {
		return deLb;
	}

	public void setDeLb(JLabel deLb) {
		this.deLb = deLb;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
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

	public DeleteStudentJPanel() {
		set();
		add(deLb);
		add(nameText);
		add(btnConfirm);
		add(btnOff);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		deLb.setBounds(220, 150, 200, 30);
		deLb.setFont(font);
		nameText.setBounds(420, 150, 190, 30);
		nameText.setFont(font);
		btnOff.setBounds(470, 300, 100, 40);
		btnOff.setFont(font);
		btnConfirm.setBounds(250, 300, 100, 40);
		btnConfirm.setFont(font);
	}
}
