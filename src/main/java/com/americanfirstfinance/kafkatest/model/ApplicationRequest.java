package com.americanfirstfinance.kafkatest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ApplicationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private long customerId;

	private long accountNo;

	private long applicationId;
}
