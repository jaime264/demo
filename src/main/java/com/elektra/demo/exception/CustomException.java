package com.elektra.demo.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 2998143567303150673L;

	public CustomException(String valMsg) {
		super(valMsg);
	}

	public CustomException(String valMsg, CustomException e) {
		super(valMsg, e);
	}

	public CustomException() {
		super();
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}

}
