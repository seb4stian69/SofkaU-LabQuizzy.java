package org.sofka.views;

/*
 * @LuisaAvila @SebastianSantis
 * @Interface la interface InterfaceViews() la utilizamos para generar el metodo viewContext el cual implementaran todas las clases de tipo Views para mostrar la vista principal dependiendo su contexto (Quiz, menu, login, etc.)
 * */

@FunctionalInterface
public interface InterfaceViews {
    void viewContext() ;
}
