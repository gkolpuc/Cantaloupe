package org.cantaloupe.examples;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.ES;
import org.cantaloupe.functions.Assertion;
import org.cantaloupe.functions.Functions;
import org.cantaloupe.util.Resource;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import com.mashape.unirest.http.exceptions.UnirestException;

public class RecordDateQueryTest {

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@Test
	@Ignore
	public void recordDateConsistentRaw() throws IOException, UnirestException {

		Cantaloupe
				.json(ES.search("events_corax").body(Resource.esReq("recordDateQuery.json")).asJson())
				.getArray("hits.hits")
				.count(Functions.greatherThan(1, "Should find at least 1 document"))
				.forEach(
						Functions.compare("_source.Derived.EventDate", "_source.Derived.SortingDate.RecordDate",
								Assertion.EQUAL))
				.forEach(Functions.compareTo("_source.Derived.EventDateLabel", "RECORD_DATE", Assertion.EQUAL))
				.atLeastOne(Functions.compareTo("_source.Derived.EventDateLabel", "RECORD_DATE", Assertion.EQUAL));

	}

}
