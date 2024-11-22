package Clases.Menus;

import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonMaquinas;
import Clases.Maquina;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.io.IOException;
import java.util.Scanner;

public class MenuMaquinas implements iMenu {

    private static GestorJsonMaquinas gestorMaq=new GestorJsonMaquinas();

    public MenuMaquinas() {
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

            GestionGenericaGimnasio<Maquina> maquinas = gestorMaq.leerListaGenericaMaquina();

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
