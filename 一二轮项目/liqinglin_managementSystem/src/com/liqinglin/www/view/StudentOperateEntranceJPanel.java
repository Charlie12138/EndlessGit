package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;

public class StudentOperateEntranceJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton btne = new JButton("个人信息");
	private JButton btnWatchScore = new JButton("查看成绩");

	public JButton getBtnWatchScore() {
		return btnWatchScore;
	}

	public void setBtnWatchScore(JButton btnWatchScore) {
		this.btnWatchScore = btnWatchScore;
	}

	public JButton getBtne() {
		return btne;
	}

	public void setBtne(JButton btne) {
		this.btne = btne;
	}

	public StudentOperateEntranceJPanel() {
		set();
		add(btne);
		add(btnWatchScore);
		setVisible(true);
		setLayout(null);
	}

	private void set() {
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		btne.setBounds(10, 10, 100, 40);
		btne.setFont(font);
		btnWatchScore.setBounds(150, 10, 100, 40);
		btnWatchScore.setFont(font);
	}
}
