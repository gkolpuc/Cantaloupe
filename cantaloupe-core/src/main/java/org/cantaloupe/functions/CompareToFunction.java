package org.cantaloupe.functions;

import org.cantaloupe.PathFinder;
import org.json.JSONObject;
import org.junit.Assert;

import com.google.common.base.Function;

public class CompareToFunction extends CantaloupeFunction<JSONObject, Boolean> {

	private String path;
	private String valueToCompare;
	private Assertion assertion;

	public CompareToFunction(String path, String valueToCompare, Assertion assertion) {
		this.path = path;
		this.valueToCompare = valueToCompare;
		this.assertion = assertion;
	}

	@Override
	public Boolean apply(JSONObject input) {
		Object v1 = PathFinder.getFieldValue(input, path);

		String message = "value of node [" + path + "] should be " + assertion + " to " + valueToCompare;
		return compare(v1, valueToCompare, message, assertion);
	}

	@Override
	public String toString() {
		return "CompareToFunction [path=" + path + ", valueToCompare=" + valueToCompare + ", assertion=" + assertion
				+ "]";
	}

}
