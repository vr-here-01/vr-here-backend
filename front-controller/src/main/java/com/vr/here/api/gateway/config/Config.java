package com.vr.here.api.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
	@Bean("commonRestTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Value("user.service.url")
	private String userServiceUrl;

	@Bean("userClient")
	public WebClient.Builder getUserClient() {
		return WebClient.builder().baseUrl(userServiceUrl);
	}
}
