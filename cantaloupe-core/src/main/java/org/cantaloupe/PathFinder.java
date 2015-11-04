package org.cantaloupe;

import java.util.Iterator;

import org.cantaloupe.json.InvalidPathException;
import org.cantaloupe.json.NotJsonObjectException;
import org.cantaloupe.json.PathNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.base.Splitter;

public class PathFinder {
	public static Json get(JSONObject _source, String path) {
		Iterable<String> split = Splitter.on('.').trimResults().omitEmptyStrings().split(path);
		Json fieldValue = get(_source, split.iterator());
		// fieldValue = fieldValue.replaceAll("\n", " ").replaceAll(",", " ");
		return fieldValue;
	}

	public static Json get(JSONObject _source, Iterator<String> split) {
		if (split.hasNext()) {
			String next = split.next();

			if (_source.has(next)) {
				try {
					JSONArray jsonArray = _source.getJSONArray(next);
					if (!split.hasNext()) {
						return new Json(jsonArray);
					} else {
						throw new InvalidPathException();
					}
				} catch (JSONException e) {
				}

				try {
					JSONObject jsonObject = _source.getJSONObject(next);
					// System.out.println(next + " is JSONObject");
					if (jsonObject != null) {
						return get(jsonObject, split);
					}
				} catch (JSONException e) {
				}

			} else {
				throw new NotJsonObjectException(_source.get(next));
			}
		}

		return new Json(_source);
	}

	public static Object getFieldValue(JSONObject _source, String path) {
		Iterable<String> split = Splitter.on('.').trimResults().omitEmptyStrings().split(path);
		Object fieldValue;
		try {
			fieldValue = getFieldValue(_source, split.iterator());
		} catch (Exception e) {
			throw new PathNotFoundException(_source, path);
		}
		return fieldValue;
	}

	private static Object getFieldValue(JSONObject _source, Iterator<String> split) {
		if (split.hasNext()) {
			String next = split.next();
			if (_source.has(next)) {
				try {
					JSONArray jsonArray = _source.getJSONArray(next);
					// System.out.println(next + " is jsonObject");
					if (jsonArray != null && jsonArray.length() > 0) {
						try {
							return getFieldValue(jsonArray.getJSONObject(0), split);
						} catch (Exception e) {
						}
						try {
							return jsonArray.getString(0);
						} catch (Exception e) {
						}
						try {
							Long l = jsonArray.getLong(0);
							// System.out.println(next + " is Long: " + l);
							return l;
						} catch (JSONException e) {
						}

						try {
							Boolean boolean1 = jsonArray.getBoolean(0);
							// System.out.println(next + " is boolean: " +
							// boolean1);
							return boolean1;
						} catch (JSONException e) {
						}
						throw new PathNotFoundException(_source, next);
					}
				} catch (JSONException e) {
				}

				try {
					JSONObject jsonObject = _source.getJSONObject(next);
					// System.out.println(next + " is JSONObject");
					if (jsonObject != null) {
						return getFieldValue(jsonObject, split);
					}
				} catch (JSONException e) {
				}

				try {
					String string = _source.getString(next);
					// System.out.println(next + " is String: " + string);
					return string;
				} catch (JSONException e) {
				}

				try {
					Long l = _source.getLong(next);
					// System.out.println(next + " is Long: " + l);
					return l;
				} catch (JSONException e) {
				}

				try {
					Boolean boolean1 = _source.getBoolean(next);
					// System.out.println(next + " is boolean: " + boolean1);
					return boolean1;
				} catch (JSONException e) {
				}

			}
			throw new PathNotFoundException(_source, next);
		}

		return _source.toString();
	}

}
