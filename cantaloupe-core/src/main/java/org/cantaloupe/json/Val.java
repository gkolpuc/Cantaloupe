package org.cantaloupe.json;

import org.cantaloupe.Json;
import org.junit.Assert;


public abstract class Val {
	private final Json json;

	public Val(Json json) {
		super();
		this.json = json;
	}

	public Json getJson() {
		return json;
	}
	public abstract Json assertEquals(Object expected);
	
	public Json assertNotNull() {
		Assert.assertNotNull(val());;
		return json;
	}
	
	abstract Object val();
}
