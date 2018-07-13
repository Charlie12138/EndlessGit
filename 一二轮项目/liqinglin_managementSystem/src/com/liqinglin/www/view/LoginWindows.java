package com.liqinglin.www.view;

import com.liqinglin.www.service.UserService;
import com.liqinglin.www.util.Constants;
import com.liqinglin.www.util.Msg;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 登录主界面
 */
public class LoginWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private LoginJPanel loginJPanel = new LoginJPanel();
	private StudentOperateEntranceJPanel studentOperate = new StudentOperateEntranceJPanel();
	private LoginTipsJPanel loginTipsJPanle;
	private TeacherOperateEntranceJPanel teacherOperate = new TeacherOperateEntranceJPanel();

	public void lanuchLWFrame(String title) {
		setTitle(title);
		setSize(850, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		loginJPanel.getBtnlo().addActionListener(new ActionListener() {// 对 “登录” 按钮的监听
			public void actionPerformed(ActionEvent e) {
				String userName = loginJPanel.getUnt().getText();
				String passWord = loginJPanel.getPwt().getText();

				if (loginJPanel.getIdenBox().getSelectedIndex() == 0) { // 判断 JComboBox 是否停留在学生那一栏
					try {
						UserService userService = new UserService();
						Msg msg = userService.studentLogin(userName, passWord);

						if (msg.getResult().equals(Constants.LOGINSUCCEED)) {
							System.out.println(msg);
							remove(loginJPanel);
							loginTipsJPanle = new LoginTipsJPanel(msg.getResult());
							getContentPane().add(loginTipsJPanle);
							getContentPane().validate();
							loginTipsJPanle.getBtnc().addActionListener(new ActionListener() { // 关闭 按钮监听
								public void actionPerformed(ActionEvent e) {
									remove(loginTipsJPanle);
									setTitle("个人主页");
									getContentPane().add(studentOperate); // 把 个人信息 按钮调出
									getContentPane().validate();
									new CheckTableJFrame().lanuchCTFrame(studentOperate, userName, passWord);
									new WatchScoreJFrame().lanuchWSFrame(studentOperate, userName);
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult(), null, JOptionPane.ERROR_MESSAGE, null);
							loginJPanel.getUnt().setText(""); // 输入错误后自行清空输入框
							loginJPanel.getPwt().setText("");
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (loginJPanel.getIdenBox().getSelectedIndex() == 1) { // 判断 JComboBox 是否停留在教师那一栏
					try {
						UserService userService = new UserService();
						Msg msg = userService.teacherLogin(userName, passWord);

						if (msg.getResult().equals(Constants.LOGINSUCCEED)) {
							System.out.println(msg);
							remove(loginJPanel);
							loginTipsJPanle = new LoginTipsJPanel(msg.getResult());
							getContentPane().add(loginTipsJPanle);
							getContentPane().validate();
							loginTipsJPanle.getBtnc().addActionListener(new ActionListener() { // 关闭 按钮监听
								public void actionPerformed(ActionEvent e) {
									remove(loginTipsJPanle);
									setTitle("主页");
									getContentPane().add(teacherOperate); // 把 添加学生 按钮调出
									getContentPane().validate();
									new AddStudentJFrame().lanuchASFrame(teacherOperate); // 创建 添加学生 窗口
									new DeleteStudentJFrame().lanuchDSJFrame(teacherOperate); // 创建 删除 学生窗口
									new SearchStudentJFrame().lanuchSSJFrame(teacherOperate); // 创建 查找学生 窗口
									new ModifyStudentJFrame().lanuchMSJFrame(teacherOperate); // 创建 修改学生 窗口
									new AddCourseJFrame().lanuchACFrame(teacherOperate); // 创建 添加科目 窗口
									new DeleteCourseJFrame().lanuchDCFrame(teacherOperate); // 创建 删除科目 窗口
									new SearchCourseJFrame().lanuchSCFrame(teacherOperate); // 创建 查找科目 窗口
									new ModifyCourseJFrame().lanuchMCFrame(teacherOperate); // 创建 修改科目 窗口
									new SetScoreJFrame().lanuchSSFrame(teacherOperate); // 创建 设置成绩 窗口
									new DeleteScoreJFrame().lanuchDSFrame(teacherOperate); // 创建 删除成绩 窗口
									new SearchScoreJFrame().lanuchSSFrame(teacherOperate); // 创建 查询成绩 窗口
									new ModifyScoreJFrame().lanuchMSFrame(teacherOperate); // 创建 修改成绩 窗口
								}
							});
						} else {
							JOptionPane.showMessageDialog(null, msg.getResult(), null, JOptionPane.ERROR_MESSAGE, null);
							loginJPanel.getUnt().setText(""); // 输入错误后自行清空输入框
							loginJPanel.getPwt().setText("");
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(loginJPanel);
	}
}
