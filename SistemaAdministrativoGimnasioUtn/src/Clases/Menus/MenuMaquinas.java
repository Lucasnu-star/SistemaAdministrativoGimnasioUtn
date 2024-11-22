package Clases.Menus;

import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonMaquinas;
import Clases.Maquina;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.util.Scanner;

public class MenuMaquinas implements iMenu {

    private static GestorJsonMaquinas gestorMaq=new GestorJsonMaquinas();

    public MenuMaquinas() {
    }

    //Submenu maquinas

    public void mostrarMenu() {

        Scanner scanner=new Scanner(System.in);

        int opcion;

        // por si se necesita un string

        String entrada;

        // por si se necesita una maquina
        Maquina maq = null;

        do {

            GestionGenericaGimnasio<Maquina> maquinas = gestorMaq.leerListaGenericaMaquina();

            // Mostrar el menú principal
            System.out.println("\nMenú Maquinas:");
            System.out.println("1. Mostrar maquinas ");
            System.out.println("2. Eliminar maquina ");
            System.out.println("3. Agregar maquina");
            System.out.println("4. Crear reporte maquina ");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrando Maquinas...");
                    // Mostrar las máquinas
                    Recepcionista.mostrarElementosLista(maquinas);
                    break;
                case 2:
                    System.out.println("Eliminando Maquina...");
                    System.out.println("Ingrese el tipo de maquina que desea eliminar:");
                            entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(maquinas,entrada);
                    break;
                case 3:
                    System.out.println("Agregando Maquina");
                    maq= gestorMaq.crearMaquina();
                    Recepcionista.agregarDeLista(maquinas,maq.getId(),maq);
                    gestorMaq.grabar(maquinas);
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 0); // Cuando se ingresa 0 se vuelve al menú principal
    }
}
