package Clases.Menus;

import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Miembro;
import Clases.Recepcionista;
import Excepciones.ListaVaciaExcepcion;
import Excepciones.UsuarioNoEncontradoExcepcion;
import Interfaces.iMenu;

import java.io.IOException;
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

    @Override
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

            limpiarConsola();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            limpiarConsola();


            switch (opcion) {
                case 1:
                    System.out.println("Mostrar miembros...");
                    try {
                        Recepcionista.mostrarElementosLista(listaMiembros);

                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    esperarTeclaParaContinuar();
                    break;
                case 2:
                    System.out.println("Consultar miembro...");
                    System.out.println("Ingrese DNI del miembro a buscar...");
                    entrada = scanner.nextLine();

                    try {
                        miembro = Recepcionista.consultar(listaMiembros, entrada);
                        System.out.println(miembro);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregar miembro...");
                    miembro = gestorJson.crearMiembro();
                    Recepcionista.agregarDeLista(listaMiembros, miembro.getDocumento(), miembro);

                    gestorJson.grabar(listaMiembros);

                    esperarTeclaParaContinuar();
                    break;
                case 4:
                    System.out.println("Modificar miembro...");
                    System.out.println("Ingrese el DNI del miembro a modificar:");
                    entrada = scanner.nextLine();

                    try {
                        miembro = Recepcionista.consultar(listaMiembros, entrada);

                        gestorJson.modificarMiembro(miembro);
                        gestorJson.grabar(listaMiembros);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    esperarTeclaParaContinuar();
                    break;
                case 5:
                    System.out.println("Eliminar miembro...");
                    System.out.println("Ingrese DNI del miembro a eliminar:");
                    entrada = scanner.nextLine();

                    try {
                        System.out.println(Recepcionista.eliminarDeLista(listaMiembros, entrada));

                        gestorJson.grabar(listaMiembros);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    esperarTeclaParaContinuar();
                    break;
                case 6:
                    System.out.println("Pagar couta...");
                    System.out.println("Ingrese DNI del miembro a buscar...");
                    entrada = scanner.nextLine();

                    try {
                        miembro = Recepcionista.consultar(listaMiembros, entrada);

                        System.out.println(Recepcionista.pagarCouta(miembro));

                        gestorJson.grabar(listaMiembros);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    esperarTeclaParaContinuar();
                    break;
                case 7:
                    System.out.println("Filtrar por nombre...");
                    System.out.println("Ingrese el nombre por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.miembroFiltroPorNombre(listaMiembros,entrada);
                    break;
                case 8:
                    System.out.println("Filtrar por documento...");
                    System.out.println("Ingrese el documento por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.miembroFiltroPorDocumento(listaMiembros,entrada);
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
        sb.append("\nMenú miembros:");
        sb.append("\n   1. Mostrar lista miembros");
        sb.append("\n   2. Consultar Miembro");
        sb.append("\n   3. Agregar Miembro");
        sb.append("\n   4. Modificar Miembro");
        sb.append("\n   5. Eliminar Miembro");
        sb.append("\n   6. Pagar couta");
        sb.append("\n   7. Filtrar por nombre");
        sb.append("\n   8. Filtrar por documento");
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





