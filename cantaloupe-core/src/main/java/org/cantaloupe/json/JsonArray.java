package org.cantaloupe.json;

import org.json.JSONObject;

import com.google.common.base.Function;

public interface JsonArray extends Base,SortableJsonArray{

	JsonArray forEach(Function<JSONObject, JSONObject> f);

	JsonObject first();

	JsonArray count(Function<Integer, Boolean> equalTo);

	JsonArray forEachStringVal(Function<Object, Boolean> func);
	
	JsonArray contains(String... contains);

	

	


	


}
