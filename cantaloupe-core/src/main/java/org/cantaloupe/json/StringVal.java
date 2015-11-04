package org.cantaloupe.json;

import org.cantaloupe.Json;
import org.junit.Assert;

public class StringVal extends Val{

	private final String val;
	

	public StringVal(Object val, Json json) {
		super(json);
		this.val = (String) val;
	}
	@Override
	public Json assertEquals(Object expected) {
		Assert.assertTrue(expected.equals(val));
		return getJson();
	}
	@Override
	Object val() {
		return val;
	}

}
