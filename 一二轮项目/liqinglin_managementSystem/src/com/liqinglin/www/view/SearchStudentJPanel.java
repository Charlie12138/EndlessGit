package com.liqinglin.www.view;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchStudentJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField nameText = new JTextField();
	private JButton btnConfirm = new JButton("确认");
	private JButton btnOff = new JButton("取消");
	private JLabel searchLabel = new JLabel("请输入需要查找的学生姓名：");

	public JLabel getSearchLabel() {
		return searchLabel;
	}

	public void setSearchLabel(JLabel searchLabel) {
		this.searchLabel = searchLabel;
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

	public SearchStudentJPanel() {
		set();
		add(searchLabel);
		add(nameText);
		add(btnConfirm);
		add(btnOff);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		searchLabel.setBounds(220, 150, 200, 30);
		searchLabel.setFont(font);
		nameText.setBounds(420, 150, 190, 30);
		nameText.setFont(font);
		btnOff.setBounds(470, 300, 100, 40);
		btnOff.setFont(font);
		btnConfirm.setBounds(250, 300, 100, 40);
		btnConfirm.setFont(font);
	}

}
