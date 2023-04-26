package com.americanfirstfinance.kafkatest.controller;

import com.americanfirstfinance.kafkatest.model.DealerMasterResponse;
import com.americanfirstfinance.kafkatest.services.KafkaService;
import com.americanfirstfinance.kafkatest.services.RestService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/kafka")
@RestController
@Slf4j
public class TestController {

	@Autowired(required=true)
	KafkaService service;
	
	@Autowired(required=true)
	RestService restService;
	
//	@Autowired
//	private Tracer tracer;
//	
	//http://localhost:8181/api/kafka/customerNumber/1/accountNumber/4
	@GetMapping("/customerNumber/{customerNumber}/accountNumber/{accountNumber}")
	public String testKafka(@PathVariable("customerNumber") long customerNumber,
			@PathVariable("accountNumber") long accountNumber) {
//			Span span = tracer.currentSpan();
//			if (span != null) {
//				log.info("Trace ID {}", span.context().traceId());
//				log.info("Span ID {}", span.context().spanId());
//			}
			log.info("Application getLoanLeaseTerms request Customer Id : {} and ", customerNumber);
			log.info("Application getLoanLeaseTerms request Customer Id : {} and ", customerNumber);
			service.publishMessage(customerNumber, accountNumber);
		return "SUCCESS";
	}
	
	//http://localhost:8181/api/kafka/resttemplate/1300
	@GetMapping("resttemplate/{dealerId}")
	public DealerMasterResponse testKafka(@PathVariable("dealerId") String dealerId) {
			log.info("Application testKafka request dealerId Id : {} ", dealerId);
			return restService.getByRestTemplate(dealerId);
	}
	
	//http://localhost:8181/api/kafka/webclient/1300
		@GetMapping("webclient/{dealerId}")
		public DealerMasterResponse webclient(@PathVariable("dealerId") String dealerId) {
				log.info("Application webclient request dealerId Id : {} ", dealerId);
				return restService.getBywebclient(dealerId);
		}
}