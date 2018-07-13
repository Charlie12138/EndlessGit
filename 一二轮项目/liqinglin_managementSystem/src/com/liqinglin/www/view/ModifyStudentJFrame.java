package com.liqinglin.www.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.po.Student;
import com.liqinglin.www.service.StudentService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;

public class ModifyStudentJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private AddStudentJPanel modifyUtil;
	private ModifyStudentJPanel modifyJPanel;

	public void lanuchMSJFrame(TeacherOperateEntranceJPanel tojp) {
		setResizable(false);
		setTitle("修改学生信息");
		setSize(1020, 540);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		tojp.getBtnModi().addActionListener(new ActionListener() {// 修改信息 按钮监听
			public void actionPerformed(ActionEvent e) {
				if (modifyUtil != null)
					remove(modifyUtil);
				if (modifyJPanel != null)
					remove(modifyJPanel);
				modifyJPanel = new ModifyStudentJPanel();
				getContentPane().add(modifyJPanel);
				setVisible(true);

				modifyJPanel.getBtnConfirm().addActionListener(new ActionListener() {// 确认 按钮监听
					public void actionPerformed(ActionEvent e) {

						StudentService studentService = new StudentService();
						modifyUtil = new AddStudentJPanel();
						modifyUtil.getIdt().setEditable(false); // 设置主键的数据不能被修改
						Msg msg = studentService.modifyStep1(modifyJPanel.getNameText().getText());

						if (msg.getResult().equals(Constants.SEARCHSUCCEED)) {
							Student stu = (Student) msg.getMessage();
							modifyUtil.getIdt().setText("" + stu.getId());
							modifyUtil.getUserNamet().setText(stu.getUserName());
							modifyUtil.getNamet().setText(stu.getName());
							modifyUtil.getSext().setText(stu.getSex());
							modifyUtil.getEmerConNumt().setText(stu.getEmergencyContactNum());
							modifyUtil.getEmailt().setText(stu.getEmail());
							modifyUtil.getGradet().setText(stu.getGrade());
							modifyUtil.getStuClasst().setText(stu.getStuClass());
							modifyUtil.getPassWordt().setText(stu.getPassWord());
							remove(modifyJPanel);
							getContentPane().add(modifyUtil);
							getContentPane().validate();
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult());
						}

						modifyUtil.getBtnConfirm().addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Student student = new Student();
								student.setId(Integer.parseInt(modifyUtil.getIdt().getText()));
								student.setUserName(modifyUtil.getUserNamet().getText());
								student.setPassWord(modifyUtil.getPassWordt().getText());
								student.setName(modifyUtil.getNamet().getText());
								student.setEmergencyContactNum(modifyUtil.getEmerConNumt().getText());
								student.setEmail(modifyUtil.getEmailt().getText());
								student.setGrade(modifyUtil.getGradet().getText());
								student.setStuClass(modifyUtil.getStuClasst().getText());
								student.setSex(modifyUtil.getSext().getText());
								Msg msg = studentService.modifyStep2(student);
								JOptionPane.showMessageDialog(null, msg.getResult());
							}
						});
						modifyUtil.getBtnOff().addActionListener(new ActionListener() {
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
