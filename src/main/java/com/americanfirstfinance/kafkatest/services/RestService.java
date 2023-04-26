package com.americanfirstfinance.kafkatest.services;

import com.americanfirstfinance.kafkatest.client.DealerLocationClient;
import com.americanfirstfinance.kafkatest.model.DealerMasterResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestService {

	   @Autowired
	    DealerLocationClient dealerLocationClient;

	   
	public DealerMasterResponse getByRestTemplate(String dealerId) {
		DealerMasterResponse dealerMasterResponse = dealerLocationClient.getDealerMaster(dealerId);
		log.info("dealerMasterResponse {}",dealerMasterResponse);
		throw new NullPointerException();
		//return dealerMasterResponse;
	}
	
	public DealerMasterResponse getBywebclient(String dealerId) {
		DealerMasterResponse dealerMasterResponse = dealerLocationClient.getDealerMasterByFlux(dealerId);
		log.info("getByRestTemplateByFlux dealerMasterResponse {}",dealerMasterResponse);
		return dealerMasterResponse;
	}
}
