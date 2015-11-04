package org.cantaloupe.functions;

import com.google.common.base.Function;

public class Functions {
	public static NodeExistAssertFunction exists(String path) {
		return new NodeExistAssertFunction(path);
	}

	public static Function<Integer, Boolean> equalTo(Integer i) {
		return new IntegerEqualToFunction(i);
	}

	public static NotContainFunction notContains(Object[] notContains) {
		return new NotContainFunction(notContains);
	}

	public static Function<Object, Boolean> contains(String[] contains) {
		return new ContainsFunction(contains);
	}
}
