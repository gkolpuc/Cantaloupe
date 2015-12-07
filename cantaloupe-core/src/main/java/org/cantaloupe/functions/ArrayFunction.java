package org.cantaloupe.functions;

import java.util.HashSet;
import java.util.Set;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.Json;
import org.cantaloupe.json.JsonArray;
import org.json.JSONObject;

import com.google.common.base.Function;

public class ArrayFunction extends CantaloupeFunction<JSONObject, Boolean> {

	Set<CantaloupeFunction<Object, Boolean>> functions = new HashSet<CantaloupeFunction<Object, Boolean>>();
	private final String path;
	private String[] contains;

	public ArrayFunction(String path) {
		this.path = path;
	}

	@Override
	public Boolean apply(JSONObject input) {
		JsonArray array = Cantaloupe.json(input).getArray(path);
		for (CantaloupeFunction<Object, Boolean> func : functions) {
			array.forEachStringVal(func);
		}

		if (contains != null) {
			array.contains(contains);
		}
		return true;
	}

	public CantaloupeFunction<JSONObject, Boolean> notContains(String... notContains) {
		functions.add(Functions.notContains(notContains));
		return this;
	}

	public CantaloupeFunction<JSONObject, Boolean> contains(String... contains) {
		this.contains = contains;
		return this;
	}

	public CantaloupeFunction<JSONObject, Boolean> apply(CantaloupeFunction<Object, Boolean> func) {
		functions.add(func);
		return this;
	}

}
