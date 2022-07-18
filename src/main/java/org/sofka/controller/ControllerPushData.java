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

    /* @LuisaAvila @SebastianSantis
     * @Class ControllerPushData() es una clase privada que no se utiliza pero que ayuda a hacer referencia a que es una clase que unicamente contiene metodos utiles que se ejecutan dentro de la aplicacion
     */

    private ControllerPushData(){throw new IllegalStateException("Utility class");}

    /*
     * @LuisaAvila @SebastianSantis
     * @method getURIMongo() tiene la función de obtener la uri de mongoDb que esta dentro del archivo de config.properties
     * @return String url, la clase retorna un string con los datos de la url de la base de datos en la nube
     * */

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

    /* @LuisaAvila @SebastianSantis
     * @method el método pushInCollection() sirve como su nombre lo indica para agregar datos a la collecion de mongo la cual contiene la informacion de los usuarios que han realizado el quiz
     * @param String user, contiene el nombre del usuario registrado en el loguin al momento de iniciar la aplicacion
     * @param Integer score, contiene el puntaje del usuario al finalizar (ganar o perder) el quiz
     * @param Boolean win, contiene un dato booleano que especifica si el usuario gano o no
     */

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
