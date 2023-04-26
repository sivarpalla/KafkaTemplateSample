package com.americanfirstfinance.kafkatest.client;

import com.americanfirstfinance.kafkatest.model.DealerMasterResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.handler.ssl.SslContextBuilder;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Component
public class DealerLocationClient {

	@Value("${rest-client.DealerLocationClient.url}")
	private String dealerLocationUrl;

	@Autowired 
	RestTemplate restTemplate;

	@Autowired
	private WebClient webClient;
	
	
	public DealerMasterResponse getDealerMaster(String dealerId) {
		final var url = dealerLocationUrl + "/api/dealers/dealerId/" + dealerId ;
		DealerMasterResponse dealerMasterResponse = null;
		try {
			log.info("DealerLocationClient getDealerMaster for dealerId {} and dealerSettingCode {} triggered",dealerId);
			dealerMasterResponse = restTemplate
					.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<DealerMasterResponse>() {
					}).getBody();
		} catch (Exception ex) {
			log.error("Exception while getting getDealerMaster for dealerId {} due to: {}", dealerId, ex.toString());
		}

		return dealerMasterResponse;
	}

	public DealerMasterResponse getDealerMasterByFlux(String dealerId) {
		final var url = dealerLocationUrl + "/api/dealers/dealerId/"+dealerId ;
		return webClient
				.get()
				.uri(url)
				.retrieve()
				//.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.bodyToMono(DealerMasterResponse.class)
				.block();
	}

}
