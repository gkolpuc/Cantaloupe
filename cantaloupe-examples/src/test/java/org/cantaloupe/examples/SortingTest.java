package org.cantaloupe.examples;

import static org.cantaloupe.SortDef.sort;
import static org.cantaloupe.json.SortOrder.Asc;
import static org.cantaloupe.json.SortOrder.Desc;

import java.io.IOException;

import org.cantaloupe.Cantaloupe;
import org.cantaloupe.examples.util.API;
import org.cantaloupe.http.HttpStatus;
import org.cantaloupe.json.TestStatus;
import org.cantaloupe.junit.CantaloupeJUnitRunner;
import org.cantaloupe.util.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mashape.unirest.http.exceptions.UnirestException;

//@formatter:off
@RunWith(CantaloupeJUnitRunner.class)
public class SortingTest {
	
	@Test
	public void shouldSortByThreeIndicators() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success ==
					Cantaloupe.call(API.search()
							.queryString("size", "200")
							.body(Resource.epReq("sort/DateSpecPointer_desc&Date_asc.json"))
							.asJson())
							.verify(HttpStatus.ok())
							.json()
				.getArray("events")
				.verify(
						sort().by("EventData.Derived.EventDateSpecifierSortPointer", Desc)
							.by("EventData.Derived.EventDate", Asc)
							.relyOn("EventData.extendedEventId"))
				.status());

	}

	@Test
	public void shouldSortDate() throws UnirestException, IOException {
		Assert.assertTrue(
			TestStatus.Success ==
					Cantaloupe.json(API.search()
						.queryString("size", "200")
						.body(Resource.epReq("sort/Date_asc.json"))
						.asJson())
				.getArray("events")
				.verify(
						sort().by("EventData.Derived.EventDate", Asc)
							.relyOn("EventData.extendedEventId"))
				.status());

	}

	@Test
	public void shouldSortByEconomicIndicator() throws UnirestException, IOException {
		
		Assert.assertTrue(
			TestStatus.Success == 
					Cantaloupe.json(API.search()
							.queryString("size", "200")
							.body(Resource.epReq("sort/EconomicIndicatorIssue.json"))
							.asJson())
				.getArray("events")
				.verify(
						sort().by("EventData.Derived.CountryRank", Desc)
							.by("EventData.Enrichment.EconomicIndicator.EconIndECIBundle", Asc)
							.by("EventData.Derived.EconomicIndicatorRank", Asc)
							.relyOn("EventData.extendedEventId"))
				.status());

	}
}
// @formatter:on
