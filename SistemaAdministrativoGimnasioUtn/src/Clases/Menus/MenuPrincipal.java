package Clases.Menus;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.io.IOException;
import java.util.Scanner;
/**
 * Clase para acceder al menu principal
 * te presenta las distintas secciones
 *
 *  @version 1
 */
public class MenuPrincipal implements iMenu{
    private final Recepcionista recepcionista;

    public MenuPrincipal(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    @Override
    public void mostrarMenu() {
        // unico scanner

        int opcion;

        do {

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            Scanner scanner = new Scanner(System.in);
            opcion = scanner.nextInt();

            limpiarConsola();

            switch (opcion) {
                case 1:
                    // Llama al menu entrenadores
                    MenuEntrenadores entr = new MenuEntrenadores();
                    entr.mostrarMenu();
                    limpiarConsola();
                    break;
                case 2:
                    // Llamar miembros
                    MenuMiembros miem = new MenuMiembros();
                    miem.mostrarMenu();
                    limpiarConsola();
                    break;
                case 3:
                    // Llamar personal de mantenimiento
                    MenuPersonalMantenimiento pers = new MenuPersonalMantenimiento();
                    pers.mostrarMenu();
                    limpiarConsola();
                    break;
                case 4:
                    //Carga los datos del gimnasio desde el archivo y los muestra
                    MenuMaquinas maq = new MenuMaquinas();
                    maq.mostrarMenu();
                    limpiarConsola();
                    break;

                case 5:
                    System.out.println("Perfil: \n"+recepcionista);
                    break;
                case 0:
                    System.out.println("¡Nos vemos! cerrando programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
                    esperarTeclaParaContinuar();
            }
        } while (opcion != 0); // Se cierra el programa cuando se ingrese 0
    }

    @Override
    public String mostrarInterfaz() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMenú Principal: ");
        sb.append(" \n   1. Entrenadores ");
        sb.append(" \n   2. Miembros ");
        sb.append(" \n   3. Personal de mantenimiento ");
        sb.append(" \n   4. Maquinas");
        sb.append(" \n   5. Ver perfil ");
        sb.append(" \n   0. Salir del programa ");
        sb.append(" \n ");
        sb.append("\nIngrese una opcion ");
        return sb.toString();
    }

    @Override
    public void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    @Override
    public void esperarTeclaParaContinuar(){
        System.out.println("\nPresione cualquier numero o simbolo para continuar...");
        try {
            System.in.read(); // Espera una entrada
            System.in.read(); // Limpia el salto de línea residual
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

