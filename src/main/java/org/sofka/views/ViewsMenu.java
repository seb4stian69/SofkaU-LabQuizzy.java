package org.sofka.views;

import org.jboss.logging.Logger;
import org.sofka.model.ModelUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * @LuisaAvila@SebatianSantis
 * @class ViewsMenu se realiza la construcción del menú que visualizará el usuario.
 * @see se implementa la insterfaz InterfaceViews.
 */
public class ViewsMenu implements InterfaceViews {

    final BufferedReader getDataBuffer = new BufferedReader(new InputStreamReader(System.in));
    final Logger log = Logger.getLogger("Logger");
    private ModelUser user = null;

    public ViewsMenu() {/*Void constructor*/}

    public void setUser(ModelUser user) {
        this.user = user;
    }

    @Override
    public void viewContext() throws RuntimeException {

        log.info("\nMenu:\n1] Starts Game\n2] View history\n3] Exit\n\nPut your option: ");

        Integer option;

        try {

            option = Integer.parseInt(getDataBuffer.readLine());
            controlMenu(option);

        } catch (IOException e) {
            log.error(e);
        }


    }
    /*
     * @LuisaAvila@SebatianSantis
     * @class ControlMenu se encarga de ejecutar las diferentes funciones según opcion que introduzca el usuario.
     * @param option hace referencia a la opción que introduce el usuario.
     * @see se implementa el método quiz.viewContext para redirigir al quiz al usuario, quiz.setUser para guara el usuario,login.viewContext para redirigir el usuario al login.
     */
    public void controlMenu(Integer option) {

        switch (option) {

            case 1 -> {
                ViewsQuiz quiz = new ViewsQuiz();
                quiz.setUser(user);
                quiz.viewContext();
            }

            case 2 -> {
                ViewsHistory history = new ViewsHistory();
                history.setUser(user);
                history.viewContext();
            }

            case 3 -> System.exit(0);

            default -> {

                log.info("wrong option");
                ViewsLogin login = new ViewsLogin();
                login.viewContext();

            }

        }

    }

}


