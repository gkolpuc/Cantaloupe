package org.cantaloupe.functions;

import org.junit.Assert;

import com.google.common.base.Function;

public class NotContainFunction extends CantaloupeFunction<Object, Boolean> {

	private final Object[] notContains;

	public NotContainFunction(Object[] notContains) {
		this.notContains = notContains;
	}

	@Override
	public Boolean apply(Object input) {
		for (Object o : notContains) {
			Assert.assertNotEquals(o, input);
		}
		return true;
	}

}
