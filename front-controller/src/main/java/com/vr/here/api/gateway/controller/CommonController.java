package com.vr.here.api.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vr.here.api.gateway.model.Country;
import com.vr.here.api.gateway.serviceproxy.UserServiceProxy;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("api")
public class CommonController {
	@Autowired
	private UserServiceProxy userServiceProxy;
	@Autowired
	@Qualifier("commonRestTemplate")
	private RestTemplate commonRestTemplate;
	@Qualifier("commonRestTemplate")
	private RestTemplate usersRestTemplate;
	private static final String EXTERNAL_API_URL = "https://restcountries.com/v3.1/all";
	@Value("user.service")
	private String USERVICE_URL;

	@GetMapping("countries")
	public ResponseEntity<List<Country>> fetchCountries() {
		return ResponseEntity.ok(List.of(commonRestTemplate.getForObject(EXTERNAL_API_URL, Country[].class)));
	}

	@GetMapping("username/{username}")
	public ResponseEntity<Boolean> getUserNames(@PathVariable(value = "username") String username) {
		return ResponseEntity
				.ok(commonRestTemplate.getForObject(USERVICE_URL + "/username/" + username, Boolean.class));
	}

	@GetMapping("email/{email}")
	public ResponseEntity<Boolean> getEmails(@PathVariable(value = "email") String email) {
		return ResponseEntity.ok(commonRestTemplate.getForObject(USERVICE_URL + "/email/" + email, Boolean.class));
	}

	@GetMapping("location")
	public ResponseEntity<String> getLocationDetails(@PathParam("lat") String lattitude,
			@PathParam("lng") String longitude) {
		return ResponseEntity.ok(null);

	}
}
