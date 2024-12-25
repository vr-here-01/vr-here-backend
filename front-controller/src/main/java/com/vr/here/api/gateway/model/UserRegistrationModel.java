package com.vr.here.api.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {
	private String username;
	private String email;
	private String mobileNumber;
	private String password;
}
