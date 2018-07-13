package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.service.ScoreService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class DeleteScoreJFrame extends JFrame {

	/**
	 * 删除成绩窗口
	 */
	private static final long serialVersionUID = 1L;
	AddCourseJPanel acjp;

	public void lanuchDSFrame(TeacherOperateEntranceJPanel tojp) {
		setTitle("删除成绩");
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		tojp.getBtnDeleteScore().addActionListener(new ActionListener() { // 对 设置成绩 按钮的监听
			public void actionPerformed(ActionEvent e) {
				if (acjp != null)
					remove(acjp);
				acjp = new AddCourseJPanel();
				/**
				 * 利用添加科目的panel做删除成绩的panel
				 */
				acjp.getCourseIdLb().setText("学生姓名：");
				acjp.getCourseNameLb().setText("科目：");
				acjp.getTeacherIdLb().setVisible(false);
				acjp.getTeacherIdText().setVisible(false);
				getContentPane().add(acjp);
				setVisible(true);
				acjp.getBtnConfirm().addActionListener(new ActionListener() { // 对 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						ScoreService scoreService = new ScoreService();
						Msg msg = scoreService.deleteScore(acjp.getCourseIdText().getText(),
								acjp.getCourseNameText().getText());
						if (msg.getResult().equals(Constants.DELETESUCCEED)) {
							JOptionPane.showMessageDialog(null, msg.getResult());
							acjp.getCourseIdText().setText("");
							acjp.getCourseNameText().setText("");
						} else if (msg.getResult().equals(Constants.NOSTUDENT)) {
							JOptionPane.showMessageDialog(null, msg.getResult());
							acjp.getCourseIdText().setText("");
						} else if (msg.getResult().equals(Constants.NOCOURSE)) {
							JOptionPane.showMessageDialog(null, msg.getResult());
							acjp.getCourseNameText().setText("");
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult());
						}
					}
				});
				acjp.getBtnOff().addActionListener(new ActionListener() { // 对 删除成绩 界面的“取消”按钮的监听
					public void actionPerformed(ActionEvent e) {
						remove(acjp);
						dispose();
					}

				});
			}
		});
	}
}
