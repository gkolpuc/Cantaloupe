package org.cantaloupe.http;

public class HttpStatus {

	private int status;

	public HttpStatus(int status) {
		super();
		this.status = status;
	}

	public static HttpStatus ok() {
		return new HttpStatus(200);
	}

	public int getStatusCode() {
		return status;
	}
}
