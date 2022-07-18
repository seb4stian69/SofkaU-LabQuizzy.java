package org.sofka.amain;

import org.sofka.views.ViewsLogin;

public class MainClass {

    /*
    * @LuisaAvila @SebastianSantis
    * @method el método main nos otorga la primera vista la cual es el login
    * @see con el metodo viewContext() agregamos la vista a la consola luego de iniciar la clase login
    */

    public static void main(String[] args) {

        /*
         * @LuisaAvila @SebastianSantis
         * @Class login, instancia de la clase login la cual nos sirve para renderizar en consola el login incial de la aplicacion
         * @Method viewContext(), Metodo utilizado para renderizar la vista en consola del ViewsLogin
         */

        ViewsLogin login = new ViewsLogin();
        login.viewContext();

    }

}
