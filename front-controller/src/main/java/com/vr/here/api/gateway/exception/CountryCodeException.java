package com.vr.here.api.gateway.exception;

public class CountryCodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CountryCodeException() {
		super();
	}

	public CountryCodeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CountryCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CountryCodeException(String message) {
		super(message);
	}

	public CountryCodeException(Throwable cause) {
		super(cause);
	}

}
