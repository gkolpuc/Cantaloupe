package org.cantaloupe.examples;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.API;
import org.cantaloupe.json.TestStatus;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

public class MetadataTest {

	@Test
	public void eventTypeLookupContainsSampleId() throws UnirestException {
		Assert.assertTrue(TestStatus.Success == Cantaloupe.json(API.get("/cache/lookup/eventtypes").asJson())
				.getLongVal("1000351539.id").assertEquals(1000351539L).status());
	}
}
