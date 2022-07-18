package org.sofka.controller;

import org.jboss.logging.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import org.json.simple.*;
import org.json.simple.parser.*;
import org.sofka.model.ModelQuestion;

/*
 * @LuisaAvila @SebatianSantis
 * @class ObjectQuestion se encarga de obtener la data de preguntas.
 */

public class ObjectQuestion {

    private final static Logger log = Logger.getLogger("Logger");
    private static final String jsonPath = getJsonPath();

    /*
     * @LuisaAvila @SebatianSantis
     * @method ObjectQuestion trae la data de preguntas del archivo properties.
     * @return retorna la data de preguntas
     */


    private ObjectQuestion() {
        throw new IllegalStateException("utility class");
    }

    public static String getJsonPath() {

        try (InputStream entry = new FileInputStream("src/main/config.properties")) {

            Properties props = new Properties();
            props.load(entry);

            return props.getProperty("jsonPath");

        } catch (IOException e) {
            log.error(e);
            return e.toString();
        }

    }

    /*
     * @LuisaAvila @SebatianSantis
     * @method returnArray se encarga de analizar la cadena de texto como JSON y accede al elemento array.
     * @return retorna los objetos dentro del array.
     */


    public static JSONArray returnArray() throws IOException, ParseException {

        Object ob = new JSONParser().parse(new FileReader(jsonPath));
        JSONObject objectJson = (JSONObject) ob;
        return (JSONArray) objectJson.get("array");

    }

    /*
     * @LuisaAvila @SebatianSantis
     * @method mapQuestion se accede a los datos de cada uno de los objetos y se instancia la clase model para que cada clave del JSON se convierta en un atributo de la clase y asi acceder a la pregunta.
     * @return retorna una estructura de datos
     */


    public static ArrayList<ModelQuestion> mapQuestion(JSONArray array) {

        ArrayList<ModelQuestion> arrayQuestions = new ArrayList<>();

        for (Object o : array) {

            JSONObject object = (JSONObject) o;

            String correct = object.get("correct").toString();
            String option1 = object.get("option1").toString();
            String option2 = object.get("option2").toString();
            String option3 = object.get("option3").toString();
            String question = object.get("question").toString();
            Integer level = Integer.parseInt(object.get("level").toString());

            ModelQuestion objectQuestion = new ModelQuestion(correct, option1, option2, option3, question, level);

            arrayQuestions.add(objectQuestion);

        }

        return arrayQuestions;

    }


}
