package com.liqinglin.www.view;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import com.liqinglin.www.po.Student;
import com.liqinglin.www.service.StudentService;
import com.liqinglin.www.util.Constants;

public class SearchStudentJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private SearchStudentTableJPanel tableJPanel;
	private SearchStudentJPanel searchJPanel;

	public void lanuchSSJFrame(TeacherOperateEntranceJPanel tojp) {
		setSize(1000, 550);
		setTitle("查找学生");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		tojp.getBtnSearch().addActionListener(new ActionListener() {// 查找学生 的按钮监听
			public void actionPerformed(ActionEvent e) {
				if (tableJPanel != null)
					remove(tableJPanel); // 使第二次点 查找学生 的按钮可以重新输入
				if (searchJPanel != null)
					remove(searchJPanel);
				searchJPanel = new SearchStudentJPanel();
				getContentPane().add(searchJPanel);
				setVisible(true);

				searchJPanel.getBtnConfirm().addActionListener(new ActionListener() { // 确定 按钮的监听
					public void actionPerformed(ActionEvent e) {
						StudentService studentService = new StudentService();
						List<Student> list = (List<Student>) studentService
								.searchStudent(searchJPanel.getNameText().getText());
						if (list != null) {
							remove(searchJPanel);
							tableJPanel = new SearchStudentTableJPanel(list);
							getContentPane().add(tableJPanel);
							getContentPane().validate();
						} else if (list == null) {
							JOptionPane.showMessageDialog(null, Constants.NOSTUDENT);
							searchJPanel.getNameText().setText("");
						}
					}
				});

				searchJPanel.getBtnOff().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						searchJPanel.getNameText().setText("");
						dispose();
					}
				});

			}
		});
	}
}
