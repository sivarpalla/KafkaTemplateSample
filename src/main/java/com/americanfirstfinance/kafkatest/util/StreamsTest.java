package com.americanfirstfinance.kafkatest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.valueextraction.Unwrapping.Skip;

import io.micrometer.core.annotation.Counted;

public class StreamsTest {

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
        Map<Integer, Employee> map = employees.stream().collect(Collectors.toMap(e->e.getEmployeeId(), e->e));
        map.entrySet().stream().forEach(x->System.out.println(x));;
        
        List<Integer> numbers=Arrays.asList(1,2,3,4,5,6,7,9,10);
//        int max=numbers.stream().max(Comparator.comparingInt(i->i)).get();
        int max=numbers.stream().max(Integer::compareTo).get();
        System.out.println("max:"+max);
        
        int secondMax=numbers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1);
        System.out.println("secondMax:"+secondMax);
        int sum = numbers.stream().reduce(-1*max*(max+1)/2, (a,b)->a+b);
        System.out.println("sum:"+sum);
//        numbers.stream().reduce((a,b,c)->a+b+c).
        
        //find number of occurance of each character.  
        String s = "ilovejavastreams";
        String[] result=s.split("");
        Map<String,Long> count =Arrays.stream(result).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println("countmap:"+count);
        //find duplicates in above
        List<String> duplicates = count.entrySet().stream().filter(m->m.getValue()>1).map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("duplicates:"+duplicates);
        
        List<String> stringsLength= Arrays.asList("asa","fdde","hyhyy","qwqwq");
        Map<String,Integer> wordLength = stringsLength.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("wordLength:"+wordLength);
        String longestWord=wordLength.entrySet().stream().max(Map.Entry.comparingByKey()).get().getKey();
        System.out.println("longestWord,"+longestWord);
        String longestWord2=stringsLength.stream().reduce((a,b)->a.length()>b.length()?a:b).get();
        System.out.println("longestWord2:"+longestWord2);
        
        int[] numbersarray = {1,2,3,4,5,6,7,9,10};
       List<String> startsWith1= Arrays.stream(numbersarray).boxed().map(n->n+"").filter(a->a.startsWith("1")).collect(Collectors.toList());
       System.out.println("startsWith1"+startsWith1);
        
	}  

}
