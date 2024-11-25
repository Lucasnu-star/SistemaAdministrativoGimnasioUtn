package Clases.Menus;

import Clases.*;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonPersonalMantenimiento;
import Clases.Gestoras.Validaciones;
import Excepciones.ListaVaciaExcepcion;
import Excepciones.UsuarioNoEncontradoExcepcion;
import Interfaces.iMenu;

import java.util.Scanner;

/**
 * Clase para mostrar el menu de entrenadores
 * te muestra las distintas cosas que podes hacer para gestionar a un empleado de mantenimiento
 *
 *  @version 1
 */

public class MenuPersonalMantenimiento implements iMenu {
    private final GestorJsonPersonalMantenimiento gestorJson;

    private GestionGenericaGimnasio<PersonalMantenimiento> listaPersonalM;

    public MenuPersonalMantenimiento() {
        gestorJson = new GestorJsonPersonalMantenimiento();
        listaPersonalM = new GestionGenericaGimnasio<>();
    }

    public GestorJsonPersonalMantenimiento getGestorJson() {
        return gestorJson;
    }

    public GestionGenericaGimnasio<PersonalMantenimiento> getListaPersonalM() {
        return listaPersonalM;
    }

    @Override
    public void mostrarMenu() {

        Scanner scanner=new Scanner(System.in);

        int opcion;

        // por si se necesita un string
        String entrada;

        // por si se necesita un empleado de mantenimiento
        PersonalMantenimiento empleadoM = null;
        do {
            listaPersonalM = gestorJson.leerListaGenericaPersonalM();

            Validaciones.limpiarConsola();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            Validaciones.limpiarConsola();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar personal: ");

                    try {
                        Recepcionista.mostrarElementosLista(listaPersonalM);

                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }


                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 2:
                    System.out.println("Consultar empleado...");
                    System.out.println("Ingrese DNI del empleado a buscar...");
                    entrada = scanner.nextLine();

                    try {
                        empleadoM = Recepcionista.consultar(listaPersonalM, entrada);
                        System.out.println(empleadoM);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregar emplado...");
                    empleadoM = gestorJson.crearEmpleadoMantenimiento();
                    Recepcionista.agregarDeLista(listaPersonalM, empleadoM.getDocumento(), empleadoM);

                    gestorJson.grabar(listaPersonalM);

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 4:
                    System.out.println("Modificar empleado...");
                    System.out.println("Ingrese el DNI del empleado a modificar:");
                    entrada = scanner.nextLine();

                    try {
                        empleadoM = Recepcionista.consultar(listaPersonalM, entrada);

                        gestorJson.modificarEmpladoM(empleadoM);

                        gestorJson.grabar(listaPersonalM);

                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 5:
                    System.out.println("Eliminar empleado...");
                    System.out.println("Ingrese DNI del empleado a eliminar:");
                    entrada = scanner.nextLine();

                    try {
                        Recepcionista.eliminarDeLista(listaPersonalM, entrada);

                        gestorJson.grabar(listaPersonalM);
                    }catch (UsuarioNoEncontradoExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 6:
                    System.out.println("Filtrar por nombre...");
                    System.out.println("Ingrese el nombre por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.mantenimientoFiltroPorNombre(listaPersonalM,entrada);
                    break;
                case 7:
                    System.out.println("Filtrar por documento...");
                    System.out.println("Ingrese el documento por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.mantenimientoFiltroPorDocumento(listaPersonalM,entrada);
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
        } while (opcion != 0); // Cuando se ingresa 0 se vuelve al menú principal
    }

    @Override
    public String mostrarInterfaz() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMenú Personal de mantenimiento:");
        sb.append("\n   1. Mostrar personal ");
        sb.append("\n   2. Consultar empleado ");
        sb.append("\n   3. Agregar empleado ");
        sb.append("\n   4. Modificar empleado ");
        sb.append("\n   5. Eliminar empleado ");
        sb.append("\n   6. Filtrar por nombre");
        sb.append("\n   7. Filtrar por documento");
        sb.append("\n   0. Volver al Menú Principal");
        sb.append("\nIngrese una opción: ");
        return sb.toString();
    }


}

