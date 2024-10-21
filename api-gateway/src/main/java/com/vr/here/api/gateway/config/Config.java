package com.vr.here.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
	@Bean("commonRestTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
