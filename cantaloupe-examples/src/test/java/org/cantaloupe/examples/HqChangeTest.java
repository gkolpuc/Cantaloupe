package org.cantaloupe.examples;

import static org.cantaloupe.functions.Functions.exists;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.API;
import org.cantaloupe.json.TestStatus;
import org.cantaloupe.util.Resource;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

//@formatter:off
public class HqChangeTest {

	@Test
	public void shouldReturnHqEvents() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success == 
				Cantaloupe.json(API.search()
								.body(Resource.epReq("hq_change_all.json"))
								.asJson())
				.getArray("events")
				.forEach(exists("EventData.CHQCEvent"))
				.status());
	}
}
