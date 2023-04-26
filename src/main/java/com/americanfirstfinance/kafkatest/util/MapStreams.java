package com.americanfirstfinance.kafkatest.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MapStreams {

	public static void main(String[] args) {
        HashMap<Integer, String> employeeMap = new HashMap<>();
        
        // Add employee data to the hash map
        employeeMap.put(1, "John Doe");
        employeeMap.put(2, "Jane Smith");
        employeeMap.put(3, "Bob Johnson");
        employeeMap.put(4, "Alice Lee");
        employeeMap.put(5, "David Brown");
        
        
        employeeMap.entrySet().stream().filter(e->e.getKey()>2).sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach(System.out::println);

	}

}
