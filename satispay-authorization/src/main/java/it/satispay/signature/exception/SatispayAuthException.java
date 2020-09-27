package it.satispay.signature.exception;

import org.springframework.http.HttpStatus;

public class SatispayAuthException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	private final HttpStatus statusCode;
	private final String error;

	public SatispayAuthException(HttpStatus statusCode, String error) {
		super();
		this.statusCode = statusCode;
		this.error = error;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getError() {
		return error;
	}
}
