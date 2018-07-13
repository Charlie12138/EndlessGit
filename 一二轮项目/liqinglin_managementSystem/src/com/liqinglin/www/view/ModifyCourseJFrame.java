package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.po.Course;
import com.liqinglin.www.service.CourseService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class ModifyCourseJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private AddCourseJPanel modifyJPanel2;
	private ModifyStudentJPanel modifyJPanel;

	public void lanuchMCFrame(TeacherOperateEntranceJPanel tojp) {
		setResizable(false);
		setTitle("修改科目信息");
		setSize(850, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		tojp.getBtnModiCourse().addActionListener(new ActionListener() {// 修改信息 按钮监听
			public void actionPerformed(ActionEvent e) {
				if (modifyJPanel != null)
					remove(modifyJPanel);
				if (modifyJPanel2 != null)
					remove(modifyJPanel2);
				modifyJPanel = new ModifyStudentJPanel();
				modifyJPanel.getModificationLable().setText("请输入要修改的科目名称：");
				modifyJPanel.getNameText().setBounds(420, 150, 190, 30);
				modifyJPanel.getModificationLable().setBounds(200, 150, 200, 30);
				modifyJPanel.getBtnConfirm().setBounds(220, 300, 100, 40);
				modifyJPanel.getBtnOff().setBounds(500, 300, 100, 40);
				getContentPane().add(modifyJPanel);
				setVisible(true);
				modifyJPanel.getBtnConfirm().addActionListener(new ActionListener() {// 确认 按钮监听
					public void actionPerformed(ActionEvent e) {
						CourseService courseService = new CourseService();
						modifyJPanel2 = new AddCourseJPanel();
						modifyJPanel2.getCourseIdText().setEditable(false);
						Msg msg = courseService.modifyCouseStep1(modifyJPanel.getNameText().getText());
						if (msg.getResult().equals(Constants.SEARCHSUCCEED)) {
							Course cou = (Course) msg.getMessage();
							modifyJPanel2.getCourseIdText().setText("" + cou.getCourseId());
							modifyJPanel2.getCourseNameText().setText(cou.getCourseName());
							modifyJPanel2.getTeacherIdText().setText("" + cou.getTeacherId());
							remove(modifyJPanel);
							getContentPane().add(modifyJPanel2);
							getContentPane().validate();
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult());
						}

						modifyJPanel2.getBtnConfirm().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (modifyJPanel2.getTeacherIdText().getText().matches("[0-9]+")) {
									Course course = new Course();
									course.setCourseId(Integer.parseInt(modifyJPanel2.getCourseIdText().getText()));
									course.setCourseName(modifyJPanel2.getCourseNameText().getText());
									course.setTeacherId(Integer.parseInt(modifyJPanel2.getTeacherIdText().getText()));
									Msg msg = courseService.modifyCourseStep2(course);
									JOptionPane.showMessageDialog(null, msg.getResult());
								} else {
									JOptionPane.showMessageDialog(null, "请检查id");
									modifyJPanel2.getTeacherIdText().setText("");
								}
							}
						});

						modifyJPanel2.getBtnOff().addActionListener(new ActionListener() { // 对 修改科目 界面的“取消”按钮的监听
							public void actionPerformed(ActionEvent e) {
								dispose();
							}

						});
					}

				});
				modifyJPanel.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modifyJPanel.getNameText().setText("");
						dispose();
					}
				});
			}

		});
	}
}
