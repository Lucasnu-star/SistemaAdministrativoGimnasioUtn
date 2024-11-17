package Clases.Menus;

import Clases.Entrenador;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonEntrenadores;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Miembro;
import Clases.Recepcionista;

import java.util.Scanner;

/**
 * Clase para mostrar el menu de entrenadores
 * te muestra las distintas cosas que podes hacer para gestionar un entrenador}
 *
 *  @version 1
 */

public class MenuEntrenadores {

    private static GestorJsonEntrenadores gestorJson = new GestorJsonEntrenadores();

    // Submenú de entrenadores
    public static void mostrarMenuEntrenadores(Scanner scanner) {

        int opcion;

        // por si se necesita un string
        String entrada;

        // por si se necesita un entrenador
        Entrenador entrenador = null;


        do {
            // cuando termina la funcion, lee de nuevo el archivo
            GestionGenericaGimnasio<Entrenador> lista = gestorJson.leerListaGenericaEntrenadores();

            // cada vez que termina la funcion, se limpia
            entrenador = new Entrenador();

            System.out.println("\nMenú entrenadores:");
            System.out.println("1. Mostrar entrenadores");
            System.out.println("2. Consultar entrenador");
            System.out.println("3. Agregar entrenador");
            System.out.println("4. Modificar entrenador");
            System.out.println("5. Eliminar entrenador");
            System.out.println("6. Asignar miembro a entrenador");
            System.out.println("7. Cantidad de miembros x entrenador");
            System.out.println("8. Agregar certificado a entrenador");
            System.out.println("9. Calcular salario");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar entrenadores...");
                    Recepcionista.mostrarElementosLista(lista);
                    break;
                case 2:
                    System.out.println("Consultar entrenador...");
                    System.out.println("Ingrese el dni");
                    entrada = scanner.nextLine();
                    entrenador = Recepcionista.consultar(lista, entrada);
                    System.out.println(entrenador);
                    break;
                case 3:
                    System.out.println("Agregar entrenador...");
                    entrenador = gestorJson.crearEntrenador();
                    Recepcionista.agregarDeLista(lista, entrenador.getDocumento(), entrenador);
                    gestorJson.grabar(lista);

                    break;
                case 4:
                    System.out.println("Modificar entrenador...");
                    System.out.println("ingrese el dni del entrenador a cambiar");
                    entrada = scanner.nextLine();
                    entrenador = Recepcionista.consultar(lista, entrada);
                    gestorJson.modificarEntrenador(entrenador);
                    gestorJson.grabar(lista);

                    break;
                case 5:
                    System.out.println("Eliminar entrenador...");
                    System.out.println("Ingrese documento del entrenador a eliminar");
                    entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(lista, entrada);
                    gestorJson.grabar(lista);
                    break;

                case 6:
                    GestorJsonMiembros gestorJsonMiembros = new GestorJsonMiembros();
                    GestionGenericaGimnasio<Miembro> listaMiembros = gestorJsonMiembros.leerListaGenericaMiembros();

                    System.out.println("Asignando miembro...");

                    // Mostrar entrenadores existentes
                    Recepcionista.mostrarElementosLista(lista);

                    // Solicitar documento del entrenador
                    System.out.println("Ingrese el documento del entrenador al que desea asignar un miembro:");
                    entrada = scanner.nextLine();

                    // Consultar el entrenador existente en la lista
                    entrenador = Recepcionista.consultar(lista, entrada);

                    if (entrenador != null) {
                        // Crear y asignar un nuevo miembro al entrenador existente
                        System.out.println("Ingrese el dni del miembro a asignar");
                        entrada = scanner.nextLine();
                        Miembro nuevoMiembro = Recepcionista.consultar(listaMiembros, entrada);
                        entrenador.asignarMiembro(nuevoMiembro);
                        System.out.println("Miembro asignado correctamente.");
                    } else {
                        System.out.println("No se encontró un entrenador con el documento proporcionado.");
                    }
                    gestorJson.grabar(lista);
                    break;

                case 7:
                    System.out.println("Mostrar miembros asignados a un entrenador...");

                    // Mostrar entrenadores disponibles
                    Recepcionista.mostrarElementosLista(lista);

                    // Solicitar el documento del entrenador
                    System.out.println("Ingrese el documento del entrenador para ver sus miembros asignados:");
                    entrada = scanner.nextLine();

                    // Buscar al entrenador
                    entrenador = Recepcionista.consultar(lista, entrada);

                    if (entrenador != null) {
                        // Llamar al método para consultar y mostrar los miembros asignados
                        entrenador.consultarMiembros();
                    } else {
                        System.out.println("No se encontró un entrenador con el documento proporcionado.");
                    }
                    break;

                case 8:
                    System.out.println("Agregando certificado..");
                    System.out.println("Ingrese el dni del entrenador ");
                    entrada = scanner.nextLine();

                    entrenador = Recepcionista.consultar(lista, entrada);

                    if (entrenador!= null){
                        System.out.println("Ingrese el certificado");
                        entrada = scanner.nextLine();
                        entrenador.agregarCertificado(entrada);
                    }

                    gestorJson.grabar(lista);

                    break;

                case 9:
                    System.out.println("Calculando salario..");
                    System.out.println("Ingrese dni ");
                    entrada = scanner.nextLine();

                    Recepcionista.calcularSalario(lista, entrada);

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





