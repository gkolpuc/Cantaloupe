package org.cantaloupe.functions;

import com.google.common.base.Function;
import static org.junit.Assert.assertEquals;

public class IntegerEqualToFunction implements Function<Integer, Boolean> {

	private Integer i;

	public IntegerEqualToFunction(Integer i) {
		this.i = i;
	}

	@Override
	public Boolean apply(Integer arg0) {
		assertEquals(arg0, i);
		return true;
	}

}
