package Clases.Menus;

import Clases.Entrenador;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonEntrenadores;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Gestoras.Validaciones;
import Clases.Miembro;
import Clases.Recepcionista;
import Enums.eEspecialidad;
import Enums.eTipoMaquina;
import Excepciones.ListaVaciaExcepcion;
import Excepciones.UsuarioNoEncontradoExcepcion;
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

    private final GestorJsonEntrenadores gestorJson;

    private final GestorJsonMiembros gestorJsonMiembros;

    private GestionGenericaGimnasio<Entrenador> lista;

    private GestionGenericaGimnasio<Miembro> listaMiembros;

    public MenuEntrenadores() {
        lista = new GestionGenericaGimnasio<>();
        listaMiembros = new GestionGenericaGimnasio<>();
        gestorJson = new GestorJsonEntrenadores();
        gestorJsonMiembros = new GestorJsonMiembros();
    }

    public GestorJsonEntrenadores getGestorJson() {
        return gestorJson;
    }

    public GestorJsonMiembros getGestorJsonMiembros() {
        return gestorJsonMiembros;
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
            lista = gestorJson.leerListaGenericaEntrenadores();
            listaMiembros = gestorJsonMiembros.leerListaGenericaMiembros();

            // cada vez que termina la funcion, se limpia
            entrenador = new Entrenador();

            Validaciones.limpiarConsola();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            Validaciones.limpiarConsola();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar entrenadores...");

                    try {
                        Recepcionista.mostrarElementosLista(lista);
                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 2:
                    System.out.println("Consultar entrenador...");
                    System.out.println("Ingrese el dni");
                    entrada = scanner.nextLine();

                    try {
                        entrenador = Recepcionista.consultar(lista, entrada);
                        System.out.println(entrenador);
                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregar entrenador...");
                    entrenador = gestorJson.crearEntrenador();

                    Recepcionista.agregarDeLista(lista, entrenador.getDocumento(), entrenador);
                    gestorJson.grabar(lista);

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 4:
                    System.out.println("Modificar entrenador...");
                    System.out.println("Ingrese el dni del entrenador a cambiar");
                    entrada = scanner.nextLine();

                    try {
                        entrenador = Recepcionista.consultar(lista, entrada);
                        gestorJson.modificarEntrenador(entrenador);
                        gestorJson.grabar(lista);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 5:
                    System.out.println("Eliminar entrenador...");
                    System.out.println("Ingrese documento del entrenador a eliminar");
                    entrada = scanner.nextLine();

                    try {
                        System.out.println(Recepcionista.eliminarDeLista(lista, entrada));
                        gestorJson.grabar(lista);
                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;

                case 6:
                    System.out.println("Asignando miembro...");

                    // Mostrar entrenadores existentes
                    Recepcionista.mostrarElementosLista(lista);

                    // Solicitar documento del entrenador
                    System.out.println("Ingrese el documento del entrenador al que desea asignar un miembro:");
                    entrada = scanner.nextLine();

                    try{
                    // Consultar el entrenador existente en la lista
                    entrenador = Recepcionista.consultar(lista, entrada);

                        // Crear y asignar un nuevo miembro al entrenador existente
                        // MOSTRAR lista de miembors antes de pedir dni
                        System.out.println("Lista de miembros");
                        Recepcionista.mostrarElementosLista(listaMiembros);

                        System.out.println("Ingrese el dni del miembro a asignar");
                        entrada = scanner.nextLine();

                        Miembro nuevoMiembro = Recepcionista.consultar(listaMiembros, entrada);
                        entrenador.asignarMiembro(nuevoMiembro);
                        System.out.println("Miembro asignado correctamente.");

                        gestorJson.grabar(lista);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;

                case 7:
                    System.out.println("Eliminar miembro a entrenador...");

                    try {
                        // Mostrar entrenadores existentes
                        Recepcionista.mostrarElementosLista(lista);

                        // Solicitar documento del entrenador
                        System.out.println("Ingrese el documento del entrenador al que desea eliminar un miembro:");
                        entrada = scanner.nextLine();

                        // Consultar el entrenador existente en la lista
                        entrenador = Recepcionista.consultar(lista, entrada);

                        //MOSTRAR lista de miembros antes de pedir dni
                        entrenador.consultarMiembros();
                        System.out.println("Ingrese el dni del miembro a eliminar");
                        entrada = scanner.nextLine();

                        Miembro miembroEliminar = Recepcionista.consultar(listaMiembros, entrada);
                        System.out.println(entrenador.eliminarMiembro(miembroEliminar));

                        gestorJson.grabar(lista);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;

                case 8:
                    System.out.println("Mostrar miembros asignados a un entrenador...");

                    try {
                        // Mostrar entrenadores disponibles
                        Recepcionista.mostrarElementosLista(lista);

                        // Solicitar el documento del entrenador
                        System.out.println("Ingrese el documento del entrenador para ver sus miembros asignados:");
                        entrada = scanner.nextLine();

                        // Buscar al entrenador
                        entrenador = Recepcionista.consultar(lista, entrada);
                        entrenador.consultarMiembros();

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;

                case 9:
                    System.out.println("Agregando certificado..");
                    System.out.println("Ingrese el dni del entrenador ");
                    entrada = scanner.nextLine();

                    try {
                        entrenador = Recepcionista.consultar(lista, entrada);

                        System.out.println("Ingrese el certificado");
                        entrada = scanner.nextLine();
                        entrenador.agregarCertificado(entrada);
                        gestorJson.grabar(lista);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }


                    Validaciones.esperarTeclaParaContinuar();

                    break;

                case 10:
                    System.out.println("Calculando salario..");
                    System.out.println("Ingrese dni ");
                    entrada = scanner.nextLine();

                    Recepcionista.calcularSalario(lista, entrada);

                    Validaciones.esperarTeclaParaContinuar();
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
                case 13:
                    System.out.println("Filtrar por especialidad...");
                    System.out.println("Ingrese la opcion deseada por la que desea filtrar...");
                    eEspecialidad[] tipo = eEspecialidad.values();
                    for (int i = 0; i < tipo.length; i++) {
                        System.out.println((i + 1) + ". " +tipo[i].name());
                    }
                    opcion = -1;
                    boolean opcionValida = false;

                    // Validar la entrada del usuario
                    while (!opcionValida) {
                        try {
                            System.out.print("Ingrese el número de la opción deseada: ");
                            opcion = Integer.parseInt(scanner.nextLine());

                            if (opcion >= 1 && opcion <= tipo.length) {
                                opcionValida = true;
                            } else {
                                System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y " + tipo.length + ".");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada no válida. Por favor, ingrese un número.");
                        }
                    }
                    Recepcionista.entrenadorFiltroPorTipo(lista, tipo[opcion - 1]);
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
        sb.append("\n  13. Filtrar por especialidad");
        sb.append("\n   0. Volver al Menú Principal");
        sb.append("\nIngrese una opción: ");
        return sb.toString();
    }


}








