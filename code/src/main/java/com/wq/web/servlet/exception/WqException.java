package com.wq.web.servlet.exception;

public class WqException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5991090075115183901L;

	public WqException() {
		super();
	}

	public WqException(String message, Throwable cause) {
		super(message, cause);
	}

	public WqException(String message) {
		super(message);
	}

	public WqException(Throwable cause) {
		super(cause);
	}
}
