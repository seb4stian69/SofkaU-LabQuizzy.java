package org.sofka.views;

import org.jboss.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.sofka.controller.ObjectQuestion;
import org.sofka.model.ModelQuestion;
import org.sofka.model.ModelQuiz;
import org.sofka.model.ModelUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ViewsQuiz implements InterfaceViews {

    private final Scanner getData = new Scanner(System.in);
    final Logger log = Logger.getLogger("Logger");
    private ModelQuestion question = null;
    private Integer level = 0;
    private Integer score = 0;
    private final ViewsMenu menu = new ViewsMenu();
    private ModelUser saveUser = null;

    public void setUser(ModelUser user){
        this.saveUser = user;
    }

    private String[] randomOptions;

    public ViewsQuiz(){/*Void constructor*/}

    public void getQuestion(){

        level+=1;

        try{

            JSONArray array = ObjectQuestion.returnArray();
            ArrayList<ModelQuestion> questions =  ObjectQuestion.mapQuestion(array);

            ModelQuiz quiz = new ModelQuiz(questions);
            question = quiz.getRandomQuestion(level);

        } catch (IOException|ParseException e) {
            log.error(e);
        }

    }

    public void updateData(){

        getQuestion();

        String correct = question.getCorrect();
        String option1 = question.getOption1();
        String option2 = question.getOption2();
        String option3 = question.getOption3();

        randomOptions = new String[] {correct,option1,option2,option3};

    }

    public void validate(String answer, Integer index){

        if(Boolean.TRUE.equals(question.confirm( randomOptions[index] ))){

            score+=1;

            if(score == 5){

                saveUser.setScore(score);
                saveUser.setWon(true);
                saveUser.saveNewPlayer();

                log.info("! You have won, congratulations");
                menu.viewContext();

            } else {
                viewContext();
            }

        } else{

            saveUser.setScore(score);
            saveUser.setWon(false);
            saveUser.saveNewPlayer();

            log.error(
                    "Answer ["+answer+"] incorrect\n" +
                    "you will be sent to the menu where in the [History] section you can see your results"
            );

            menu.viewContext();

        }

    }

    @Override
    public void viewContext (){

        updateData();

        Arrays.sort(randomOptions);

        log.info(
                "\n\n[" + saveUser.getName() + "] | Level - [" + level + "] | Score: " + score + " | ( If you need exit digit [E] or [e] )" +
                "\nQuestion: " + question.getQuestion() +
                "\n\nA] " + randomOptions[0] +
                "\nB] " + randomOptions[1] +
                "\nC] " + randomOptions[2] +
                "\nD] " + randomOptions[3] +
                "\n\nPut your answer:"
        );

        String answer = getData.nextLine();

        switch ( answer.toUpperCase() ){

            case "A" -> validate(answer,0);
            case "B" -> validate(answer,1);
            case "C" -> validate(answer,2);
            case "D" -> validate(answer,3);
            case "E" -> menu.viewContext();

            default -> {
                log.error("[¡Wrong value!] You will be sent to the menu");
                menu.viewContext();
            }

        }

    }



}
