package Clases.Menus;

import Clases.*;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonPersonalMantenimiento;
import Interfaces.iMenu;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase para mostrar el menu de entrenadores
 * te muestra las distintas cosas que podes hacer para gestionar a un empleado de mantenimiento
 *
 *  @version 1
 */

public class MenuPersonalMantenimiento implements iMenu {
    private static GestorJsonPersonalMantenimiento gestorJson = new GestorJsonPersonalMantenimiento();

    public MenuPersonalMantenimiento() {
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
            GestionGenericaGimnasio<PersonalMantenimiento> personalM = gestorJson.leerListaGenericaPersonalM();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar personal: ");
                    Recepcionista.mostrarElementosLista(personalM);

                    esperarTeclaParaContinuar();
                    break;
                case 2:
                    System.out.println("Consultar empleado...");
                    System.out.println("Ingrese DNI del empleado a buscar...");
                    entrada = scanner.nextLine();
                    empleadoM = Recepcionista.consultar(personalM, entrada);
                    if (empleadoM != null) {
                        System.out.println(empleadoM);
                    } else {
                        System.out.println("Empleado no encontrado.");
                    }

                    esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregar emplado...");
                    empleadoM = gestorJson.crearEmpleadoMantenimiento();
                    Recepcionista.agregarDeLista(personalM, empleadoM.getDocumento(), empleadoM);

                    gestorJson.grabar(personalM);

                    esperarTeclaParaContinuar();
                    break;
                case 4:
                    System.out.println("Modificar empleado...");
                    System.out.println("Ingrese el DNI del empleado a modificar:");
                    entrada = scanner.nextLine();
                    empleadoM = Recepcionista.consultar(personalM, entrada);

                    gestorJson.modificarEmpladoM(empleadoM);

                    gestorJson.grabar(personalM);

                    esperarTeclaParaContinuar();
                    break;
                case 5:
                    System.out.println("Eliminar empleado...");
                    System.out.println("Ingrese DNI del empleado a eliminar:");
                    entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(personalM, entrada);

                    gestorJson.grabar(personalM);

                    esperarTeclaParaContinuar();
                    break;
                case 6:
                    System.out.println("Filtrar por nombre...");
                    System.out.println("Ingrese el nombre por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.mantenimientoFiltroPorNombre(personalM,entrada);
                    break;
                case 7:
                    System.out.println("Filtrar por documento...");
                    System.out.println("Ingrese el documento por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.mantenimientoFiltroPorDocumento(personalM,entrada);
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

