package com.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static java.util.Arrays.asList;
public class InsertTest {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("course");
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("insertTest");
		mongoCollection.dropCollection();
		
		Document smith = new Document("name", "Smith").append("age", 30).append("profession", "programmer");
		
		Helpers.printJson(smith);
		/*mongoCollection.insertOne(smith);
		Helpers.printJson(smith);*/
		
		Document jones = new Document("name", "Jones").append("age", 25).append("profession", "Hacker");
		mongoCollection.insertMany(asList(smith, jones));
		Helpers.printJson(smith);
		Helpers.printJson(jones);
	}

}
