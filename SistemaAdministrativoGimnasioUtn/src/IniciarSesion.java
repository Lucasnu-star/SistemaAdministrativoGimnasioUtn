import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonRecepcionistas;
import Clases.Menus.MenuPrincipal;
import Clases.Recepcionista;

import java.util.Scanner;

/**
 * Esta clase sirve para que un recepcionista pueda iniciar sesion
 */
public class IniciarSesion {
    GestorJsonRecepcionistas gestorJson;

    public IniciarSesion() {
        this.gestorJson = new GestorJsonRecepcionistas();
    }

    public GestorJsonRecepcionistas getGestorJson() {
        return gestorJson;
    }

    /**
     * Esta metodo lee el archivo de recepcionistas, pide los datos para iniciar sesion,
     * verifica si existen, y abre el menu si los datos ingresados son correctos
     */
    public void IniciadoDeSesion() {
        GestionGenericaGimnasio<Recepcionista> recepcionistas = gestorJson.leerListaGenericaRecepcionistas();
        Scanner scanner = new Scanner(System.in);

        boolean iniciadoExitoso = false;

        do {
            System.out.println("Nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.println("Contraseña: ");
            String contrasenia = scanner.nextLine();

            for (Recepcionista recepcionista : recepcionistas.getGestionUsuario().values()) {
                if (recepcionista.getNombreUsuario().equals(nombreUsuario) &&
                        recepcionista.getContrasenia().equals(contrasenia)) {
                    System.out.println("Iniciaste sesión...");
                    iniciadoExitoso = true;
                    MenuPrincipal.mostrarMenuPrincipal();
                    break;
                }
            }

            if (!iniciadoExitoso) {
                System.out.println("Error: el usuario o contraseña son incorrectos");
            }

        } while (!iniciadoExitoso);
    }

}
