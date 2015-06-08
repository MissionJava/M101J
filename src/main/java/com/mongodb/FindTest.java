package com.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
public class FindTest {

	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("course");
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("findTest");
		
		mongoCollection.dropCollection();
		
		//insert 10 documents
		for (int i = 0; i < 10; i++) {
			mongoCollection.insertOne(new Document("x", i));
		}
		
		System.out.println("FindOne:");
		Document first = mongoCollection.find().first();
		Helpers.printJson(first);
		
		System.out.println("Find all with into:");
		List<Document> all = mongoCollection.find().into(new ArrayList<Document>());
		for (Document cur : all) {
			Helpers.printJson(cur);
		}
		
		System.out.println("Find all with iteration:");
		MongoCursor<Document> cursor= mongoCollection.find().iterator();
		try {
			while (cursor.hasNext()) {
				Document cur = (Document) cursor.next();
				Helpers.printJson(cur);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Count:");
		
		long count = mongoCollection.count();
		System.out.println(count);
	}

}
