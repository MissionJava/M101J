package com.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

public class FindWithProjectionsTest {

	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("course");
		MongoCollection<Document> mongoCollection = mongoDatabase
				.getCollection("findWithProjectionsTest");

		mongoCollection.dropCollection();

		// insert 10 documents with two random integers
		for (int i = 0; i < 10; i++) {
			mongoCollection.insertOne(new Document()
					.append("x", new Random().nextInt(2))
					.append("y", new Random().nextInt(100)).append("i", i));
		}

		Bson filter = Filters.and(Filters.eq("x", 0), Filters.gt("y", 10),
				Filters.lt("y", 90));
		// Bson projection = new Document("x", 0).append("_id", 0); //exclude x
		// Bson projection = new Document("y", 1).append("i", 1); //include y
		// and i
		Bson projection = Projections.exclude("x", "_id");
		// Bson projection = Projections.include("x", "_id");
		/*
		 * here x = 0 means exclude x fields from result output for x:0 Find all
		 * with into: { "_id" : ObjectId("55760e56d92d5d06ace3a17b"), "y" : 21,
		 * "i" : 1 } { "_id" : ObjectId("55760e56d92d5d06ace3a17d"), "y" : 69,
		 * "i" : 3 } { "_id" : ObjectId("55760e56d92d5d06ace3a180"), "y" : 15,
		 * "i" : 6 } { "_id" : ObjectId("55760e56d92d5d06ace3a182"), "y" : 72,
		 * "i" : 8 } { "_id" : ObjectId("55760e56d92d5d06ace3a183"), "y" : 17,
		 * "i" : 9 }
		 * 
		 * =================================== output for append _id:0 Find all
		 * with into: { "y" : 35, "i" : 1 } { "y" : 53, "i" : 3 } { "y" : 58,
		 * "i" : 5 } { "y" : 29, "i" : 7 } { "y" : 84, "i" : 8 }
		 */

		System.out.println("Find all with into:");
		List<Document> all = mongoCollection.find(filter)
				.projection(projection).into(new ArrayList<Document>());
		for (Document cur : all) {
			Helpers.printJson(cur);
		}
	}

}
