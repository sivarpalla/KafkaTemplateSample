package com.americanfirstfinance.kafkatest.publisher;

import com.americanfirstfinance.kafkatest.messaging.MessageContainer;
import com.americanfirstfinance.kafkatest.model.ApplicationRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationImportEventPublisher {

    @Value("${appImport.topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishApplicationImportDecision(MessageContainer<ApplicationRequest> messageContainer) {

        if (messageContainer.getPayload() == null) {
        	log.error("ApplicationImportEventPublisher inside publish error");
        }

        try {
            log.info("ApplicationImportEventPublisher inside publish");
            kafkaTemplate.send(topicName, messageContainer);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}