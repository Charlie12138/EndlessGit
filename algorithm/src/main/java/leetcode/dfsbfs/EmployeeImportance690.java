package leetcode.dfsbfs;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class EmployeeImportance690 {
	public int getImportance(List<Employee> employees, int id) {
		int result = 0;
		Map<Integer, Employee> employeeMap = new HashMap<>();
		for (Employee e : employees) {
			employeeMap.put(e.id, e);
		}
		Employee employee = employeeMap.get(id);
		LinkedList<Integer> subordinates = new LinkedList<>(employee.subordinates);
		while (!subordinates.isEmpty()) {
			int curEmp = subordinates.poll();
			Employee curEmployee = employeeMap.get(curEmp);
			result += curEmployee.importance;
			if (curEmployee.subordinates != null) {
				subordinates.addAll(curEmployee.subordinates);
			}
		}



//		Optional<Employee> optional = employees.stream().filter(e -> e.id == id).findAny();
//		if (optional.isPresent()) {
//			employee = optional.get();
//			if (employee.subordinates != null) {
//				result = employee.subordinates.stream().mapToInt(integer -> getImportance(employees, integer)).sum();
//			}
//			return employee.importance + result;
//		}

//		for (Employee e : employees) {
//			if (e.id == id) {
//				employee = e;
//			}
//		}
//		if (employee != null) {
//			if (employee.subordinates != null) {
//				for (Integer i : employee.subordinates) {
//					result += getImportance(employees, i);
//				}
//			}
//			return employee.importance + result;
//		}
		return employee.importance + result;
	}

	public static void main(String[] args) {
		Employee employee = new Employee(1, 5, Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
		Employee employee1 = new Employee(2, 3, null);
		Employee employee2 = new Employee(3, 3, null);
		Employee employee3 = new Employee(4, 3, null);
		Employee employee4 = new Employee(5, 3, null);
		Employee employee5 = new Employee(6, 3, null);
		Employee employee6 = new Employee(7, 3, null);
		Employee employee7 = new Employee(8, 3, null);
		Employee employee8 = new Employee(9, 3, null);
		Employee employee9 = new Employee(10, 3, null);
		Employee employee10 = new Employee(11, 3, null);
		Employee employee11= new Employee(12, 3, null);
		Employee employee12 = new Employee(13, 3, null);
		Employee employee13 = new Employee(14, 3, null);

		EmployeeImportance690  e= new EmployeeImportance690();
		long start = System.nanoTime();
		System.out.println(e.getImportance(Arrays.asList(employee, employee1, employee2, employee3,
				employee4, employee5, employee6, employee7, employee8, employee9, employee10, employee11,
				employee12, employee13), 1));
		System.out.println("time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
	}
}

class Employee {
	/**
	 * 	It's the unique id of each node;
	 * 	unique id of this employee
	 */
	public int id;
	/**
	 * the importance value of this employee
	 */
	public int importance;
	/**
	 * the id of direct subordinates
	 */
	public List<Integer> subordinates;

	public Employee(int id, int importance, List<Integer> subordinates) {
		this.id = id;
		this.importance = importance;
		this.subordinates = subordinates;
	}
}