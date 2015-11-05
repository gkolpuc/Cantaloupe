package org.cantaloupe.examples;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.API;
import org.cantaloupe.json.TestStatus;
import org.cantaloupe.util.Resource;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;
//@formatter:off
public class GeneralHealthTest {
	@Test
	public void shouldReturnTotalCount() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success == 
				Cantaloupe.json(API.search()
									.body(Resource.epReq("minimumRequest.json"))
									.asJson())
						  .getLongVal("totalCount")
						  .assertNotNull()
						  .status());
	}

}
