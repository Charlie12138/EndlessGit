package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.po.Score;
import com.liqinglin.www.service.ScoreService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class ModifyScoreJFrame extends JFrame {

	/**
	 * 修改成绩窗口
	 */
	private static final long serialVersionUID = 1L;
	private AddCourseJPanel modifyScoreJPanel; // 输入要修改的学生和科目的
	private AddCourseJPanel modifyScoreJPanel2;

	public void lanuchMSFrame(TeacherOperateEntranceJPanel teacherOperateJPanel) {
		setTitle("修改成绩");
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		teacherOperateJPanel.getBtnModifiScore().addActionListener(new ActionListener() { // 对 设置成绩 按钮的监听
			public void actionPerformed(ActionEvent e) {
				if (modifyScoreJPanel != null)
					remove(modifyScoreJPanel);
				if (modifyScoreJPanel2 != null)
					remove(modifyScoreJPanel2);
				modifyScoreJPanel = new AddCourseJPanel();
				modifyScoreJPanel.getCourseIdLb().setText("学生姓名：");
				modifyScoreJPanel.getCourseNameLb().setText("科目：");
				modifyScoreJPanel.getTeacherIdLb().setVisible(false);
				modifyScoreJPanel.getTeacherIdText().setVisible(false);
				getContentPane().add(modifyScoreJPanel);
				setVisible(true);

				modifyScoreJPanel.getBtnConfirm().addActionListener(new ActionListener() { // 对 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						ScoreService scoreService = new ScoreService();
						Msg msg = scoreService.searchScore(modifyScoreJPanel.getCourseIdText().getText(),
								modifyScoreJPanel.getCourseNameText().getText());
						if (msg.getResult().equals(Constants.SEARCHSUCCEED)) {
							Score score = (Score) msg.getMessage(); // 接受查询到的结果

							/**
							 * 创建修改界面
							 */
							remove(modifyScoreJPanel);
							modifyScoreJPanel2 = new AddCourseJPanel();
							modifyScoreJPanel2.getCourseIdLb().setText("学生姓名：");
							modifyScoreJPanel2.getCourseNameLb().setText("科目：");
							modifyScoreJPanel2.getTeacherIdLb().setText("成绩");
							modifyScoreJPanel2.getTeacherIdText().setText("" + score.getScore());
							modifyScoreJPanel2.getCourseIdText().setText(score.getStudentName());
							modifyScoreJPanel2.getCourseIdText().setEditable(false);
							modifyScoreJPanel2.getCourseNameText().setText(score.getCourseName());
							modifyScoreJPanel2.getCourseNameText().setEditable(false);
							getContentPane().add(modifyScoreJPanel2);
							getContentPane().validate();

							modifyScoreJPanel2.getBtnConfirm().addActionListener(new ActionListener() { // 对 确定 按钮的监听
								public void actionPerformed(ActionEvent e) {
									if (modifyScoreJPanel2.getTeacherIdText().getText()
											.matches("^[0-9]+(.[0-9]{0,2})?$")) {
										Score score = new Score();
										score.setStudentName(modifyScoreJPanel2.getCourseIdText().getText());
										score.setCourseName(modifyScoreJPanel2.getCourseNameText().getText());
										score.setScore(
												Double.parseDouble(modifyScoreJPanel2.getTeacherIdText().getText()));
										Msg msg = scoreService.modifyScore(score);
										JOptionPane.showMessageDialog(null, msg.getResult());
									} else {
										JOptionPane.showMessageDialog(null, "成绩格式不对");
										modifyScoreJPanel2.getTeacherIdText().setText("");
									}
								}
							});

							/**
							 * 取消 按钮的监听
							 */
							modifyScoreJPanel2.getBtnOff().addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									dispose();
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult());
						}

					}
				});

				modifyScoreJPanel.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}

		});
	}

}