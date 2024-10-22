package com.vr.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vr.user.service.service.UserService;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;

	@GetMapping("/username/{username}")
	public ResponseEntity<Boolean> isUserValid(@PathVariable String username) {
		return userService.isUserExisting(username);
	}
	@GetMapping("/email/{email}")
	public ResponseEntity<Boolean> isEmailValid(@PathVariable String email) {
		return userService.isEmailExisting(email);
	}
}
