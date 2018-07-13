package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.service.CourseService;
import com.liqinglin.www.util.Msg;

public class DeleteCourseJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DeleteCourseJPanel deleteCourseJPanel;

	public void lanuchDCFrame(TeacherOperateEntranceJPanel teacherOperateEntranceJPanel) {
		setTitle("删除科目");
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		teacherOperateEntranceJPanel.getBtnDeleteCourse().addActionListener(new ActionListener() { // 对 删除科目 按钮的监听
			public void actionPerformed(ActionEvent e) {
				if (deleteCourseJPanel != null)
					remove(deleteCourseJPanel);
				deleteCourseJPanel = new DeleteCourseJPanel();
				getContentPane().add(deleteCourseJPanel);
				setVisible(true);
				deleteCourseJPanel.getBtnConfirm().addActionListener(new ActionListener() { // 对 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						String courseName = deleteCourseJPanel.getCourseNameText().getText();
						CourseService courseService = new CourseService();
						Msg msg = courseService.deleteCourse(courseName);
						JOptionPane.showMessageDialog(null, msg.getResult());
					}
				});
				deleteCourseJPanel.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						deleteCourseJPanel.getCourseNameText().setText("");
						dispose();
					}
				});
			}

		});
	}
}
