package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

public class LoginJPanel extends JPanel {
	/**
	 * 登录主界面的组件
	 */
	private static final long serialVersionUID = 1L;
	private JLabel unLb = new JLabel("用户名");
	private JTextField unt;
	private JLabel pwLb = new JLabel("密码");
	private JTextField pwt;
	private JButton btnlo = new JButton("登录");
	private JLabel idenLb = new JLabel("选择身份");
	private JComboBox<String> idenBox = new JComboBox<String>();

	public JLabel getIdenLb() {
		return idenLb;
	}

	public void setIdenLb(JLabel idenLb) {
		this.idenLb = idenLb;
	}

	public JComboBox<String> getIdenBox() {
		return idenBox;
	}

	public void setIdenBox(JComboBox<String> idenBox) {
		this.idenBox = idenBox;
	}

	public JLabel getUnLb() {
		return unLb;
	}

	public void setUnLb(JLabel unLb) {
		this.unLb = unLb;
	}

	public JTextField getUnt() {
		return unt;
	}

	public void setUnt(JTextField unt) {
		this.unt = unt;
	}

	public JLabel getPwLb() {
		return pwLb;
	}

	public void setPwLb(JLabel pwLb) {
		this.pwLb = pwLb;
	}

	public JTextField getPwt() {
		return pwt;
	}

	public void setPwt(JTextField pwt) {
		this.pwt = pwt;
	}

	public JButton getBtnlo() {
		return btnlo;
	}

	public void setBtnlo(JButton btnlo) {
		this.btnlo = btnlo;
	}

	public LoginJPanel() {
		set();
		add(unLb);
		add(pwLb);
		add(unt);
		add(pwt);
		add(btnlo);
		add(idenBox);
		add(idenLb);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正楷体", Font.PLAIN, 15);
		unLb.setLocation(250, 115);
		unLb.setSize(250, 130);
		unLb.setFont(font);
		unt = new JTextField();
		unt.setSize(190, 30);
		unt.setLocation(350, 165);
		pwLb.setSize(200, 100);
		pwLb.setLocation(255, 190);
		pwLb.setFont(font);
		pwt = new JPasswordField(); // 密码不可见
		pwt.setSize(190, 30);
		pwt.setLocation(350, 225);
		btnlo.setSize(120, 40);
		btnlo.setLocation(380, 320);
		btnlo.setFont(font);
		idenBox.setSize(120, 30);
		idenBox.setLocation(350, 120);
		idenBox.addItem("学生");
		idenBox.addItem("教师");
		idenBox.setFont(font);
		idenLb.setSize(250, 130);
		idenLb.setLocation(240, 70);
		idenLb.setFont(font);
	}
}
