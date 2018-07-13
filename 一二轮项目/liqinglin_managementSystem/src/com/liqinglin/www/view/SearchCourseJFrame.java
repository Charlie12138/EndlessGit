package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.po.Course;
import com.liqinglin.www.service.CourseService;
import com.liqinglin.www.util.Constants;

public class SearchCourseJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private SearchCourseResultJPanel resultJPanel;
	private SearchCourseJPanel searchCourseJPanel;

	public void lanuchSCFrame(TeacherOperateEntranceJPanel teacherOperateJPanel) {
		setSize(850, 550);
		setTitle("查找科目");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		teacherOperateJPanel.getBtnSearchCourse().addActionListener(new ActionListener() {// 查找科目 的按钮监听
			public void actionPerformed(ActionEvent e) {
				if (searchCourseJPanel != null)
					remove(searchCourseJPanel);
				if (resultJPanel != null)
					remove(resultJPanel);
				searchCourseJPanel = new SearchCourseJPanel();
				getContentPane().add(searchCourseJPanel);
				setVisible(true);
				searchCourseJPanel.getBtnConfirm().addActionListener(new ActionListener() { // 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						CourseService courseService = new CourseService();
						List<Course> list = (List<Course>) courseService
								.searchCourse(searchCourseJPanel.getNameText().getText());
						if (list != null) {
							remove(searchCourseJPanel);
							resultJPanel = new SearchCourseResultJPanel(list);
							getContentPane().add(resultJPanel);
							getContentPane().validate();
						} else if (list == null) {
							JOptionPane.showMessageDialog(null, Constants.NOCOURSE);
							searchCourseJPanel.getNameText().setText("");
						}
					}
				});
				searchCourseJPanel.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchCourseJPanel.getNameText().setText("");
						dispose();
					}
				});
			}

		});
	}

}
