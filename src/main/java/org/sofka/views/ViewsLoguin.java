package org.sofka.views;

import org.jboss.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.sofka.model.ModelUser;

public class ViewsLoguin implements InterfaceViews {

    ViewsMenu menu = new ViewsMenu();

    final BufferedReader getDataBuffer = new BufferedReader(new InputStreamReader(System.in));
    final Logger log = Logger.getLogger("Logger");

    private String nameUser;

    public ModelUser createUser(String nameUser) {
        return new ModelUser(nameUser);
    }

    @Override
    public void viewContext() {

        try {

            log.info("Enter a user");
            nameUser = getDataBuffer.readLine();
            menu.setUser(createUser(nameUser));
            menu.viewContext();

        } catch (IOException e) {
            log.error(e);
        }

    }


}
