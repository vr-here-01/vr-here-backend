package com.vr.user.service.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.vr.user.service.entities.User;
import com.vr.user.service.repo.UserRepository;

@Component
public class LoadCache {
	@Autowired
	private UserRepository userRepository;

	@Bean("usersCache")
	@Cacheable
	private List<User> loadUsers() {
		return userRepository.findAll();
	}
}
