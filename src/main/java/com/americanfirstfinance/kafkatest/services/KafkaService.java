package com.americanfirstfinance.kafkatest.services;

import com.americanfirstfinance.kafkatest.messaging.MessageContainer;
import com.americanfirstfinance.kafkatest.model.ApplicationRequest;
import com.americanfirstfinance.kafkatest.publisher.ApplicationImportEventPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaService {
	
	@Autowired
	ApplicationImportEventPublisher applicationImportEventPublisher;

	public void publishMessage(long customerId, long accountNo) {
		log.info("Service applicationPreDecision producer customerId {}",customerId);
		ApplicationRequest applicationRequest = ApplicationRequest.builder().accountNo(accountNo).customerId(customerId).build();
		MessageContainer<ApplicationRequest> mc = new MessageContainer(applicationRequest);
		applicationImportEventPublisher.publishApplicationImportDecision(mc);
	}
	
	public void processMessage(ApplicationRequest applicationRequest) {
		log.info("*********************Service processMessage consumer applicationRequest {}",applicationRequest);
		
	}

}
