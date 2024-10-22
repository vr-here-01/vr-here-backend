package com.vr.here.api.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vr.here.api.gateway.model.Country;

@RestController
public class CommonController {
	@Autowired
	@Qualifier("commonRestTemplate")
	private RestTemplate commonRestTemplate;
	@Qualifier("commonRestTemplate")
	private RestTemplate usersRestTemplate;
	private static final String EXTERNAL_API_URL = "https://restcountries.com/v3.1/all";
	private static final String USERVICE_URL = "http://localhost:8081";

	@GetMapping("/api/countries/")
	public ResponseEntity<List<Country>> fetchCountries() {
		return ResponseEntity.ok(List.of(commonRestTemplate.getForObject(EXTERNAL_API_URL, Country[].class)));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<Boolean> getUserNames(@PathVariable(value = "username") String username) {
		return ResponseEntity
				.ok(commonRestTemplate.getForObject(USERVICE_URL + "/username/" + username, Boolean.class));
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Boolean> getEmails(@PathVariable(value = "email") String email) {
		return ResponseEntity
				.ok(commonRestTemplate.getForObject(USERVICE_URL + "/email/" + email, Boolean.class));
	}
}
