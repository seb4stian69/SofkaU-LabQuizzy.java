package org.sofka.views;

import org.jboss.logging.Logger;
import org.sofka.controller.ControllerGetData;
import org.sofka.model.ModelUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * @LuisaAvila/SebatianSantis
 * @class ViewsHistory se encarga de que el usuario pues revisar el historial de su partida.
 * @see InterfaceViews se implementa la interfaz.
 */

public class ViewsHistory implements InterfaceViews{

    final BufferedReader getDataBuffer = new BufferedReader(new InputStreamReader(System.in));
    private final ViewsMenu menu = new ViewsMenu();
    final Logger log = Logger.getLogger("Logger");
    private ModelUser saveUser = null;

    public void setUser(ModelUser user){
        this.saveUser = user;
    }

    public ViewsHistory(){/*Void constructor*/}


    /*
     * @LuisaAvila/SebatianSantis
     * @method viewContext obtiene la data de la bse de datos a través de ControllerGetData
     * @see ControllerGetData instancia la clase ControllerGetData para acceder al método ontrollerGetData.
     */



    @Override
    public void viewContext(){

        ControllerGetData getData = new ControllerGetData();
        getData.getDataCollection();

        try {

            log.info("Input any data for exit");
            getDataBuffer.readLine();

            log.info("You´re exit");
            menu.setUser(saveUser);
            menu.viewContext();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
