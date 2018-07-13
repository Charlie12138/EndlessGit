package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

public class ModifyStudentJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btnConfirm = new JButton("确认");
	private JButton btnOff = new JButton("取消");
	private JLabel modificationLable = new JLabel("请输入要修改的学生姓名：");
	private JTextField nameText = new JTextField();

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

	public JLabel getModificationLable() {
		return modificationLable;
	}

	public void setModificationLable(JLabel modificationLable) {
		this.modificationLable = modificationLable;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
	}

	public ModifyStudentJPanel() {
		set();
		add(btnConfirm);
		add(btnOff);
		add(modificationLable);
		add(nameText);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		btnConfirm.setBounds(350, 300, 100, 40);
		btnConfirm.setFont(font);
		btnOff.setBounds(570, 300, 100, 40);
		btnOff.setFont(font);
		modificationLable.setBounds(320, 150, 200, 30);
		modificationLable.setFont(font);
		nameText.setBounds(520, 150, 190, 30);
		nameText.setFont(font);
	}
}
