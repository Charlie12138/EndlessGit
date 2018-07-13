package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.service.CommentService;
import com.liqinglin.www.service.StudentService;

public class WatchScoreJFrame extends JFrame {

	/**
	 * 查看成绩的窗口
	 */
	private static final long serialVersionUID = 1L;
	CommentJPanel commentJPanel;

	public void lanuchWSFrame(StudentOperateEntranceJPanel cejp, String userName) {
		setSize(1000, 550);
		setTitle("个人信息");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		cejp.getBtnWatchScore().addActionListener(new ActionListener() {// 个人信息 按钮监听
			public void actionPerformed(ActionEvent e) {
				StudentService ss = new StudentService();
				Map<String, Double> map = ss.watchScore(userName);
				if (map != null) {
					WatchScoreJPanel watchJPanel = new WatchScoreJPanel(map);
					if (commentJPanel != null)
						remove(commentJPanel);
					getContentPane().add(watchJPanel);
					setVisible(true);
					watchJPanel.getComment().addActionListener(new ActionListener() {// 老师评语 按钮监听
						public void actionPerformed(ActionEvent e) {
							remove(watchJPanel);
							CommentService commentSerivce = new CommentService();
							Map<String, String> map2 = commentSerivce.comment(userName);
							commentJPanel = new CommentJPanel(map2);
							getContentPane().add(commentJPanel);
							getContentPane().validate();
						}

					});
				} else {
					JOptionPane.showMessageDialog(null, "还没有成绩");
				}
			}
		});
	}
}
