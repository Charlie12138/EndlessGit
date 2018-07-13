package com.liqinglin.www.view;

import java.awt.event.*;
import javax.swing.*;
import com.liqinglin.www.po.Student;
import com.liqinglin.www.service.StudentService;
import com.liqinglin.www.util.Msg;

public class CheckTableJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private StudentService ss = new StudentService();

	public void lanuchCTFrame(StudentOperateEntranceJPanel studentOperate, String userName, String passWord) {
		setSize(1000, 550);
		setTitle("个人信息");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		studentOperate.getBtne().addActionListener(new ActionListener() {// 个人信息 按钮监听
			public void actionPerformed(ActionEvent e) {
				Msg msg = ss.check(userName, passWord);
				getContentPane().add(new CheckTableJPanel((Student) msg.getMessage()));
				setVisible(true);
			}
		});
	}
}