package org.sofka.model;

import lombok.*;
import java.util.List;
import java.util.Objects;


@Setter @Getter
public class ModelQuiz {

    List<ModelQuestion> questions;
    Integer level =1;
    Integer score;

    /*
     * @LuisaAvila @SebastianSantis
     * @Class la clase ModelQuiz nos sirve para crear un objeto de tipo quiz el cual recibira un arreglo de preguntas que se pasara como parametro
     * @params List<ModelQuestion> questions, es la el arreglo de preguntas que se utiliza dentro de la clase
     */

    public ModelQuiz(List<ModelQuestion> questions){
        this.questions = questions;
    }

    /*
     * @LuisaAvila @SebastianSantis
     * @Method getRandomQuestion(), sirve para retornar una pregunta aleatoria haciendo uso del metodo filter el cual nos ayuda a filtrar por medio del nivel de la pregunta y retornandonos un nuevo arreglo de preguntas para luego mediante el metodo random pasar una pregunta en especifico de esa lista creada con el filter
     * @params Integer level, el nivel que se le pasa al metodo para que realize el filtrado correspondiente
     * @return Modelquestion question, retorna una pregunta de clase ModelQuestion
     */

    public ModelQuestion getRandomQuestion(Integer level) {

        List<ModelQuestion> questionRandom = questions.stream().filter(
                qL -> Objects.equals(qL.getLevel(), level)
        ).toList();

        int random = (int) ( Math.random() * questionRandom.size() );
        return questionRandom.get(random);

    }

}
