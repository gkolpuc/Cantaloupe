package org.cantaloupe.json;

public class NotJsonObjectException extends RuntimeException {

	private static final long serialVersionUID = -6724820650722946L;

	public NotJsonObjectException(Object object) {
		super(""+object);
	}

}
