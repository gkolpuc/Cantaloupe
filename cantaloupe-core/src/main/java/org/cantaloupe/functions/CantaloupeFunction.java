package org.cantaloupe.functions;

import org.junit.Assert;

import com.google.common.base.Function;

public abstract class CantaloupeFunction<F, T> implements Function<F, T> {
	private boolean noAssertions = false;

	public void noAssertions() {
		noAssertions = true;
	}

	public boolean doAssertions() {
		return !noAssertions;
	}

	Boolean compare(Object v1, Object v2, String message, Assertion assertion) {
		if (assertion == Assertion.EQUAL) {
			if (doAssertions()) {
				Assert.assertEquals(message, v1, v2);
			}
			return message.equals(v1);
		} else if (assertion == Assertion.NOT_EQUAL) {
			if (doAssertions()) {
				Assert.assertNotEquals(message, v1, v2);
			}
			return !message.equals(v1);
		}
		return true;
	}

}
