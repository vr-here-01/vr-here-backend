package com.vr.here.api.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vr.here.api.gateway.model.Country;

@RestController
public class CommonController {
	@Autowired
	@Qualifier("commonRestTemplate")
	private RestTemplate restTemplate;
	private static final String EXTERNAL_API_URL = "https://restcountries.com/v3.1/all";

	@GetMapping("/api/countries/")
	public ResponseEntity<List<Country>> fetchCountries() {
		return ResponseEntity.ok(List.of(restTemplate.getForObject(EXTERNAL_API_URL, Country[].class)));
	}

}
