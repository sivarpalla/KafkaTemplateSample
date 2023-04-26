package com.americanfirstfinance.kafkatest.messaging;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageContainer<T> {
	@Valid
	private T payload;
	@NotBlank
	private String messageId;
	@NotBlank
	private String messageType;
	@NotBlank
	private String clientId;

	public MessageContainer(T payload) {
		this.setPayload(payload);
	}
}