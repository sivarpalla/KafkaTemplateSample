package com.americanfirstfinance.kafkatest.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapStreams2 {

	public static void main(String[] args) {
		HashMap<Employee, String> employeeMap = new HashMap<>();

		// Create employee objects
		Employee emp1 = new Employee(1234, "John", "Doe", "Sales", 5000.00);
		Employee emp2 = new Employee(5678, "Jane", "Smith", "Marketing", 6000.00);
		Employee emp3 = new Employee(9012, "Bob", "Johnson", "IT", 7000.00);
		Employee emp4 = new Employee(3456, "Mary", "Jones", "Finance", 8000.00);
		Employee emp5 = new Employee(7890, "Tom", "Wilson", "HR", 9000.00);

		// Add employee data to the hash map
		employeeMap.put(emp1, "Sales");
		employeeMap.put(emp2, "Marketing");
		employeeMap.put(emp3, "IT");

		employeeMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.comparing(Employee::getSalary).reversed()))
				.forEach(System.out::println);

	}

}
