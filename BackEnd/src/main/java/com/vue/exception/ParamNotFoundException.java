package com.vue.exception;

public class ParamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ParamNotFoundException() {
		super();
	}

	public ParamNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParamNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamNotFoundException(String message) {
		super(message);
	}

	public ParamNotFoundException(Throwable cause) {
		super(cause);
	}
}
