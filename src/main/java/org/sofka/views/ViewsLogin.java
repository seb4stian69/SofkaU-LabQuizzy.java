package org.sofka.views;

import org.jboss.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.sofka.model.ModelUser;
/*
 * @LuisaAvila/SebatianSantis
 * @class ViewsLogin es se encarga de generar la vista para que el jugador guardesu usuario.
 * @see InterfaceViews se implementa la interfaz.
 */
public class ViewsLogin implements InterfaceViews {

    ViewsMenu menu = new ViewsMenu();

    final BufferedReader getDataBuffer = new BufferedReader(new InputStreamReader(System.in));
    final Logger log = Logger.getLogger("Logger");

    public ModelUser createUser(String nameUser) {
        return new ModelUser(nameUser);
    }

    /*
     * @LuisaAvila/SebatianSantis
     * @class viewContext se encarga de guardar en una variable el nombre de usuario y luego con el método se guarda en la clase menú para luego se enviado a la base de dtaos.
     * @see  se instancia la clase menú para guardar el nombre del usuaerio y luego desplegar el menú.
     */

    @Override
    public void viewContext() {

        try {

            log.info("Enter a user");
            String nameUser = getDataBuffer.readLine();
            menu.setUser(createUser(nameUser));
            menu.viewContext();

        } catch (IOException e) {
            log.error(e);
        }

    }


}
