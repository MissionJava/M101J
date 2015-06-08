package com.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //MongoClient is heavy-weight object. so avoid to create more than 1 object
//        MongoClient mongoClient = new MongoClient(); //Default
//        MongoClient mongoClient = new MongoClient("localhost", 27017); //Specific to url and port
//        MongoClient mongoClient = new MongoClient(asList(new ServerAddress("localhost", 27017))); //List of Server addresses
//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017")); // mongo client uri
        
//        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build(); //default options
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(1000).build();	//change default options
        MongoClient mongoClient = new MongoClient(new ServerAddress(), mongoClientOptions);
        
        //get database "test"
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test").withReadPreference(ReadPreference.secondary());
        
        //get all collections from test database
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("test");
    }
}
