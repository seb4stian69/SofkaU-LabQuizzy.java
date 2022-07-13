package org.sofka.amain;

import org.sofka.controller.ControllerGetData;
import org.sofka.controller.ControllerPushData;

public class MainClass {

    public static void main(String[] args) {

        ControllerPushData.pushInCollection("Sebastian nuevo",15,false);

        ControllerGetData con = new ControllerGetData();
        con.getDataCollection();


    }

}
