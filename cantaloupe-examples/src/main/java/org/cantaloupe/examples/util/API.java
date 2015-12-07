package org.cantaloupe.examples.util;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

public class API {
	private static String url = "http://10.51.10.45:8090";

	// private static String url =
	// "http://hdcp-event-platform-api-beta.int.thomsonreuters.com:80";

	public static HttpRequestWithBody search() {
		return Unirest.post(url + "/events").header("accept", "application/json")
				.header("Content-Type", "application/json");
	}

	public static HttpRequest get(String relativePath) {
		return Unirest.get(url + relativePath);
	}

}
