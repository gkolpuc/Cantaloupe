package org.cantaloupe.examples;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.API;
import org.cantaloupe.functions.JsonFunctions;
import org.cantaloupe.json.TestStatus;
import org.cantaloupe.util.Resource;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;
//@formatter:off
public class IncludeExcludeTest {
	@Test
	public void shouldExcludeOrganization() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success == 
				Cantaloupe.json(API.search()
									.body(Resource.epReq("OneIncludeOneExclude.json"))
									.asJson())
				.getArray("events")
				.forEach(JsonFunctions.arrayFunction("EventData.Derived.OrganizationID")
						.notContains("4296649838"))
				.status());
	}

}
