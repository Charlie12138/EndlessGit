package com.liqinglin.www.view;

import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.liqinglin.www.po.Course;

public class SearchCourseResultJPanel extends JPanel {

	/**
	 * 为查看科目设置表格
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labWlc = new JLabel();
	private JTable table;
	private JScrollPane jscrollpane = new JScrollPane();
	private String[] columnNames = { "科目id", "科目名称", "教师" };
	private Object[][] obj;

	public SearchCourseResultJPanel(List<Course> list) {
		obj = new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			obj[i][0] = list.get(i).getCourseId();
			obj[i][1] = list.get(i).getCourseName();
			obj[i][2] = list.get(i).getTeacherName();
		}
		table = new JTable(obj, columnNames);
		set();
		jscrollpane.setViewportView(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		add(jscrollpane);
		add(labWlc);
		setLayout(null);
	}

	private void set() {
		labWlc.setSize(200, 100);
		labWlc.setLocation(0, 0);
		labWlc.setText("您要找的科目如下");
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		labWlc.setFont(font);
		jscrollpane.setSize(400, 100);
		jscrollpane.setLocation(225, 100);
	}

}
