package Clases.Menus;

import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonMaquinas;
import Clases.Maquina;
import Clases.PersonalMantenimiento;
import Clases.Recepcionista;
import Enums.eTipoMaquina;
import Interfaces.iMenu;

import java.io.IOException;
import java.util.Scanner;

public class MenuMaquinas implements iMenu {

    private final GestorJsonMaquinas gestorMaq;

    private GestionGenericaGimnasio<Maquina> maquinas;

    public MenuMaquinas() {
        gestorMaq = new GestorJsonMaquinas();
        maquinas = new GestionGenericaGimnasio<>();
    }

    public GestorJsonMaquinas getGestorMaq() {
        return gestorMaq;
    }

    public GestionGenericaGimnasio<Maquina> getMaquinas() {
        return maquinas;
    }

    //Submenu maquinas
    @Override
    public void mostrarMenu() {

        Scanner scanner=new Scanner(System.in);

        int opcion;

        // por si se necesita un string

        String entrada;

        // por si se necesita una maquina
        Maquina maq = null;

        do {

            maquinas = gestorMaq.leerListaGenericaMaquina();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Mostrando Maquinas...");
                    // Mostrar las máquinas
                    Recepcionista.mostrarElementosLista(maquinas);

                    esperarTeclaParaContinuar();
                    break;
                case 2:
                    System.out.println("Eliminando Maquina...");
                    System.out.println("Ingrese el tipo de maquina que desea eliminar:");
                            entrada = scanner.nextLine();
                    Recepcionista.eliminarDeLista(maquinas,entrada);

                    esperarTeclaParaContinuar();
                    break;
                case 3:
                    System.out.println("Agregando Maquina");
                    maq= gestorMaq.crearMaquina();
                    Recepcionista.agregarDeLista(maquinas,maq.getId(),maq);
                    gestorMaq.grabar(maquinas);

                    esperarTeclaParaContinuar();
                    break;
                case 4:
                    System.out.println("creando reporte maquina");
                    Recepcionista.crearReporte();
                    gestorMaq.grabar(maquinas);
                    break;

                case 5:
                    System.out.println("Filtrar por nombre...");
                    System.out.println("Ingrese el nombre por el que desea filtrar...");
                    entrada=scanner.nextLine();
                    Recepcionista.maquinaFiltroPorNombre(maquinas,entrada);
                    break;
                case 6:
                    System.out.println("Filtrar por tipo de maquina...");
                    System.out.println("Ingrese la opcion deseada por la que desea filtrar...");

                    eTipoMaquina[] tipo = eTipoMaquina.values();
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
                    Recepcionista.maquinaFiltroPorTipo(maquinas, tipo[opcion - 1]);
                    break;
                case 7:
                    System.out.println("Filtrando segun su estado...");
                    System.out.println("Ingrese la opcion deseada por la que desea filtrar...");
                    System.out.println("1. Perfecto estado");
                    System.out.println("2. Fuera de servicio");
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
                    Recepcionista.maquinaFiltroPorEstado(maquinas,valor);
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
        sb.append("\nMenú Maquinas:");
        sb.append("\n   1. Mostrar maquinas ");
        sb.append("\n   2. Eliminar maquina ");
        sb.append("\n   3. Agregar maquina");
        sb.append("\n   4. Crear reporte maquina ");
        sb.append("\n   5. Filtrar por nombre");
        sb.append("\n   6. Filtrar por tipo de maquina");
        sb.append("\n   7. Filtrar por estado de maquina");
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
