use enron;

// mongo < 2.js
// MongoDB shell version: 3.2.10
// connecting to: test
// switched to db enron
// { "From" : "susan.mara@enron.com", "To" : "jeff.dasovich@enron.com", "Total" : 750 }

db.messages.aggregate([
{$unwind: "$headers.To"},
{$group:{_id:"$_id", from:{$first:"$headers.From"}, to:{$addToSet:"$headers.To"}}},
//unwind to[]
{$unwind: "$to"},
{$group: {_id: { from:'$from', to:'$to' }, total : {$sum : 1}}},
{$sort: { total: -1}},
{$limit: 1}
]);﻿

db.messages.aggregate([
{$unwind: '$headers.To'},
{$group: {_id : '$_id', From : {$first: '$headers.From'}, UnwindTo: {$addToSet : '$headers.To'}}},
{$unwind: '$UnwindTo'},
{$group: {_id: { From:'$From', To:'$UnwindTo' }, total : {$sum : 1}}},
{$sort: { total: -1}},
{$project: {_id:0, From: '$_id.From', To : '$_id.To', Total: '$total'}},
{$limit: 1}
]);
