package org.sofka.views;

import org.jboss.logging.Logger;
import org.sofka.model.ModelUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ViewsMenu implements InterfaceViews {

    final BufferedReader getDataBuffer = new BufferedReader(new InputStreamReader(System.in));
    final Logger log = Logger.getLogger("Logger");
    ModelUser user = null;

    public ViewsMenu(){/*Void constructor*/}

    public void setUser(ModelUser user){
        this.user = user;
    }

    @Override
    public void viewContext(){

          log.info(" MENU:" +
                  "1] Starts Game" +
                  "2] View history" +
                  "3] Exit");

        Integer option = null;

        try {
            option = Integer.parseInt(getDataBuffer.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // witch (option){
        //
        //    case 1 -> viewQuiz()

        //

    }

    // public void viewMenu(){
    //     Scanner nom
    // }

}
