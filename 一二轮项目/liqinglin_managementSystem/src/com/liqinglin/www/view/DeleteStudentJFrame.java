package com.liqinglin.www.view;

import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.liqinglin.www.service.StudentService;
import com.liqinglin.www.util.Msg;

public class DeleteStudentJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public void lanuchDSJFrame(TeacherOperateEntranceJPanel tojp) {
		setSize(850, 550);
		setTitle("删除学生");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tojp.getBtnDelete().addActionListener(new ActionListener() {// 登录 按钮监听
			public void actionPerformed(ActionEvent e) {
				DeleteStudentJPanel dsjp = new DeleteStudentJPanel();
				getContentPane().add(dsjp);
				setVisible(true);
				dsjp.getBtnConfirm().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String str = dsjp.getNameText().getText();
						StudentService studentService = new StudentService();
						Msg msg = studentService.deleteStudent(str);
						JOptionPane.showMessageDialog(null, msg.getResult());
					}

				});
				dsjp.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dsjp.getNameText().setText("");
						dispose();
					}

				});
			}
		});
	}
}
