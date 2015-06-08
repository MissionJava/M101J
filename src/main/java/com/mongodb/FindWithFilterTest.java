package com.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
public class FindWithFilterTest {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("course");
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("findWithFilterTest");
		
		mongoCollection.dropCollection();
		
		//insert 10 documents with two random integers
		for (int i = 0; i < 10; i++) {
			mongoCollection.insertOne(new Document().append("x", new Random().nextInt(2)).append("y", new Random().nextInt(100)));
		}
		
//		Bson filter = new Document("x", 0).append("y", new Document("$gt", 10).append("$lt", 90));
		/*Bson filter = Filters.eq("x", 0);*/
		Bson filter = Filters.and(Filters.eq("x", 0), Filters.gt("y", 10), Filters.lt("y", 90));
		System.out.println("Find all with into:");
		List<Document> all = mongoCollection.find(filter).into(new ArrayList<Document>());
		for (Document cur : all) {
			Helpers.printJson(cur);
		}
		
		System.out.println("Count:");
		
		long count = mongoCollection.count(filter);
		System.out.println(count);
	}

}
