# Cantaloupe


Cantaloupe is open-source library for testing REST services. It provides fulent API for JSON data manipulation and assertion.

#### Examples

###### Sorting
```
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
```

###### Asserting field value
```
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
```


```
	@Test
	public void eventTypeLookupContainsSampleId() throws UnirestException {
		Assert.assertTrue(
			TestStatus.Success == 
				Cantaloupe.json(API.get("/cache/lookup/eventtypes")
									.asJson())
				.getLongVal("1000351539.id")
				.assertEquals(1000351539L)
				.status());
	}
```

```
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
```

###### Asserting array count
```
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
```

###### Array functions
```
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
```


```
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
```



```
