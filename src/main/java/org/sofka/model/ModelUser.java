package org.sofka.model;

import lombok.*;
import org.sofka.controller.ControllerPushData;

@Getter
@Setter
@AllArgsConstructor
public class ModelUser {

    String name;
    Integer score;
    Boolean won;

    public void saveNewPlayer() {
        ControllerPushData.pushInCollection(this.name, this.score, this.won);
    }

}

