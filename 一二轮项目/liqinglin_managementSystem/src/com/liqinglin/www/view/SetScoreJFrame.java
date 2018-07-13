package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.service.ScoreService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class SetScoreJFrame extends JFrame {

	/**
	 * 设置成绩的窗口
	 */
	private static final long serialVersionUID = 1L;

	public void lanuchSSFrame(TeacherOperateEntranceJPanel tojp) {
		setTitle("设置成绩");
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		tojp.getBtnSetScore().addActionListener(new ActionListener() { // 对 设置成绩 按钮的监听
			public void actionPerformed(ActionEvent e) {
				AddCourseJPanel acjp = new AddCourseJPanel();
				/**
				 * 利用添加科目的panel做设置成绩的panel
				 */
				acjp.getCourseIdLb().setText("学生姓名：");
				acjp.getCourseNameLb().setText("科目：");
				acjp.getTeacherIdLb().setText("成绩：");
				acjp.add(acjp.getComment());
				acjp.add(acjp.getCommentlb());
				getContentPane().add(acjp);
				setVisible(true);
				acjp.getBtnConfirm().addActionListener(new ActionListener() { // 对 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						ScoreService scoreService = new ScoreService();
						if (acjp.getTeacherIdText().getText().matches("^[0-9]+(.[0-9]{0,2})?$")) {
							Msg msg = scoreService.setScore(acjp.getCourseIdText().getText(),
									acjp.getCourseNameText().getText(),
									Double.parseDouble(acjp.getTeacherIdText().getText()), acjp.getComment().getText());
							if (msg.getResult().equals(Constants.ADDSUCCEED)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								acjp.getCourseIdText().setText("");
								acjp.getCourseNameText().setText("");
								acjp.getTeacherIdText().setText("");
								acjp.getComment().setText("");
							} else if (msg.getResult().equals(Constants.NOSTUDENT)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								acjp.getCourseIdText().setText("");
							} else if (msg.getResult().equals(Constants.NOCOURSE)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								acjp.getCourseNameText().setText("");
							} else if (msg.getResult().equals(Constants.SCOREEXISTED)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								acjp.getCourseNameText().setText("");
							} else {
								JOptionPane.showMessageDialog(null, msg.getResult());
							}
						} else {
							JOptionPane.showMessageDialog(null, "成绩格式不正确");
						}
					}
				});
				acjp.getBtnOff().addActionListener(new ActionListener() { // 对 添加科目 界面的“取消”按钮的监听
					public void actionPerformed(ActionEvent e) {
						remove(acjp);
						dispose();
					}
				});
			}

		});
	}
}
