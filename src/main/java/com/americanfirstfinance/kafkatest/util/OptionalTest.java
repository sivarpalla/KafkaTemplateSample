package com.americanfirstfinance.kafkatest.util;

import java.util.Optional;

public class OptionalTest {

	public static void main(String[] args) {
		Employee e= new Employee(0, "2323", null, null, 0);
		
		Optional<String> emailOptional = Optional.ofNullable(e.getFirstName());
		System.out.println(emailOptional.orElse(""));
		System.out.println(emailOptional.orElseGet(()->""));

	}

}
