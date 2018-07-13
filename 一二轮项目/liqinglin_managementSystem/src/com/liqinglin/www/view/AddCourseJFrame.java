package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.po.Course;
import com.liqinglin.www.service.CourseService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

/**
 * 添加科目的窗口
 */
public class AddCourseJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public void lanuchACFrame(TeacherOperateEntranceJPanel teacherOperate) {
		setTitle("添加科目");
		setSize(850, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		teacherOperate.getBtnAddCourse().addActionListener(new ActionListener() { // 对 添加科目 按钮的监听
			public void actionPerformed(ActionEvent e) {
				AddCourseJPanel addCourseJPanel = new AddCourseJPanel();
				getContentPane().add(addCourseJPanel);
				setVisible(true);

				addCourseJPanel.getBtnConfirm().addActionListener(new ActionListener() { // 对 添加科目 界面的“确认”按钮的监听
					public void actionPerformed(ActionEvent e) {
						Course course = new Course();
						CourseService courseService = new CourseService(); /**
																			 * 判断输入是否为数字
																			 */
						if (addCourseJPanel.getCourseIdText().getText().matches("[0-9]+")
								&& addCourseJPanel.getTeacherIdText().getText().matches("[0-9]+")) {
							course.setCourseId(Integer.parseInt(addCourseJPanel.getCourseIdText().getText()));
							course.setCourseName(addCourseJPanel.getCourseNameText().getText());
							course.setTeacherId(Integer.parseInt(addCourseJPanel.getTeacherIdText().getText()));
							Msg msg = courseService.addCourse(course);
							if (msg.getResult().equals(Constants.ADDSUCCEED)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								addCourseJPanel.getCourseIdText().setText("");
								addCourseJPanel.getCourseNameText().setText("");
								addCourseJPanel.getTeacherIdText().setText("");
							} else if (msg.getResult().equals(Constants.IDEXISTED)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								addCourseJPanel.getCourseIdText().setText("");
							} else if (msg.getResult().equals(Constants.COURSEEXISTED)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								addCourseJPanel.getCourseNameText().setText("");
							} else if (msg.getResult().equals(Constants.NOTEACHER)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								addCourseJPanel.getTeacherIdText().setText("");
							}
						} else {
							JOptionPane.showMessageDialog(null, Constants.CHECKID);
							addCourseJPanel.getCourseIdText().setText("");
						}
					}
				});

				addCourseJPanel.getBtnOff().addActionListener(new ActionListener() { // 对 添加科目 界面的“取消”按钮的监听
					public void actionPerformed(ActionEvent e) {
						remove(addCourseJPanel);
						dispose();
					}

				});
			}
		});
	}
}
