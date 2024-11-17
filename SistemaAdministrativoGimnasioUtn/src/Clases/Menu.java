package Clases;

import Clases.Menus.MenuEntrenadores;

import java.util.Scanner;

/**
 * Esta clase reprenseta el manejo del menu, usamos metodos con switch para que haya un optimo manejo de secciones.
 *
 *  @version 1
 */
public class Menu {

    //Menu principal
    public static void MenuPrincipal(Gimnasio gym) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        JSONArchivos.importarEntrenadoresDesdeJson(gym);
        JSONArchivos.importarMiembrosDesdeJson(gym);


        do {


            // Mostrar el menú principal
            System.out.println("\nMenú Principal:");
            System.out.println("1. Entrenadores");
            System.out.println("2. Miembros");
            System.out.println("3. Máquinas");
            System.out.println("4. Personal de Mantenimiento");

            System.out.println("0. Salir del Programa");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Llamar entrenadores
                    MenuEntrenadores.mostrarMenuEntrenadores(gym);
                    break;
                case 2:
                    // Llamar miembros
                    Menu.mostrarMenuMiembros(gym);
                    break;
                case 3:
                    // Llamar maquinas
                    Menu.mostrarMenuMaquinas(gym);
                    break;
                case 4:
                    // Llamar personal de mantenimiento
                    Menu.mostrarMenuMantenimiento(gym);
                    break;
                case 0:
                    System.out.println("¡Nos vemos! cerrando programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 0); // Se cierra el programa cuando se ingrese 0
    }
}