
package com.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

public class Week2_3_HW_Test {

    public static void main(String[] args) {

        MongoClient client = new MongoClient();
        DB database = client.getDB("students");
        
        DBCollection collection = database.getCollection("grades");

        BasicDBObject query = new BasicDBObject("type", "homework");
        DBCursor cursor = collection.find(query).sort(new BasicDBObject("student_id", 1).append("score", -1));
        List<Object> map = new ArrayList<Object>();
        Double test = 0d;
        ObjectId prev = new ObjectId();
        try {
            while(cursor.hasNext()) {
                DBObject obj = cursor.next();
                Double student_id = (Double) obj.get("student_id");
                System.out.println(obj);
                System.out.println("test " + test + " <--> " + student_id);
                if (!test.equals(student_id)) {
                    System.out.println("\t### removing "+student_id+", _id "+prev.toString());
                    map.add(prev);
                }
                test = student_id;
                prev = (ObjectId) obj.get("_id");
            }
            map.add(prev);
        } finally {
            cursor.close();
        }
        for (int i=0; i < map.size(); i++) {
            System.out.println("going to remove: "+map.get(i));
            collection.remove(new BasicDBObject("_id",map.get(i)));
        }
    }

}
