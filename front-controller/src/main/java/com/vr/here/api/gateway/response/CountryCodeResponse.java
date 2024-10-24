package com.vr.here.api.gateway.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryCodeResponse {
	private String code;
	private String countryCode;
	private String countryName;
	private String message;
}
