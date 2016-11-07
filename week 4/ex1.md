Suppose you have a collection with the following indexes:

``` db.products.getIndexes()
[
    {
        "v" : 1,
        "key" : {
            "_id" : 1
        },
        "ns" : "store.products",
        "name" : "_id_"
    },
    {
        "v" : 1,
        "key" : {
            "sku" : 1
        },
                "unique" : true,
        "ns" : "store.products",
        "name" : "sku_1"
    },
    {
        "v" : 1,
        "key" : {
            "price" : -1
        },
        "ns" : "store.products",
        "name" : "price_-1"
    },
    {
        "v" : 1,
        "key" : {
            "description" : 1
        },
        "ns" : "store.products",
        "name" : "description_1"
    },
    {
        "v" : 1,
        "key" : {
            "category" : 1,
            "brand" : 1
        },
        "ns" : "store.products",
        "name" : "category_1_brand_1"
    },
    {
        "v" : 1,
        "key" : {
            "reviews.author" : 1
        },
        "ns" : "store.products",
        "name" : "reviews.author_1"
    }
]
```
Which of the following queries can utilize at least one index to find all matching documents, or to sort? Check all that apply.

Note: the text for some answers may wrap; you can ignore the wrapping.


+ [ ] db.store.explain().find({brand:"habla"})
+ [x] db.store.explain().find({brand:"habla"}).sort({price:1}) <- uses the price indexing as base
+ [x] db.store.explain().find({$and:[{price:{$gt:30}},{price:{$lt:50}}]}).sort({brand:1}) <- uses the price index as well
+ [ ] db.store.explain().find({brand:"habla"}).sort({category:1,brand:-1})
