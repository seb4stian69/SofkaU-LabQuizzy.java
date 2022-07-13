package org.sofka.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jboss.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ControllerPushData {

    private static final Logger log = Logger.getLogger("Logger");

    private ControllerPushData(){throw new IllegalStateException("Utility class");}

    public static String getURIMongo(){

        try (InputStream entry = new FileInputStream("src/main/config.properties")) {

            Properties props = new Properties();
            props.load(entry);

            return props.getProperty("mongoURL");

        }catch(IOException e){
            log.error(e);
            return e.toString();
        }

    }

    public static void pushInCollection(String user, Integer score, Boolean win) {

        ConnectionString connectionString = new ConnectionString(getURIMongo());
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        try(MongoClient mongoClient = MongoClients.create(settings)){

            MongoDatabase database = mongoClient.getDatabase("history");
            MongoCollection<Document> collection =  database.getCollection("historyschemas");

            Document doc = new Document("user",user)
                    .append("score",score)
                    .append("win",win);

            collection.insertOne(doc);

        }

    }

}
