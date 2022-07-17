package org.sofka.views;

import org.jboss.logging.Logger;
import org.sofka.model.ModelUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ViewsMenu implements InterfaceViews {

    final BufferedReader getDataBuffer = new BufferedReader(new InputStreamReader(System.in));
    final Logger log = Logger.getLogger("Logger");
    ModelUser user = null;

    public ViewsMenu() {/*Void constructor*/}

    public void setUser(ModelUser user) {
        this.user = user;
    }

    @Override
    public void viewContext() throws RuntimeException {

        log.info("\nMENU:\n1] Starts Game\n2] View history\n3] Exit");

        Integer option;

        try {
            option = Integer.parseInt(getDataBuffer.readLine());

            controlMenu(option);

        } catch (IOException e) {
            log.error(e);
        }


    }

    public void controlMenu(Integer option) {
        switch (option) {

            case 1 -> {
                ViewsQuiz quiz = new ViewsQuiz();
                quiz.viewContext();
            }

            case 2 -> {

            }

            case 3 -> System.exit(0);

            default -> {

                log.info("wrong option");
                ViewsLoguin login = new ViewsLoguin();
                login.viewContext();

            }

        }

    }

}


