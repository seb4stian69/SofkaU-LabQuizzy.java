package org.sofka.model;

import lombok.*;
import org.sofka.controller.ControllerPushData;

@Getter
@Setter
public class ModelUser {

    String name;
    Integer score;
    Boolean won;

    public ModelUser(String name){
        this.name = name;
    }

    public void saveNewPlayer() {
        ControllerPushData.pushInCollection(this.name, this.score, this.won);
    }

}

