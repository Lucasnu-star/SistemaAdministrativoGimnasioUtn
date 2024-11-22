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

        int opcion;

        do {

            // Mostrar el menú principal
            System.out.println("\nMenú Principal:");
            System.out.println("1. Entrenadores");
            System.out.println("2. Miembros");
            System.out.println("3. Personal de Mantenimiento");
            System.out.println("4. Maquinas");
            System.out.println("0. Salir del Programa");
            System.out.print("Ingrese una opción: ");


            Scanner scanner = new Scanner(System.in);
            opcion = scanner.nextInt();


            switch (opcion) {
                case 1:
                    // Llama al menu entrenadores
                    MenuEntrenadores entr=new MenuEntrenadores();
                    entr.mostrarMenu();
                    break;
                case 2:
                    // Llamar miembros
                    MenuMiembros miem=new MenuMiembros();
                    miem.mostrarMenu();
                    break;
                case 3:
                    // Llamar personal de mantenimiento
                    MenuPersonalMantenimiento pers=new MenuPersonalMantenimiento();
                    pers.mostrarMenu();
                    break;
                case 4:
                    //Carga los datos del gimnasio desde el archivo y los muestra
                    MenuMaquinas maq=new MenuMaquinas();
                    maq.mostrarMenu();
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

