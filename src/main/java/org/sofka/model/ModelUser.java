package org.sofka.model;

import lombok.*;
import org.sofka.controller.ControllerPushData;

@Getter @Setter @NoArgsConstructor
public class ModelUser {

    String name;
    Integer score;
    Boolean won;

    /*
     * @LuisaAvila @SebastianSantis
     * @Class ModelUser() , clase que se instancia por primera vez en el loguin para la creacion de un objeto usuario el cual se le iran actualizando sus atributos a medida que conteste el quiz
     * @params String name, el nombre que se le pasa al inicio de la aplicacion en el loguin
     */

    public ModelUser(String name){
        this.name = name;
    }

    /*
     * @LuisaAvila @SebastianSantis
     * @Method saveNewPlayer(), metodo de la clase usuario que se llama al momento de que el mismo finalize (gane, pierda) el quiz, sirve para almacenar los datos que el tenga registrados hasta ese momento
     */

    public void saveNewPlayer() {
        ControllerPushData.pushInCollection(this.name, this.score, this.won);
    }

}

