package com.liqinglin.www.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import com.liqinglin.www.po.Student;
import java.awt.Font;
import java.util.List;

public class SearchStudentTableJPanel extends JPanel {

	/**
	 * 建立查询结果的表格
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labWlc = new JLabel();
	private JTable table;
	private JScrollPane jscrollpane = new JScrollPane();
	private String[] columnNames = { "id", "用户名", "密码", "姓名", "性别", "紧急联系电话", "邮箱", "年级", "班级" };
	private Object[][] obj;

	public SearchStudentTableJPanel(List<Student> list) {

		set(list);

		add(jscrollpane);
		add(labWlc);
		setLayout(null);
	}

	private void set(List<Student> list) {
		obj = new Object[list.size()][9];
		for (int i = 0; i < list.size(); i++) {
			obj[i][0] = list.get(i).getId();
			obj[i][1] = list.get(i).getUserName();
			obj[i][2] = list.get(i).getPassWord();
			obj[i][3] = list.get(i).getName();
			obj[i][4] = list.get(i).getSex();
			obj[i][5] = list.get(i).getEmergencyContactNum();
			obj[i][6] = list.get(i).getEmail();
			obj[i][7] = list.get(i).getGrade();
			obj[i][8] = list.get(i).getStuClass();
			table = new JTable(obj, columnNames);
		}
		jscrollpane.setViewportView(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		labWlc.setSize(200, 100);
		labWlc.setLocation(0, 0);
		labWlc.setText("您要找的学生如下");
		Font font = new Font("方正黑体", Font.PLAIN, 15);
		labWlc.setFont(font);
		jscrollpane.setSize(1000, 200);
		jscrollpane.setLocation(0, 100);
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(30);
		firsetColumn.setMaxWidth(30);
		firsetColumn.setMinWidth(30);
	}
}
