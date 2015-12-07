package org.cantaloupe.functions;

import org.cantaloupe.PathFinder;
import org.json.JSONObject;

public class CompareFunction extends CantaloupeFunction<JSONObject, Boolean> {

	private String path1;
	private String path2;
	private Assertion assertion;

	CompareFunction(String path1, String path2, Assertion assertion) {
		this.path1 = path1;
		this.path2 = path2;
		this.assertion = assertion;
	}

	@Override
	public String toString() {
		return "CompareFunction [path1=" + path1 + ", path2=" + path2 + ", assertion=" + assertion + "]";
	}

	@Override
	public Boolean apply(JSONObject input) {
		Object v1 = PathFinder.getFieldValue(input, path1);
		Object v2 = PathFinder.getFieldValue(input, path2);

		String message = "values of nodes [" + path1 + "," + path2 + "] should be " + assertion;

		return compare(v1, v2, message, assertion);
	}

}
