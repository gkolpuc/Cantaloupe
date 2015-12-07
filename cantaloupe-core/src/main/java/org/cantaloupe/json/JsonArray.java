package org.cantaloupe.json;

import org.cantaloupe.functions.CantaloupeFunction;
import org.json.JSONObject;

import com.google.common.base.Function;

public interface JsonArray extends Base, SortableJsonArray {

	JsonArray forEach(CantaloupeFunction<JSONObject, Boolean> f);

	JsonObject first();

	JsonArray count(CantaloupeFunction<Integer, Boolean> equalTo);

	JsonArray forEachStringVal(CantaloupeFunction<Object, Boolean> func);

	JsonArray contains(String... contains);

	JsonArray atLeastOne(CantaloupeFunction<JSONObject, Boolean> f);

}
