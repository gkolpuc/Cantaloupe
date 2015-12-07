package org.cantaloupe.examples.util;

import org.cantaloupe.util.Property;
import org.junit.Before;

public class MockableTestCase {

	@Before
	public void before() {
		if (Property.get("runtime.env", "").equals("local")) {
			mock();
		}
	}

	public void mock() {
	}

}
