package com.vr.here.api.gateway.exception;

public class ServerSideException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServerSideException() {
		super();
	}

	public ServerSideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServerSideException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerSideException(String message) {
		super(message);
	}

	public ServerSideException(Throwable cause) {
		super(cause);
	}

}
