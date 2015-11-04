package org.cantaloupe.functions;

import java.util.HashSet;
import java.util.Set;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.Json;
import org.cantaloupe.json.JsonArray;
import org.json.JSONObject;

import com.google.common.base.Function;

public class ArrayFunction implements Function<JSONObject, JSONObject> {

	Set<Function<Object, Boolean>> functions = new HashSet<Function<Object, Boolean>>();
	private final String path;
	private String[] contains;

	public ArrayFunction(String path) {
		this.path = path;
	}

	@Override
	public JSONObject apply(JSONObject input) {
		JsonArray array = Cantaloupe.json(input).getArray(path);
		for (Function<Object, Boolean> func : functions) {
			array.forEachStringVal(func);
		}

		if (contains != null) {
			array.contains(contains);
		}
		return input;
	}

	public Function<JSONObject, JSONObject> notContains(String... notContains) {
		functions.add(Functions.notContains(notContains));
		return this;
	}

	public Function<JSONObject, JSONObject> contains(String... contains) {
		this.contains = contains;
		return this;
	}

	public Function<JSONObject, JSONObject> apply(Function<Object, Boolean> func) {
		functions.add(func);
		return this;
	}

}
