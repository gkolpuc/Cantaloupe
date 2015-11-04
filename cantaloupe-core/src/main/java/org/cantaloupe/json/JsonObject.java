package org.cantaloupe.json;

public interface JsonObject {

	StringVal getStringVal(String string);
	JsonArray getArray(String path) ;
	LongVal getLongVal(String string);
}
