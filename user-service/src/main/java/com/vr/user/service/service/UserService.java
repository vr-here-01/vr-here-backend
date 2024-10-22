package com.vr.user.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vr.user.service.entities.User;
import com.vr.user.service.repo.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	@Autowired
	@Qualifier("usersCache")
	private List<User> users;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ResponseEntity<Boolean> isUserExisting(String username) {
		return ResponseEntity
				.ok(users.stream().filter(user -> user.getUsername().equals(username)).findFirst().isEmpty());

	}

	public ResponseEntity<Boolean> isEmailExisting(String email) {
		return ResponseEntity.ok(users.stream().filter(user -> user.getEmail().equals(email)).findFirst().isEmpty());

	}
}
