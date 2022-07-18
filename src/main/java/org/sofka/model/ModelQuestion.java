package org.sofka.model;

import lombok.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
/*
 * @LuisaAvila/SebatianSantis
 * @class ModelQuestion se encarga de dar la estructura a la pregunta.
 */
public class ModelQuestion {

    String correct;
    String option1;
    String option2;
    String option3;
    String question;
    Integer level;

    /*
     * @LuisaAvila/SebatianSantis
     * @methodse encarga de validar la respuesta  del usuario.
     * @return retorna un buleano tru si la respuesta es correcta y uno false si esta es incorrecta
     */

    public Boolean confirm(String res) {
        return (Objects.equals(this.correct, res));
    }

}
