package org.sofka.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.*;
import java.io.*;
import org.jboss.logging.Logger;
import java.util.Properties;

public class ControllerGetData {

    private final Logger log = Logger.getLogger("Logger");

    public ControllerGetData() {/*Void constructor*/}

    public String getURIMongo(){

        try (InputStream entry = new FileInputStream("src/main/config.properties")) {

            Properties props = new Properties();
            props.load(entry);

            return props.getProperty("mongoURL");

        }catch(IOException e){
            log.error(e);
            return e.toString();
        }

    }

    public void getDataCollection(){

        ConnectionString connectionString = new ConnectionString(getURIMongo());
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        try(MongoClient mongoClient = MongoClients.create(settings)){

            MongoDatabase database = mongoClient.getDatabase("history");

            MongoCollection<Document> collection = database.getCollection("historyschemas");

            try {

                try (MongoCursor<Document> data = collection.find().cursor()) {

                    while (data.hasNext()) {
                        log.info((data.next().toJson()));
                    }

                }

            } catch (Exception e) {
                log.error(e);
            }

        }

    }

}
