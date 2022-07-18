package org.sofka.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.*;
import java.io.*;
import org.jboss.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Properties;

public class ControllerGetData {

    private final Logger log = Logger.getLogger("Logger");

    /*
     * @LuisaAvila @SebastianSantis
     * @Class la clase ControllerGetData() la utilizamos para inicializar el controlador que recibe los datos dentro de la base de datos en mongo, esto con la finalidad de procesarlos y mostrarlos en pantalla dentro de la vista del historial
     * */

    public ControllerGetData() {/*Void constructor*/}

    /*
     * @LuisaAvila @SebastianSantis
     * @method getURIMongo() tiene la función de obtener la uri de mongoDb que esta dentro del archivo de config.properties
     * @return String url, la clase retorna un string con los datos de la url de la base de datos en la nube
     * */

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

    /*
     * @LuisaAvila @SebastianSantis
     * @method el método getDataCollection() mediante las funciones del paquete com.mongodb, nos obtiene la base de datos y la coleccion de la misma donde se encuentra la informacion suministrada por los usuarios al momento de finalizar su quiz, luego mediante un cursor y un ciclo while pintamos la informacion en consola destructurando los datos mediante el metodo get() el cual recibe la clave que obtiene el valor dentro de la base de datos
     * @see Se muestran en consola los datos formateados de todas las personas que finalizaron (ganaron o perdieron) el quiz
     * * */

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

                        Object ob = new JSONParser().parse( data.next().toJson() );
                        JSONObject objectJson = (JSONObject) ob;

                        String name = objectJson.get("user").toString();
                        Integer score = Integer.parseInt(objectJson.get("score").toString());
                        Boolean won = Boolean.parseBoolean(objectJson.get("win").toString());

                        log.info("User: " + name + " | Score: " + score + " | Result: " + won );

                    }

                }

            } catch (Exception e) {
                log.error(e);
            }

        }

    }

}
