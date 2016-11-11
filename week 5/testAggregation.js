use test;

db.zips.aggregate([
  {$match: {$and:[
    { state:{ $in:[ "CA", "NY" ]}},
    { pop: {$gt:25000}}
  ]}},
  {$group: {_id: {city: "$city"}, popSumPerCity: {$sum: "$pop"}}},
  {$group: {_id: {states:"CA & NY"}, popavg1: {$avg: "$popSumPerCity"}}}
]);

db.zips.aggregate([
  {$match: { state:{ $in:[ "CA", "NY" ]}}},
  {$group: {_id: {city: "$city"}, pop: {$sum: "$pop"}}},
  {$match: {pop: {$gt: 25000}}},
  {$group: {_id: {states:"CA & NY"}, popavg2: {$avg: "$pop"}}}
]);

db.zips.aggregate([
  {$match: { $or: [{state: "CA"}, {state: "NY"}]}},
  {$group: {_id: {city: "$city"}, pop: {$sum: "$pop"}}},
  {$match: {pop: {$gt: 25000}}},
  {$group: {_id: {states:"CA & NY"}, popavg3: {$avg: "$pop"}}}
]);
