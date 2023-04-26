package com.americanfirstfinance.kafkatest.util;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class NewTest1 {

	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(1234, "John", "Doe", "Sales", 5000.00));
        employees.add(new Employee(5678, "Jane", "Smith", "Marketing", 6000.00));
        employees.add(new Employee(9012, "Bob", "Johnson", "IT", 7000.00));
        employees.add(new Employee(3456, "Mary", "Jones", "Finance", 8000.00));
        employees.add(new Employee(7890, "Tom", "Wilson", "HR", 9000.00));

        //to print employee details based on dept
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.toList()))
        .entrySet().forEach(e->System.out.println(e.getKey()+" "+e.getValue()));
	}

}
