package org.cantaloupe.examples.util;

import static org.cantaloupe.util.Property.getUrl;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class ES {
	// -Dcantaloupe.url.es-events-search:http://localhost:9200/events/_search
	private static String url = "http://10.51.10.16:9200/" + "events" + "/_search";

	public static HttpRequestWithBody search() {
		return Unirest.post(getUrl("es-events-search", url)).header("accept", "application/json")
				.header("Content-Type", "application/json");
	}

	public static HttpRequestWithBody search(String indexName) {
		return Unirest.post(getUrl("es-events-search", url.replace("events", indexName)))
				.header("accept", "application/json").header("Content-Type", "application/json");
	}

	public static HttpRequest urlSearch(String q) {
		return Unirest.get(getUrl("es-events-search", url)).queryString("q", q);
	}

}
