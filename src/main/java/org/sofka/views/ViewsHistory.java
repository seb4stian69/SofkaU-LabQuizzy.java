package org.sofka.views;

import org.sofka.controller.ControllerGetData;

public class ViewsHistory implements InterfaceViews{

    String newplayer;

    public  ViewsHistory(){/*Constructor*/};

    @Override
    public void viewContext(){

        ControllerGetData getData = new ControllerGetData();
        getData.getDataCollection();


    }

}
