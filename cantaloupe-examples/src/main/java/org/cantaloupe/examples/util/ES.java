package org.cantaloupe.examples.util;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class ES {

	private static String url ="http://10.51.10.16:9200/events/_search";

	public static HttpRequestWithBody search() {
		return Unirest.post(url).header("accept", "application/json").header("Content-Type", "application/json");
	}

	public static HttpRequest urlSearch(String q) {
		return Unirest.get(url).queryString("q", q);
	}
}
