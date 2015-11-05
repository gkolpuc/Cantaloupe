package org.cantaloupe.examples;

import static org.cantaloupe.functions.Functions.equalTo;
import static org.cantaloupe.util.Constants.ZERO;
import static org.cantaloupe.util.Resource.esReq;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.ES;
import org.cantaloupe.json.TestStatus;
import org.junit.Assert;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;
//@formatter:off
public class SemTest {
	@Test
	public void shouldReturnZeroEvents() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success ==
				Cantaloupe.json(ES.search()
				.body(esReq("AdditionalMaterialsWithDuplicatedLink.json"))
				.asJson())
				.getArray("hits.hits")
				.count(equalTo(ZERO))
				.status());
	}
}
