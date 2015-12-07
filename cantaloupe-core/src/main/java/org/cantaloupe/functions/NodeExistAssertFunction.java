package org.cantaloupe.functions;

import static org.junit.Assert.assertEquals;

import org.cantaloupe.Json;
import org.cantaloupe.PathFinder;
import org.json.JSONObject;

import com.google.common.base.Function;

public class NodeExistAssertFunction extends CantaloupeFunction<JSONObject, Boolean> {

	private String path;

	public NodeExistAssertFunction(String path) {
		this.path = path;
	}

	@Override
	public Boolean apply(JSONObject _source) {
		Json json = PathFinder.get(_source, path);
		assertEquals(json.isEmpty(), false);
		return true;
	}

}
