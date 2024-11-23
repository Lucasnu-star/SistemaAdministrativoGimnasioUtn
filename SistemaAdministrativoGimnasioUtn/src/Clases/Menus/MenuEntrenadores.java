package Clases.Menus;

import Clases.Entrenador;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonEntrenadores;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Miembro;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase para mostrar el menu de entrenadores
 * te muestra las distintas cosas que podes hacer para gestionar un entrenador
 *
 *  @version 1
 */

public class MenuEntrenadores implements iMenu {

    private static GestorJsonEntrenadores gestorJson = new GestorJsonEntrenadores();

    private static GestorJsonMiembros gestorJsonMiembros = new GestorJsonMiembros();

    public MenuEntrenadores() {
    }

    // Submenú de entrenadores


    @Override
    public void mostrarMenu() {

        Scanner scanner=new Scanner(System.in);
        int opcion;

        // por si se necesita un string
        String entrada;

        // por si se necesita un entrenador
        Entrenador entrenador = null;


        do {
            // cuando termina la funcion, lee de nuevo el archivo
            GestionGenericaGimnasio<Entrenador> lista = gestorJson.leerListaGenericaEntrenadores();
            GestionGenericaGimnasio<Miembro> listaMiembros = gestorJsonMiembros.leerListaGenericaMiembros();

            // cada vez que termina la funcion, se limpia
            entrenador = new Entrenador();

            limpiarConsola();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            limpiarConsola();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar entrenadores...");
                    Recepcionista.mostrarElementosLista(lista);

                    esperarTeclaParaContinuar();
                    break;
                case 2:
                    System.out.println("Consultar entrenador...");
                    System.out.println("Ingrese el dni");
                    entrada = scanner.nextLine();
                    entrenador = Recepcionista.consultar(lista, entrada);
                    System.out.println(entrenador);

                    esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregar entrenador...");
                    entrenador = gestorJson.crearEntrenador();
                    Recepcionista.agregarDeLista(lista, entrenador.getDocumento(), entrenador);
                    gestorJson.grabar(lista);

                    esperarTeclaParaContinuar();
                    break;
                case 4:
                    System.out.println("Modificar entrenador...");
                    System.out.println("Ingrese el dni del entrenador a cambiar");
                    entrada = scanner.nextLine();
                    entrenador = Recepcionista.consultar(lista, entrada);
                    gestorJson.modificarEntrenador(entrenador);
                    gestorJson.grabar(lista);

                    esperarTeclaParaContinuar();
                    break;
                case 5:
                    System.out.println("Eliminar entrenador...");
                    System.out.println("Ingrese documento del entrenador a eliminar");
                    entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(lista, entrada);
                    gestorJson.grabar(lista);

                    esperarTeclaParaContinuar();
                    break;

                case 6:
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
                        //MOSTRAR lista de miembors antes de pedir dni
                        System.out.println("Lista de miembros");
                        Recepcionista.mostrarElementosLista(listaMiembros);
                        System.out.println("Ingrese el dni del miembro a asignar");
                        entrada = scanner.nextLine();
                        Miembro nuevoMiembro = Recepcionista.consultar(listaMiembros, entrada);
                        entrenador.asignarMiembro(nuevoMiembro);
                        System.out.println("Miembro asignado correctamente.");
                    } else {
                        System.out.println("No se encontró un entrenador con el documento proporcionado.");
                    }
                    gestorJson.grabar(lista);

                    esperarTeclaParaContinuar();
                    break;

                case 7:
                    System.out.println("Eliminar miembro a entrenador...");

                    // Mostrar entrenadores existentes
                    Recepcionista.mostrarElementosLista(lista);

                    // Solicitar documento del entrenador
                    System.out.println("Ingrese el documento del entrenador al que desea eliminar un miembro:");
                    entrada = scanner.nextLine();

                    // Consultar el entrenador existente en la lista
                    entrenador = Recepcionista.consultar(lista, entrada);

                    if (entrenador != null) {
                        // Crear y asignar un nuevo miembro al entrenador existente
                        //MOSTRAR lista de miembros antes de pedir dni
                        if (!entrenador.getMiembrosAsignados().isEmpty()){
                            entrenador.consultarMiembros();
                            System.out.println("Ingrese el dni del miembro a eliminar");
                            entrada = scanner.nextLine();
                            Miembro miembroEliminar = Recepcionista.consultar(listaMiembros, entrada);
                            System.out.println(entrenador.eliminarMiembro(miembroEliminar));
                        }else{
                            System.out.println("El entrenador no tiene miembros asignados ");
                        }
                    } else {
                        System.out.println("No se encontró un entrenador con el documento proporcionado.");
                    }
                    gestorJson.grabar(lista);

                    esperarTeclaParaContinuar();
                    break;

                case 8:
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

                    esperarTeclaParaContinuar();
                    break;

                case 9:
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

                    esperarTeclaParaContinuar();

                    break;

                case 10:
                    System.out.println("Calculando salario..");
                    System.out.println("Ingrese dni ");
                    entrada = scanner.nextLine();

                    Recepcionista.calcularSalario(lista, entrada);

                    esperarTeclaParaContinuar();
                    break;
                case 11:
                    System.out.println("Filtrar por nombre...");
                    System.out.println("Ingrese el nombre por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.entrenadorFiltroPorNombre(lista,entrada);
                    break;
                case 12:
                    System.out.println("Filtrar por documento...");
                    System.out.println("Ingrese el documento por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.entrenadorFiltroPorDocumento(lista,entrada);
                    break;

                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 0);  // Cuando se ingresa 0 se vuelve al menú principal
    }


    @Override
    public String mostrarInterfaz() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMenú entrenadores:");
        sb.append("\n   1. Mostrar entrenadores");
        sb.append("\n   2. Consultar entrenador");
        sb.append("\n   3. Agregar entrenador");
        sb.append("\n   4. Modificar entrenador");
        sb.append("\n   5. Eliminar entrenador");
        sb.append("\n   6. Asignar miembro a entrenador");
        sb.append("\n   7. Eliminar miembro a entrenador");
        sb.append("\n   8. Mostrar miembros de x entrenador");
        sb.append("\n   9. Agregar certificado a entrenador");
        sb.append("\n  10. Calcular salario");
        sb.append("\n  11. Filtrar por nombre");
        sb.append("\n  12. Filtrar por documento");
        sb.append("\n   0. Volver al Menú Principal");
        sb.append("\nIngrese una opción: ");
        return sb.toString();
    }


    @Override
    public void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


    // Método para pausar y esperar que el usuario presione una tecla
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








