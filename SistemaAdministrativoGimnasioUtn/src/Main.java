import Clases.*;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonEntrenadores;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Menus.MenuPrincipal;
import Clases.Gestoras.GestorJsonRecepcionistas;


public class Main {
    public static void main(String[] args) {

        Gimnasio gimnasio = new Gimnasio();

        // Cargar datos al gimnasio
        Data.cargarDatos(gimnasio);

        // Control del json
        GestorJsonEntrenadores gestorJsonEntrenadores = new GestorJsonEntrenadores();
        gestorJsonEntrenadores.grabar(gimnasio.getGestionEntrenadores());

        GestionGenericaGimnasio<Entrenador> entrenadors = new GestionGenericaGimnasio<>();
        entrenadors = gestorJsonEntrenadores.leerListaGenericaEntrenadores();
        System.out.println(entrenadors);

        GestorJsonMiembros gestorJsonMiembros = new GestorJsonMiembros();
        gestorJsonMiembros.grabar(gimnasio.getGestionMiembros());

        // iniciar sesion solo para recepcionistas
        IniciarSesion iniciarSesion = new IniciarSesion();
        iniciarSesion.IniciadoDeSesion();








    }
}


