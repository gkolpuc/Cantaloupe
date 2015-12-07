package org.cantaloupe.util;

public class Property {
	public static String get(String name, String defaultValue) {
		return System.getProperty("cantaloupe." + name, defaultValue);
	}

	public static String getUrl(String property, String defaultUrl) {
		return get("url." + property, defaultUrl);
	}
}
