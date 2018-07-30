package com.lql.converter;

import com.lql.po.Department;
import com.lql.po.Employee;
import org.springframework.core.convert.converter.Converter;

public class EmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String s) {
		if (s != null) {
			String[] vals = s.split("-");
			if (vals != null && vals.length == 4){
				String lastName = vals[0];
				String email = vals[1];
				Integer gender = Integer.parseInt(vals[2]);
				Department department = new Department();
				department.setId(Integer.valueOf(vals[3]));
////				Employee employee = new Employee(null, lastName, email, gender, department, birth);
//				System.out.println(s + "--converter--" + employee);
//				return employee;
			}
		}
		return null;
	}
}
