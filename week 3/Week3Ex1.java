package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.bson.conversions.Bson;

import java.util.ArrayList;

/**
 * Created by fabianwidmann on 28/10/16.
 */
public class Week3Ex1 {
    /* Write a program in the language of your choice that will remove the lowest homework score for each student.
     Since there is a single document for each student containing an array of scores, you will need to update the scores array and remove the homework.

     Remember, just remove a homework score. Don't remove a quiz or an exam!

     Hint/spoiler: With the new schema, this problem is a lot harder and that is sort of the point.
     One way is to find the lowest homework in code and then update the scores array with the low homework pruned.
     */
    public static void main(String[] args){
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(110).build();
        MongoClient client = new MongoClient();

        MongoDatabase db=client.getDatabase("school");
        MongoCollection<BsonDocument> collection=db.getCollection("students", BsonDocument.class);


        ArrayList<BsonDocument> docs = collection.find().into(new ArrayList<BsonDocument>());
        for (BsonDocument doc:
             docs) {
            System.out.println("doc="+doc);
            BsonArray array=doc.getArray("scores");
            BsonDocument lowestHw=null;
            for (BsonValue item :
                    array) {
                BsonDocument currDoc=item.asDocument();
                System.out.println(">> currDoc="+currDoc+" get(type)="+currDoc.get("type"));
                if(currDoc.containsValue(new BsonString("homework"))){
                    if(lowestHw==null)
                        lowestHw=currDoc;
                    else if(lowestHw.get("score").asDouble().getValue()>currDoc.get("score").asDouble().getValue()){
                        lowestHw=currDoc;
                    }
                }
            }
            System.out.println(lowestHw);
            array.remove(lowestHw);
            System.out.println(array);
            //db.students.updateOne({"_id":1},{$pull:{"scores":{"type":"homework"}}})
            System.out.println(collection.find(new Document("_id",doc.get("_id"))).first());
            System.out.println(collection.updateOne(new Document("_id",doc.get("_id")), new Document("$pull",new Document("scores",lowestHw))));
        }

/*        Bson sort = new Document("student_id",1).append("score",1);
        Bson filter = new Document("type","homework");
        //get a list of all homework docs sorted by student id, then score -> first one can be removed
        ArrayList<BsonDocument> docs = collection.find(filter).sort(sort).into(new ArrayList<BsonDocument>());
        BsonInt32 prevId=new BsonInt32(-1);
        for (BsonDocument document :
                docs) {
            //search for the id change -> delete the first doc
            if(prevId.intValue() < document.get("student_id").asInt32().intValue()){
                prevId=document.getInt32("student_id");
                collection.deleteOne(document);
            }
        }*/

    }

}
