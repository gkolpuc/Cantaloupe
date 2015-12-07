package org.cantaloupe.functions;

import org.json.JSONObject;

import com.google.common.base.Function;

public class Functions {
	public static CantaloupeFunction<JSONObject, Boolean> exists(String path) {
		return new NodeExistAssertFunction(path);
	}

	public static CantaloupeFunction<JSONObject, Boolean> compare(String path1, String path2, Assertion assertion) {
		return new CompareFunction(path1, path2, assertion);
	}

	public static CantaloupeFunction<JSONObject, Boolean> compareTo(String path, String valueToCompare,
			Assertion assertion) {
		return new CompareToFunction(path, valueToCompare, assertion);
	}

	public static CantaloupeFunction<Integer, Boolean> equalTo(Integer i) {
		return new IntegerEqualToFunction(i);
	}

	public static CantaloupeFunction<Integer, Boolean> greatherThan(Integer i, String string) {
		return new IntegerGreatherThanFunction(i, string);
	}

	public static CantaloupeFunction<Object, Boolean> notContains(Object[] notContains) {
		return new NotContainFunction(notContains);
	}

	public static CantaloupeFunction<Object, Boolean> contains(String[] contains) {
		return new ContainsFunction(contains);
	}
}
