package com.vr.here.api.gateway.exception;

public class ClientSideException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClientSideException() {
		super();
	}

	public ClientSideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ClientSideException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientSideException(String message) {
		super(message);
	}

	public ClientSideException(Throwable cause) {
		super(cause);
	}

}
