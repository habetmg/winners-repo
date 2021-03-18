//package helper;
//
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//public class ConnectToDB {
//
//    public static void main(String args[]) {
//        var mongoClient = MongoClients.create("mongodb://api:priotix@217.113.26.164:27033/api-reviews?3t.connection.name=ts-api-reviews-db+-+imported+on+Nov+20%2C+2020&3t.connectTimeout=10000&3t.uriVersion=2&3t.defaultColor=31,163,86&3t.connectionMode=direct&readPreference=primary&3t.databases=api-reviews&authSource=admin&3t.socketTimeout=0");
//            MongoDatabase database = mongoClient.getDatabase("api-bookmakers");
//            MongoCollection<Document> collection = database.getCollection("bookmakers");
//            collection.drop();
//        }
//    }

