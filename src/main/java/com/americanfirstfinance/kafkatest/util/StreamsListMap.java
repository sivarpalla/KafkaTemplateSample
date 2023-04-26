package com.americanfirstfinance.kafkatest.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsListMap {

	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(1234, "John", "Doe", "Sales", 5000.00));
        employees.add(new Employee(5678, "Jane", "Smith", "Marketing", 6000.00));
        employees.add(new Employee(9012, "Bob", "Johnson", "IT", 7000.00));
        employees.add(new Employee(3456, "Mary", "Jones", "Finance", 8000.00));
        employees.add(new Employee(7890, "Tom", "Wilson", "HR", 9000.00));
        
//        for (Employee employee : employees) {
//            System.out.println(employee.toString());
//        }
        
//        System.out.println(employees.stream().filter(e->e.getSalary()>10000).findAny().orElseGet(()->new Employee()));
      //  System.out.println(employees.stream().filter(e->e.getSalary()>6000).sorted((o1, o2) -> (int)(o1.getSalary()-o2.getSalary())).collect(Collectors.toList()));
        //employees.stream().filter(e->e.getSalary()>6000).sorted((o1, o2) -> (int)(o1.getSalary()-o2.getSalary())).forEach(System.out::println);
        //employees.stream().filter(e->e.getSalary()>6000).sorted(Comparator.comparing(Employee::getSalary)).forEach(System.out::println);
        List<String> depts = employees.stream().map(e->e.getDepartment().toLowerCase()).collect(Collectors.toList());
        System.out.println(depts);
        
        double sum= employees.stream().map(e->e.getSalary())
        		.mapToDouble(i->i)
        		.sum();
        System.out.println(sum);
        
        Employee highest = employees.stream().max(Comparator.comparing(Employee::getSalary)).get();
        System.out.println("Highest:"+highest);
        
        double secondSal=  employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().get().getSalary();
        System.out.println("secondSal:"+secondSal);
        
        List<Employee> incSalaries= employees.stream().map(e->Employee.builder().salary(e.getSalary()*2).build()).collect(Collectors.toList());
        System.out.println("incSalaries:"+incSalaries);
        
        Employee firstEmp= employees.stream().sorted(Comparator.comparing(Employee::getSalary)).skip(11).limit(1).findFirst().orElseGet(()->new Employee());
        System.out.println("firstEmp:"+firstEmp);
        
      //to print employee details based on dept
        System.out.println("***********************");
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.toList()))
        .entrySet().forEach(e->System.out.println(e.getKey()+" "+e.getValue()));
        
        
	}  

}
