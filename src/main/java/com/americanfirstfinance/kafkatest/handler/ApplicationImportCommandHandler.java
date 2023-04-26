package com.americanfirstfinance.kafkatest.handler;


import java.util.concurrent.CompletableFuture;

import com.americanfirstfinance.kafkatest.messaging.MessageContainer;
import com.americanfirstfinance.kafkatest.model.ApplicationRequest;
import com.americanfirstfinance.kafkatest.services.KafkaService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import brave.Span;
import brave.Tracer;
import brave.kafka.clients.KafkaTracing;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ApplicationImportCommandHandler {

	@Autowired(required=true)
	KafkaService service;
	
	@Autowired
	private Tracer tracer;
	
	@Autowired
	KafkaTracing kafkaTracing;
	
	@SneakyThrows
	@KafkaListener(topics = "${appImport.topic.name.consumer}")
	public void consume(ConsumerRecord<String, String> payload, Acknowledgment ack) throws Exception {
		Span span = kafkaTracing.nextSpan(payload).name("process").start();
		try{
		log.info("ApplicationImportCommandHandler inside consumer {}", payload.value());

			ObjectMapper mapper = new ObjectMapper();
			MessageContainer<ApplicationRequest> mc = mapper.readValue(payload.value(),new TypeReference<MessageContainer<ApplicationRequest>>() {});

			ApplicationRequest applicationRequest = mc.getPayload();
			CompletableFuture.runAsync(() -> {
				try (final Tracer.SpanInScope ws = tracer.withSpanInScope(span)){
					service.processMessage(applicationRequest);

				}catch (Exception e) {
					log.error("Exception while inserting KwlMesg due to  {} ", e.getMessage());
				}
				
				});
			
		} catch (RuntimeException e) {
			log.error("Error while receiving envelop message from kafka, {} ", e.getMessage());
		}finally {
			ack.acknowledge();
		}
		
	}
}
