package com.liqinglin.www.view;

import javax.swing.*;

public class LoginTipsJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbs = new JLabel();
	private JButton btnc = new JButton("关闭");

	public JButton getBtnc() {
		return btnc;
	}

	public void setBtnc(JButton btnc) {
		this.btnc = btnc;
	}

	public JLabel getLbs() {
		return lbs;
	}

	public void setLbs(JLabel lbs) {
		this.lbs = lbs;
	}

	public LoginTipsJPanel(String str) {
		set(str);
		add(lbs);
		add(btnc);
		setLayout(null);
	}

	private void set(String str) {
		lbs.setSize(200, 100);
		lbs.setLocation(400, 130);
		btnc.setSize(100, 40);
		btnc.setLocation(375, 230);
		lbs.setText(str);
	}

}