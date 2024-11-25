package Clases.Menus;

import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonMiembros;
import Clases.Gestoras.Validaciones;
import Clases.Miembro;
import Clases.Recepcionista;
import Enums.eTipoMembresia;
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

    private final GestorJsonMiembros gestorJson;

    private GestionGenericaGimnasio<Miembro> listaMiembros;

    public MenuMiembros() {
        gestorJson = new GestorJsonMiembros();
        listaMiembros = new GestionGenericaGimnasio<>();
    }

    public GestionGenericaGimnasio<Miembro> getListaMiembros() {
        return listaMiembros;
    }

    public GestorJsonMiembros getGestorJson() {
        return gestorJson;
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
            listaMiembros = gestorJson.leerListaGenericaMiembros();

            // cada vez que termina la funcion, se limpia
            miembro = new Miembro();

            Validaciones.limpiarConsola();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            Validaciones.limpiarConsola();


            switch (opcion) {
                case 1:
                    System.out.println("Mostrar miembros...");
                    try {
                        Recepcionista.mostrarElementosLista(listaMiembros);

                    }catch (ListaVaciaExcepcion e){
                        System.out.println(e.getMessage());
                    }

                    Validaciones.esperarTeclaParaContinuar();
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

                    Validaciones.esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregar miembro...");
                    miembro = gestorJson.crearMiembro();
                    Recepcionista.agregarDeLista(listaMiembros, miembro.getDocumento(), miembro);

                    gestorJson.grabar(listaMiembros);

                    Validaciones.esperarTeclaParaContinuar();
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

                    Validaciones.esperarTeclaParaContinuar();
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

                    Validaciones.esperarTeclaParaContinuar();
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

                    Validaciones.esperarTeclaParaContinuar();
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
                case 9:
                    System.out.println("Filtrando segun estado de membresía...");
                    System.out.println("Ingrese la opcion deseada por la que desea filtrar...");
                    System.out.println("1. Activa");
                    System.out.println("2. Vencida");
                    boolean opcionValid = false;
                    boolean valor= true;
                    // Validar la entrada del usuario
                    while (!opcionValid) {
                        try {
                            opcion = Integer.parseInt(scanner.nextLine());
                            if (opcion > 0 && opcion <= 2) {
                                opcionValid=true;
                            }else {
                                System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y 2.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada no válida. Por favor, ingrese un número.");
                        }
                    }
                    if (opcion == 1) {
                        valor = true;
                    } else {
                        valor = false;
                    }
                    Recepcionista.miembroFiltroPorEstado(listaMiembros,valor);
                    break;
                case 10:
                    System.out.println("Filtrando segun tipo de membresia...");
                    System.out.println("Ingrese la opcion deseada por la que desea filtrar...");
                    eTipoMembresia[] tipo = eTipoMembresia.values();
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
                    Recepcionista.miembroFiltroPorTipo(listaMiembros, tipo[opcion - 1]);
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
        sb.append("\n   9. Filtrar por estado membresia");
        sb.append("\n   10. Filtrar por tipo de membresia");
        sb.append("\n   0. Volver al Menú Principal");
        sb.append("\nIngrese una opción: ");
        return sb.toString();
    }


}





