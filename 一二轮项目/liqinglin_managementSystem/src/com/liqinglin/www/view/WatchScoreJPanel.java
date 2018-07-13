package com.liqinglin.www.view;

import java.awt.Font;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class WatchScoreJPanel extends JPanel {

	/**
	 * 成绩表
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labWlc = new JLabel();
	private JTable table;
	private JButton comment = new JButton("老师评语");
	private JScrollPane jscrollpane = new JScrollPane();
	private String[] columnNames;
	private Object[][] obj;

	public JButton getComment() {
		return comment;
	}

	public void setComment(JButton comment) {
		this.comment = comment;
	}

	public JLabel getLabWlc() {
		return labWlc;
	}

	public void setLabWlc(JLabel labWlc) {
		this.labWlc = labWlc;
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

	public WatchScoreJPanel(Map<String, Double> map) {
		set(map);
		add(jscrollpane);
		add(labWlc);
		add(comment);
		setLayout(null);
	}

	private void set(Map<String, Double> map) {
		obj = new Object[1][map.size()];
		columnNames = map.keySet().toArray(new String[map.size()]);
		System.out.println(map.size());
		obj[0] = map.values().toArray(new Double[map.size()]);
		System.out.println(obj);
		table = new JTable(obj, columnNames);
		jscrollpane.setViewportView(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		table.setDefaultRenderer(Object.class, r);
		r.setHorizontalAlignment(JLabel.CENTER);
		labWlc.setSize(200, 100);
		labWlc.setLocation(0, 0);
		labWlc.setText("你要的成绩如下：");
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		labWlc.setFont(font);
		jscrollpane.setSize(1000, 200);
		jscrollpane.setLocation(0, 100);
		comment.setBounds(450, 350, 100, 40);
		comment.setFont(font);
		for (int i = 0; i < map.size(); i++) {
			TableColumn Column = table.getColumnModel().getColumn(i);
			Column.setPreferredWidth(60);
			Column.setMaxWidth(60);
			Column.setMinWidth(60);
		}
	}
}
