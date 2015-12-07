package org.cantaloupe.functions;

import org.junit.Assert;

import com.google.common.base.Function;

public class ContainsFunction extends CantaloupeFunction<Object, Boolean> {

	private final Object[] contains;

	public ContainsFunction(Object[] contains) {
		this.contains = contains;
	}

	@Override
	public Boolean apply(Object input) {
		// not yet implemented
		return true;
	}

}