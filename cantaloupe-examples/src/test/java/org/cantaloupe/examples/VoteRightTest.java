package org.cantaloupe.examples;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.ES;
import org.cantaloupe.json.TestStatus;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;
//@formatter:off
public class VoteRightTest {

	@Test
	public void shouldHaveProperEventName() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success == 
				Cantaloupe.json(ES.urlSearch("_id:171269957492")
									.asJson())
				.getArray("hits.hits")
				.first()
				.getStringVal("_source.Event.DistinguishingEventName")
				.assertEquals("INE511A01018 Change of number of voting rights per security to 1.0 as of Jan 04, 2012")
				.status());
	}

}
