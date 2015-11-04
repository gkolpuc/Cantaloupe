package org.cantaloupe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.cantaloupe.http.HttpStatus;
import org.cantaloupe.json.AssertEqualsValsException;
import org.cantaloupe.json.JsonArray;
import org.cantaloupe.json.JsonManipulationException;
import org.cantaloupe.json.JsonObject;
import org.cantaloupe.json.LongVal;
import org.cantaloupe.json.Response;
import org.cantaloupe.json.SortOrder;
import org.cantaloupe.json.StringVal;
import org.cantaloupe.json.TestStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

public class Json implements JsonArray, JsonObject, Response {
	Integer status;

	public String jsonString() {

		if (jsonObject != null) {
			return jsonObject.toString();
		}

		if (array != null) {
			return array.toString();
		}
		return null;
	}

	private final JSONObject jsonObject;
	private final JSONArray array;

	Json(HttpResponse<JsonNode> res) {
		this(res.getBody().getObject());
		status = res.getStatus();
	}

	Json(JSONArray array) {
		this.jsonObject = null;
		this.array = array;
	}

	Json(JSONObject json) {
		this.jsonObject = json;
		this.array = null;
	}

	public JsonObject get(String path) {
		return PathFinder.get(jsonObject, path);

	}

	public JsonArray getArray(String path) {
		return (JsonArray) get(path);
	}

	@Override
	public JsonArray forEach(Function<JSONObject, JSONObject> f) {
		List<JSONObject> list = asList();

		List<JSONObject> transform = Lists.transform(list, f);
		for (JSONObject jsonObject : transform) {
		}
		return this;
	}

	private List<JSONObject> asList() {
		List<JSONObject> list = new LinkedList<JSONObject>();
		for (int i = 0; i < array.length(); i++) {
			list.add(array.getJSONObject(i));
		}
		return list;
	}

	public boolean isEmpty() {
		if (jsonObject == null && array == null) {
			return true;
		}
		return false;
	}

	@Override
	public JsonObject first() {
		return new Json(array.getJSONObject(0));
	}

	@Override
	public JsonArray count(Function<Integer, Boolean> equalTo) {
		int length = array.length();
		equalTo.apply(length);
		return this;
	}

	@Override
	public JsonArray forEachStringVal(Function<Object, Boolean> func) {
		List<String> list = asStringList();

		List<Boolean> transform = Lists.transform(list, func);
		for (Boolean b : transform) {
			Assert.assertTrue(b);
		}

		return this;
	}

	private List<String> asStringList() {
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < array.length(); i++) {
			list.add(array.getString(i));
		}
		return list;
	}

	public LongVal getLongVal(String path) {
		return new LongVal(PathFinder.getFieldValue(jsonObject, path), this);
	}

	public StringVal getStringVal(String path) {
		return new StringVal(PathFinder.getFieldValue(jsonObject, path), this);
	}

	private Object getVal(String path) {
		return PathFinder.getFieldValue(jsonObject, path);
	}

	@Override
	public JsonArray verify(final SortDef by) {
		final List<JSONObject> list = asList();

		Ordering<JSONObject> ordering = new JsonArrayOrdering(by);

		List<JSONObject> sortedCopy = ordering.sortedCopy(list);

		for (int i = 0; i < list.size(); i++) {
			Json retrieved = new Json(list.get(i));
			Json sorted = new Json(sortedCopy.get(i));

			compare(retrieved, sorted, by);
		}
		return this;
	}

	private void compare(Json retrieved, Json sorted, SortDef by) {
		if (equalVals(retrieved, sorted, by.getRelyOn())) {
			return;
		} else {
			for (SortIndicator sort : by.getSortList()) {
				assertEqualsVals(retrieved, sorted, sort.getPath());
			}
		}

	}

	private void assertEqualsVals(Json retrieved, Json sorted, String path) {
		boolean equalVals = equalVals(retrieved, sorted, path);
		if (!equalVals) {
			throw new AssertEqualsValsException(retrieved, sorted, path);
		}
		Assert.assertTrue(equalVals);
	}

	private boolean equalVals(Json retrieved, Json sorted, String path) {
		return retrieved.getVal(path).equals(sorted.getVal(path));
	}

	private void display(final List<JSONObject> list) {
		for (JSONObject jsonObject : list) {
			Json json = new Json(jsonObject);
			System.out.println(json.getVal("EventData.extendedEventId") + " "
					+ json.getVal("EventData.Derived.EventDate"));
		}
	}

	private final class JsonArrayOrdering extends Ordering<JSONObject> {
		private final SortDef by;

		private JsonArrayOrdering(SortDef by) {
			this.by = by;
		}

		/**
		 * @return a negative integer, zero, or a positive integer as the first
		 *         argument is less than, equal to, or greater than the second.
		 */
		@Override
		public int compare(JSONObject left, JSONObject right) {
			Iterator<SortIndicator> iterator = by.getSortList().iterator();

			while (iterator.hasNext()) {
				SortIndicator next = iterator.next();
				Object leftVal = new Json(left).getVal(next.getPath());
				Object rightVal = new Json(right).getVal(next.getPath());
				if (isNull(leftVal) && isNull(rightVal)) {
					continue;
				}

				if (!isNull(rightVal) && isNull(rightVal)) {
					return result(1, next.getOrder());
				}

				if (isNull(leftVal) && !isNull(rightVal)) {
					return 0;
				}

				if (leftVal instanceof Long && rightVal instanceof Long) {
					int compareTo = l(leftVal).compareTo(l(rightVal));
					if (compareTo == 0) {
						continue;
					} else {
						return result(compareTo, next.getOrder());
					}

				} else if (leftVal instanceof String && rightVal instanceof String) {
					int compareTo = s(leftVal).compareTo(s(rightVal));
					if (compareTo == 0) {
						continue;
					} else {
						return result(compareTo, next.getOrder());
					}

				} else {
					throw new JsonManipulationException();
				}

			}
			return 0;
		}

		private int result(int i, SortOrder order) {
			if (order == SortOrder.Desc) {
				return i * (-1);
			}
			return 0;
		}

		private String s(Object leftVal) {
			return (String) leftVal;
		}

		private Long l(Object leftVal) {
			return (Long) leftVal;
		}

		private boolean isNull(Object rightVal) {
			return rightVal == null;
		}
	}

	public TestStatus status() {
		return TestStatus.Success;
	}

	public Response verify(HttpStatus expected) {
		Assert.assertTrue(this.status == expected.getStatusCode());
		return this;
	}

	@Override
	public JsonObject json() {
		return this;
	}

	@Override
	public JsonArray contains(String... contains) {
		List<String> asStringList = asStringList();
		for (String string : contains) {
			if (!asStringList.contains(string)) {
				throw new ContainsException();
			}
		}

		return this;
	}

}
