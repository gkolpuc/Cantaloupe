package org.cantaloupe.examples;

import static org.cantaloupe.functions.Functions.equalTo;
import static org.cantaloupe.util.Constants.ZERO;
import static org.cantaloupe.util.Resource.esReq;
import static org.cantaloupe.util.Resource.esRes;
import static spark.Spark.port;
import static spark.Spark.post;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.ES;
import org.cantaloupe.examples.util.MockableTestCase;
import org.cantaloupe.json.TestStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

public class SemTest extends MockableTestCase {
	/*
	 * -Dcantaloupe.url.es-events-search=http://localhost:9200/events/_search
	 * -Dcantaloupe.runtime.env=local
	 */
	@Override
	public void mock() {
		port(9200);
		post("/events/_search", (request, response) -> {
			return esRes("semDuplicatedAdditionalMaterials.json");
		});
	}

//@formatter:off
	@Test
	@Ignore
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
