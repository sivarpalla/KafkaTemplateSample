package com.americanfirstfinance.kafkatest.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

	private int employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private double salary;
}
