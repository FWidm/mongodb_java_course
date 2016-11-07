In this problem you will analyze a profile log taken from a different mongoDB instance and you will import it into a collection named sysprofile. To start, please download sysprofile.json from Download Handout link and import it with the following command:

`mongoimport --drop -d m101 -c profile sysprofile.json`

Now query the profile data, looking for all queries to the students collection in the database school2, sorted in order of decreasing latency. What is the latency of the longest running operation to the collection, in milliseconds?

Answer:
```json
> db.profile.find({ns:"school2.students"}).sort({millis:-1}).pretty()
{
	"_id" : ObjectId("581dadc230d340b578c8cacc"),
	"ts" : ISODate("2012-11-20T20:09:49.862Z"),
	"op" : "query",
	"ns" : "school2.students",
	"query" : {
		"student_id" : 80
	},
	"ntoreturn" : 0,
	"ntoskip" : 0,
	"nscanned" : 10000000,
	"keyUpdates" : 0,
	"numYield" : 5,
	"lockStats" : {
		"timeLockedMicros" : {
			"r" : 19776550,
			"w" : 0
		},
		"timeAcquiringMicros" : {
			"r" : 4134067,
			"w" : 5
		}
	},
	"nreturned" : 10,
	"responseLength" : 2350,
	"millis" : 15820,
	"client" : "127.0.0.1",
	"user" : ""
}
```
