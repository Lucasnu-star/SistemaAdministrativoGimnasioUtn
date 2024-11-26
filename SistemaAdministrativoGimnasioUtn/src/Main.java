import Clases.Menus.MenuPrincipal;


public class Main {
    public static void main(String[] args) {

        Data.cargarDatos();

        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.IniciadoDeSesion();

    }
}


