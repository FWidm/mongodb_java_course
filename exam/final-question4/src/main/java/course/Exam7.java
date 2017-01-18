package course;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class Exam7 {
    public static void main(String[] args) {
        MongoClient c =  new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

        Document animal = new Document("animal", "monkey");
        System.out.println(animal);

        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "cat");
        System.out.println(animal);
        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "lion");
        System.out.println(animal);

        animals.insertOne(animal);
    }
}