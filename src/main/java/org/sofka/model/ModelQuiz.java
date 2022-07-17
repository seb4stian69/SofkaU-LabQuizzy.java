package org.sofka.model;

import lombok.*;
import java.util.List;
import java.util.Objects;


@Setter
@Getter
public class ModelQuiz {

    List<ModelQuestion> questions;
    Integer level;
    Integer score;

    public ModelQuiz(List<ModelQuestion> questions){

        this.questions = questions;

    }

    public ModelQuestion getRandomQuestion(Integer level) {

        List<ModelQuestion> questionRandom = questions.stream().filter(
                (qL) -> Objects.equals(qL.getLevel(), level)
        ).toList();

        int random = (int) ( Math.random() * questionRandom.size() );
        return questionRandom.get(random);

    }


}
