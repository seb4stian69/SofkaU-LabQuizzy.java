package org.sofka.model;

import lombok.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class ModelQuestion {

    String correct;
    String option1;
    String option2;
    String option3;
    String question;
    Integer level;

    public Boolean confirm(String res) {
        return (Objects.equals(this.correct, res));
    }

}
