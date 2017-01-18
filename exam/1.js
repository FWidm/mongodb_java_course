> db.messages.find(
...   {$and:[
...     {"headers.From":"andrew.fastow@enron.com"},
...     {"headers.To": { $in : ["john.lavorato@enron.com"]}}
...   ]}).count();
1
> db.messages.find(
...   {$and:[
...     {"headers.From":"andrew.fastow@enron.com"},
...     {"headers.To": { $in : ["jeff.skilling@enron.com"]}}
...   ]}).count();
3
