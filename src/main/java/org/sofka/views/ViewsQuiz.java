package org.sofka.views;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.sofka.controller.ObjectQuestion;
import org.sofka.model.ModelQuestion;
import org.sofka.model.ModelQuiz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ViewsQuiz {

    private final Scanner getData = new Scanner(System.in);
    private ModelQuestion question = null;
    private ModelQuiz quiz = null;
    private Integer level = 1;
    private Integer score =0;
    private String[] randomOptions;

    public ViewsQuiz(){/*Void constructor*/}

    public void getQuestion(){

        try{

            JSONArray array = ObjectQuestion.returnArray();
            ArrayList<ModelQuestion> questions =  ObjectQuestion.mapQuestion(array);

            quiz = new ModelQuiz(questions);
            question = quiz.getRandomQuestion(level);

        } catch (IOException|ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateData(){

        getQuestion();

        String quest = question.getQuestion();
        String correct = question.getCorrect();
        String option1 = question.getOption1();
        String option2 = question.getOption2();
        String option3 = question.getOption3();

        randomOptions = new String[] {correct,option1,option2,option3};

        System.out.println("\n" + quest + "\n");

    }

    public void viewQuiz (){

        updateData();

        Arrays.sort(randomOptions);

        for (String option: randomOptions){

            System.out.println(option);

        };

        String answer = getData.nextLine();

        if(Boolean.TRUE.equals(question.confirm( answer ))){

            System.out.println("Es correcto");
            level+=1;
            score +=10;

            viewQuiz();

        }else {
            System.out.println("Es incorrecto");
            level =1;
            score =0;
            ///eejcutar vista nueva
        }


    }



}
