package com.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Projections.public class FindWithSortSkipLimitTest {

	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase mongoDatabase = mongoClient.getDatabase("course");
		MongoCollection<Document> mongoCollection = mongoDatabase
				.getCollection("findWithSortSkipLimitTest");

		mongoCollection.dropCollection();

		// insert 10 documents with two random integers
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				mongoCollection.insertOne(new Document().append("i", i).append(
						"j", j));
			}
		}
		
//		Bson projection = Projections.fields(Projections.include("i", "j"), Projections.excludeId());
		Bson sort = new Document("i", 1);
		
		List<Document> all = mongoCollection.find().sort(sort).into(new ArrayList<Document>());
		for (Document cur : all) {
			Helpers.printJson(cur);
		}
	}

}
