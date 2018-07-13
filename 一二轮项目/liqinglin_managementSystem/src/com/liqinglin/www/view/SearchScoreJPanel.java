package com.liqinglin.www.view;

import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.liqinglin.www.po.Score;

public class SearchScoreJPanel extends JPanel {

	/**
	 * 查询成绩的结果
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel labWlc = new JLabel();
	private JScrollPane jscrollpane = new JScrollPane();
	private String[] columnNames = { "姓名", "科目名称", "成绩" };
	private Object[][] obj;

	public SearchScoreJPanel(Score score) {
		set(score);
		add(jscrollpane);
		add(labWlc);
		setLayout(null);
	}

	private void set(Score score) {
		obj = new Object[1][3];
		obj[0][0] = score.getStudentName();
		obj[0][1] = score.getCourseName();
		obj[0][2] = score.getScore();
		table = new JTable(obj, columnNames);
		jscrollpane.setViewportView(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		labWlc.setSize(200, 100);
		labWlc.setLocation(0, 0);
		labWlc.setText("您要找的成绩如下");
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		labWlc.setFont(font);
		table.setFont(font);
		jscrollpane.setSize(400, 100);
		jscrollpane.setLocation(225, 100);
	}
}
