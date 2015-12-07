Lista brakujacych rzeczy

Feature:

1.http response status - DONE!!!
2.array contains

Core:

1.request do elastica przez api/send

2.Json request template (do wysylania requestow w petli):

 Przykladowy test :
for (var ii = 0; ii < eventids.length; ii++) { arr.push(
.. { url: 
EPHost + "/events/details" + " HEADER:Content-Type:application/json", 
req: 
JSON.stringify(buildreq(eventids[ii])) }.. )
)

function buildreq(id){ 
var obj =
{"appId": "RegressionTest", "query": { "uuid": TESTUUID, "eventIdType": "EXTENDED" ,"eventIds":[ String(id) ]} };
return obj; }



3.Failowanie testu z messagem (chociaz na sout)

4.Z Tadkiem rozmawialismy o generwoaniu jakiegos loga (green/(red + message) + details request / response)
