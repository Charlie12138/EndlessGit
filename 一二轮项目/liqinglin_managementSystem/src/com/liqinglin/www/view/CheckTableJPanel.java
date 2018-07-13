package com.liqinglin.www.view;

import java.awt.Font;
import javax.swing.*;
import javax.swing.table.*;
import com.liqinglin.www.po.Student;

public class CheckTableJPanel extends JPanel {
	/**
	 * 建一个表格
	 */
	private static final long serialVersionUID = 1L;
	private JLabel welcomeLb = new JLabel();
	private JTable table;

	private JScrollPane jscrollpane = new JScrollPane();
	private String[] columnNames = { "id", "用户名", "姓名", "性别", "紧急联系电话", "邮箱", "年级", "班级" };
	private Object[][] obj = new Object[1][8];

	public JLabel getLabWlc() {
		return welcomeLb;
	}

	public void setLabWlc(JLabel labWlc) {
		this.welcomeLb = labWlc;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getJscrollpane() {
		return jscrollpane;
	}

	public void setJscrollpane(JScrollPane jscrollpane) {
		this.jscrollpane = jscrollpane;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public Object[][] getObj() {
		return obj;
	}

	public void setObj(Object[][] obj) {
		this.obj = obj;
	}

	public CheckTableJPanel(Student stu) {
		obj[0][0] = stu.getId();
		obj[0][1] = stu.getUserName();
		obj[0][2] = stu.getName();
		obj[0][3] = stu.getSex();
		obj[0][4] = stu.getEmergencyContactNum();
		obj[0][5] = stu.getEmail();
		obj[0][6] = stu.getGrade();
		obj[0][7] = stu.getStuClass();
		table = new JTable(obj, columnNames);
		set(stu.getName());
		jscrollpane.setViewportView(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		add(jscrollpane);
		add(welcomeLb);

		setLayout(null);
	}

	private void set(String name) {
		welcomeLb.setSize(200, 100);
		welcomeLb.setLocation(0, 0);
		welcomeLb.setText("欢迎你！" + name);
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		welcomeLb.setFont(font);
		jscrollpane.setSize(1000, 200);
		jscrollpane.setLocation(0, 100);
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
		TableColumn secsetColumn = table.getColumnModel().getColumn(1);
		secsetColumn.setPreferredWidth(80);
		secsetColumn.setMaxWidth(80);
		secsetColumn.setMinWidth(80);

	}
}
