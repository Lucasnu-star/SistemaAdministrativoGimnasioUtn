package Clases.Menus;

import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Miembro;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.util.Scanner;

/**
 * Clase para mostrar el menu de los miembros
 * te muestra las distintas cosas que podes hacer para gestionar un miembro
 *
 *  @version 1
 */
public class MenuMiembros implements iMenu {

    private static GestorJsonMiembros gestorJson = new GestorJsonMiembros();

    public MenuMiembros() {
    }

    //Submenú de miembros

    public void mostrarMenu() {

        Scanner scanner=new Scanner(System.in);

        int opcion;

        // por si se necesita un string
        String entrada;

        // por si se necesita un entrenador
        Miembro miembro = null;

        // actualiza el estado de las membresias comparando con la fecha actual
        gestorJson.actualizarMembresiasMiembros();

        do {
            // cada vez que termina la funcion, se lee el archivo
            GestionGenericaGimnasio<Miembro> listaMiembros = gestorJson.leerListaGenericaMiembros();

            // cada vez que termina la funcion, se limpia
            miembro = new Miembro();

            System.out.println("\nMenú miembros:");
            System.out.println("1. Mostrar Miembros");
            System.out.println("2. Consultar Miembro");
            System.out.println("3. Agregar Miembro");
            System.out.println("4. Modificar Miembro");
            System.out.println("5. Eliminar Miembro");
            System.out.println("6. Pagar couta");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();


            switch (opcion) {
                case 1:
                    System.out.println("Mostrar miembros...");
                    Recepcionista.mostrarElementosLista(listaMiembros);
                    break;
                case 2:
                    System.out.println("Consultar miembro...");
                    System.out.println("Ingrese DNI del miembro a buscar...");
                    entrada = scanner.nextLine();
                    miembro = Recepcionista.consultar(listaMiembros, entrada);
                    if (miembro != null) {
                        System.out.println(miembro);
                    } else {
                        System.out.println("Miembro no encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Agregar miembro...");
                    miembro = gestorJson.crearMiembro();
                    Recepcionista.agregarDeLista(listaMiembros, miembro.getDocumento(), miembro);

                    gestorJson.grabar(listaMiembros);
                    break;
                case 4:
                    System.out.println("Modificar miembro...");
                    System.out.println("Ingrese el DNI del miembro a modificar:");
                    entrada = scanner.nextLine();
                    miembro = Recepcionista.consultar(listaMiembros, entrada);

                    gestorJson.modificarMiembro(miembro);

                    gestorJson.grabar(listaMiembros);
                    break;
                case 5:
                    System.out.println("Eliminar miembro...");
                    System.out.println("Ingrese DNI del miembro a eliminar:");
                    entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(listaMiembros, entrada);

                    gestorJson.grabar(listaMiembros);
                    break;
                case 6:
                    System.out.println("Pagar couta...");
                    System.out.println("Ingrese DNI del miembro a buscar...");
                    entrada = scanner.nextLine();
                    miembro = Recepcionista.consultar(listaMiembros, entrada);
                    if (miembro != null) {
                        System.out.println(miembro);
                    } else {
                        System.out.println("Miembro no encontrado.");
                    }
                    System.out.println(Recepcionista.pagarCouta(miembro));

                    gestorJson.grabar(listaMiembros);
                    break;

                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 0);  // Cuando se ingresa 0 se vuelve al menú principal
    }

}



