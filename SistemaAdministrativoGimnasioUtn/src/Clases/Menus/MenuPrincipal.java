package Clases.Menus;

import java.util.Scanner;
/**
 * Clase para acceder al menu principal
 * te presenta las distintas secciones
 *
 *  @version 1
 */
public class MenuPrincipal {

    public static void mostrarMenuPrincipal() {
        // unico scanner
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {

            // Mostrar el menú principal
            System.out.println("\nMenú Principal:");
            System.out.println("1. Entrenadores");
            System.out.println("2. Miembros");
            System.out.println("3. Máquinas");
            System.out.println("4. Personal de Mantenimiento");
            System.out.println("5. Mostrar datos del gimnasio ");

            System.out.println("0. Salir del Programa");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Llama al menu entrenadores
                    MenuEntrenadores.mostrarMenuEntrenadores(scanner);
                    break;
                case 2:
                    // Llamar miembros
                    MenuMiembros.mostrarMenuMiembros(scanner);
                    break;
                case 3:
                    // Llamar maquinas
                    System.out.println("ccc");
                    break;
                case 4:
                    // Llamar personal de mantenimiento
                    System.out.println("dddd");
                    break;
                case 5:
                    //Carga los datos del gimnasio desde el archivo y los muestra
                    System.out.println("eeee");
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
