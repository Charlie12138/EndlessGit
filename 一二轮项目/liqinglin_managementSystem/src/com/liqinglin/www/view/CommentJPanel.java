package com.liqinglin.www.view;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class CommentJPanel extends JPanel {

	/**
	 * 评语
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane jscrollpane = new JScrollPane();
	private String[] columnNames = { "科目", "评语" };
	private Object[][] obj;

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

	public CommentJPanel(Map<String, String> map) {
		set(map);
		add(jscrollpane);
		setLayout(null);
	}

	private void set(Map<String, String> map) {
		System.out.println(map.size());
		obj = new Object[map.size()][2];
		String[] courseNames = map.keySet().toArray(new String[map.size()]);
		String[] comments = map.values().toArray(new String[map.size()]);
		for (int i = 0; i < map.size(); i++) {
			obj[i][0] = courseNames[i];
			obj[i][1] = comments[i];
		}
		table = new JTable(obj, columnNames);
		jscrollpane.setViewportView(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		table.setDefaultRenderer(Object.class, r);
		r.setHorizontalAlignment(JLabel.CENTER);
		jscrollpane.setSize(1000, 200);
		jscrollpane.setLocation(0, 100);
		TableColumn Column = table.getColumnModel().getColumn(0);
		Column.setPreferredWidth(60);
		Column.setMaxWidth(60);
		Column.setMinWidth(60);
	}
}
