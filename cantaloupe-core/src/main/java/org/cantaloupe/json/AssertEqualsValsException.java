package org.cantaloupe.json;

import java.util.Map;

import org.cantaloupe.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AssertEqualsValsException extends AssertException {

	private Json retrieved;
	private Json sorted;
	private String path;

	public AssertEqualsValsException(Json retrieved, Json sorted, String path) {
		this.retrieved = retrieved;
		this.sorted = sorted;
		this.path = path;
	}

	private static final long serialVersionUID = -4020754331501511355L;

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("==================================");
		b.append("\n");
		b.append("AssertEqualsValsException :");
		b.append("\n\n\t retrieved=\n" + pretty(retrieved.jsonString()));
		b.append("\n\n\t sorted=\n" + pretty(sorted.jsonString()));
		b.append("\n\n\t path=" + path);
		b.append("\n");
		b.append("==================================");
		return b.toString();
	}

	private String pretty(String jsonString) {
		Gson create = new GsonBuilder()
		.setPrettyPrinting().create();
		Map<String, Object> fromJson = create.fromJson(jsonString, Map.class);
		return create.toJson(fromJson);
	}

	@Override
	public void printStackTrace() {
		System.out.println("==================================");
		System.out.println(toString());
		System.out.println("==================================");
		super.printStackTrace();
	}

}
