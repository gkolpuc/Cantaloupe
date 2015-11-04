package org.cantaloupe.json;

import org.cantaloupe.Json;
import org.junit.Assert;

public class LongVal extends Val {

	private final Long val;

	public LongVal(Object val, Json json) {
		super(json);
		this.val = (Long) val;
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
