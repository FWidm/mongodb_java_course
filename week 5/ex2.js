use test;
db.zips.aggregate([
  {$match: { $or: [{state: "CA"}, {state: "NY"}]}},
  {$group: {_id: {city: "$city", state: "$state"}, pop: {$sum: "$pop"}}},
  {$match: {pop: {$gt: 25000}}},
  {$group: {_id: {states:"CA & NY"}, popavg: {$avg: "$pop"}}}]);
db.zips.aggregate([
  { $group:{ "_id":{ "state":"$state", "city":"$city" },
    "pop":{ $sum:"$pop" } } },
  { $match:{ "_id.state":
    { $in:[ "CA", "NY" ] }, "pop":{ $gt:25000 } } },
  { $group:{ "_id":null, "pop":{ $avg:"$pop" } } } ]);
