package Clases.Menus;

import Clases.*;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonPersonalMantenimiento;

import java.util.Scanner;

/**
 * Clase para mostrar el menu de entrenadores
 * te muestra las distintas cosas que podes hacer para gestionar a un empleado de mantenimiento
 *
 *  @version 1
 */

public class MenuPersonalMantenimiento{
    private static GestorJsonPersonalMantenimiento gestorJson = new GestorJsonPersonalMantenimiento();

    public static void mostrarMenuPersonalMantenimiento(Scanner scanner) {

        int opcion;

        // por si se necesita un string
        String entrada;

        // por si se necesita un empleado de mantenimiento
        PersonalMantenimiento empleadoM = null;
        do {
            GestionGenericaGimnasio<PersonalMantenimiento> personalM = gestorJson.leerListaGenericaPersonalM();
            // Mostrar el menú principal
            System.out.println("\nMenú Personal de mantenimiento:");
            System.out.println("1. Mostrar personal ");
            System.out.println("2. Consultar empleado ");
            System.out.println("3. Agregar empleado ");
            System.out.println("4. Modificar empleado ");
            System.out.println("5. Eliminar empleado ");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrar personal: ");
                    Recepcionista.mostrarElementosLista(personalM);
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
                    break;
                case 3:
                    System.out.println("Agregar emplado...");
                    empleadoM = gestorJson.crearEmpleadoMantenimiento();
                    Recepcionista.agregarDeLista(personalM, empleadoM.getDocumento(), empleadoM);

                    gestorJson.grabar(personalM);
                    break;
                case 4:
                    System.out.println("Modificar empleado...");
                    System.out.println("Ingrese el DNI del empleado a modificar:");
                    entrada = scanner.nextLine();
                    empleadoM = Recepcionista.consultar(personalM, entrada);

                    gestorJson.modificarEmpladoM(empleadoM);

                    gestorJson.grabar(personalM);
                    break;
                case 5:
                    System.out.println("Eliminar empleado...");
                    System.out.println("Ingrese DNI del empleado a eliminar:");
                    entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(personalM, entrada);

                    gestorJson.grabar(personalM);
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        } while (opcion != 0); // Cuando se ingresa 0 se vuelve al menú principal
    }
}

