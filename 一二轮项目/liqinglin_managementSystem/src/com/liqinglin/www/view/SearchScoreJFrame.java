package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.liqinglin.www.po.Score;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.service.ScoreService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class SearchScoreJFrame extends JFrame {

	/**
	 * 查询成绩窗口
	 */
	private static final long serialVersionUID = 1L;
	private SearchScoreJPanel searchScoreJPanel;
	private AddCourseJPanel searchScoreJPanel2;

	public void lanuchSSFrame(TeacherOperateEntranceJPanel tojp) {
		setTitle("查询成绩");
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		tojp.getBtnSearchScore().addActionListener(new ActionListener() { // 对 设置成绩 按钮的监听
			public void actionPerformed(ActionEvent e) {
				if (searchScoreJPanel != null)
					remove(searchScoreJPanel);
				if (searchScoreJPanel2 != null)
					remove(searchScoreJPanel2);
				searchScoreJPanel2 = new AddCourseJPanel();
				/**
				 * 利用添加科目的panel做查询成绩的panel
				 */
				searchScoreJPanel2.getCourseIdLb().setText("学生姓名：");
				searchScoreJPanel2.getCourseNameLb().setText("科目：");
				searchScoreJPanel2.getTeacherIdLb().setVisible(false);
				searchScoreJPanel2.getTeacherIdText().setVisible(false);
				getContentPane().add(searchScoreJPanel2);
				setVisible(true);
				searchScoreJPanel2.getBtnConfirm().addActionListener(new ActionListener() { // 对 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						ScoreService scoreService = new ScoreService();
						Msg msg = scoreService.searchScore(searchScoreJPanel2.getCourseIdText().getText(),
								searchScoreJPanel2.getCourseNameText().getText());
						System.out.println(msg.getResult());
						if (msg.getResult().equals(Constants.SEARCHSUCCEED)) {
							remove(searchScoreJPanel2);
							searchScoreJPanel = new SearchScoreJPanel((Score) msg.getMessage());
							getContentPane().add(searchScoreJPanel);
							getContentPane().validate();
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult());
							searchScoreJPanel2.getCourseIdText().setText("");
							searchScoreJPanel2.getCourseNameText().setText("");
						}
					}

				});
				searchScoreJPanel2.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}

		});
	}
}
