package org.cantaloupe.json;

import org.json.JSONObject;

public class PathNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8216854307230831152L;

	public PathNotFoundException(JSONObject _source, String next) {
		super("Node: " + next + " not found in " + _source);
	}

}
