package org.sofka.amain;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import org.sofka.controller.ObjectQuestion;

public class MainClass {

    public static void main(String[] args) throws IOException, ParseException {

        JSONArray array = ObjectQuestion.returnArray();
        ObjectQuestion.mapQuestion(array);
    }

}
