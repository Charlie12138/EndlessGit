package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.po.Student;
import com.liqinglin.www.service.StudentService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class AddStudentJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private AddStudentJPanel addStudentJPanel;
	private Student student = new Student();

	public void lanuchASFrame(TeacherOperateEntranceJPanel teacherOperateJPanel) {
		setResizable(false);
		setTitle("添加学生");
		setSize(1020, 540);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		teacherOperateJPanel.getBtna().addActionListener(new ActionListener() { // 对 “添加学生” 按钮的监听
			public void actionPerformed(ActionEvent e) {
				if (addStudentJPanel != null)
					remove(addStudentJPanel);
				getContentPane().add(addStudentJPanel = new AddStudentJPanel());
				getContentPane().validate();
				setVisible(true);

				addStudentJPanel.getBtnConfirm().addActionListener(new ActionListener() { // 对 添加学生 界面的“确认”按钮的监听
					public void actionPerformed(ActionEvent e) {

						if (addStudentJPanel.getIdt().getText().matches("[0-9]+")) { // 判断输入的ID是否是数字

							student.setId(Integer.parseInt(addStudentJPanel.getIdt().getText()));
							student.setUserName(addStudentJPanel.getUserNamet().getText());
							student.setPassWord(addStudentJPanel.getPassWordt().getText());
							student.setName(addStudentJPanel.getNamet().getText());
							student.setEmergencyContactNum(addStudentJPanel.getEmerConNumt().getText());
							student.setEmail(addStudentJPanel.getEmailt().getText());
							student.setGrade(addStudentJPanel.getGradet().getText());
							student.setStuClass(addStudentJPanel.getStuClasst().getText());
							student.setSex(addStudentJPanel.getSext().getText());

							StudentService studentService = new StudentService();
							Msg msg = studentService.addStudent(student);
							if (msg.getResult().equals(Constants.ADDSUCCEED)) {
								JOptionPane.showMessageDialog(null, msg.getResult());
								addStudentJPanel.getIdt().setText("");
								addStudentJPanel.getUserNamet().setText("");
								addStudentJPanel.getPassWordt().setText("");
								addStudentJPanel.getNamet().setText("");
								addStudentJPanel.getEmerConNumt().setText("");
								addStudentJPanel.getEmailt().setText("");
								addStudentJPanel.getGradet().setText("");
								addStudentJPanel.getStuClasst().setText("");
								addStudentJPanel.getSext().setText("");
							} else {
								JOptionPane.showMessageDialog(null, msg.getResult());
							}

						} else {
							JOptionPane.showMessageDialog(null, Constants.CHECKID);
							addStudentJPanel.getIdt().setText("");
						}
					}

				});

				addStudentJPanel.getBtnOff().addActionListener(new ActionListener() { // 对 添加学生 界面的“取消”按钮的监听
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}

		});

	}
}
