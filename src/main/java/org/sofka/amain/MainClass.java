package org.sofka.amain;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;

import org.sofka.model.ModelQuestion;
import org.sofka.model.ModelQuiz;
import org.sofka.controller.ObjectQuestion;

public class MainClass {

    public static void main(String[] args) throws IOException, ParseException {

        JSONArray array = ObjectQuestion.returnArray();
        ArrayList<ModelQuestion> questions =  ObjectQuestion.mapQuestion(array);

        ModelQuiz quiz = new ModelQuiz(questions);
        quiz.getRandomQuestion(1);

    }

}
