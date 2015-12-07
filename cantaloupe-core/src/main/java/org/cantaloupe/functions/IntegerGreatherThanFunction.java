package org.cantaloupe.functions;

import static org.junit.Assert.*;

import com.google.common.base.Function;

public class IntegerGreatherThanFunction extends CantaloupeFunction<Integer, Boolean> {
	private Integer i;
	private String string;

	public IntegerGreatherThanFunction(Integer i, String assertMessage) {
		this.i = i;
		this.string = assertMessage;
	}

	@Override
	public Boolean apply(Integer input) {
		assertTrue(string, input > i);
		return true;
	}

}
