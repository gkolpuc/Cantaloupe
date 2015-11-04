package org.cantaloupe.json;

import org.cantaloupe.Json;
import org.cantaloupe.http.HttpStatus;

public interface Response {
	Response verify(HttpStatus expected) ;
	
	JsonObject json();
}
