package org.cantaloupe;

import org.cantaloupe.json.JsonArray;
import org.cantaloupe.json.JsonObject;
import org.cantaloupe.json.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class Cantaloupe {
	public static Response call(HttpResponse<JsonNode> asJson) {
		return (Response)new Json(asJson);
	}
	
	public static JsonObject json(HttpResponse<JsonNode> asJson) {
		return (JsonObject)new Json(asJson);
	}
	
	public static JsonArray json(JSONArray array) {
		return (JsonArray)new Json(array);
	}
	
	public static JsonObject json(JSONObject json) {
		return (JsonObject)new Json(json);
	}
}
