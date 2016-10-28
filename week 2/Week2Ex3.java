package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.Document;

import org.bson.conversions.Bson;

import java.util.ArrayList;

public class Week2Ex3 {

    public static void main(String[] args){
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(110).build();
        MongoClient client = new MongoClient();

        MongoDatabase db=client.getDatabase("students");
        MongoCollection<BsonDocument> collection=db.getCollection("grades", BsonDocument.class);


        /*EX: Write a program in the language of your choice that will remove the grade of type "homework"
         with the lowest score for each student from the dataset in the handout. Since each document is one grade,
         it should remove one document per student. This will use the same data set as the last problem, but if you
         don't have it, you can download and re-import.*/
        Bson sort = new Document("student_id",1).append("score",1);
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
        }

    }

}
