package com.vue.exception;

public class NormalErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NormalErrorException() {
		super();
	}

	public NormalErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NormalErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public NormalErrorException(String message) {
		super(message);
	}

	public NormalErrorException(Throwable cause) {
		super(cause);
	}
}
