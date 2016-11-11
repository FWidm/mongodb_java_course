use test;
db.grades.aggregate([
  {$project:{"_id":{student_id : "$student_id", class_id : "$class_id"}, scores:1}},
  {$unwind:"$scores"},
  {$match:{"scores.type":{$in:["homework","exam"]}}},
  {$group:{_id:{student_id:"$_id.student_id", class_id:"$_id.class_id"}
    , score:{$avg:"$scores.score"}}
  },
  {$group:{_id:"$_id.class_id", avgscore:{$avg:"$score"}}},
  {$sort:{avgscore:-1}}
]);
