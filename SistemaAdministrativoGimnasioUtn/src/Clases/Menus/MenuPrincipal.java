package Clases.Menus;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonRecepcionistas;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.io.IOException;
import java.util.Scanner;
/**
 * Clase para acceder al menu principal
 * te presenta las distintas secciones
 *
 *  @version 1
 */
public class MenuPrincipal implements iMenu{
    private Recepcionista recepcionista;

    private final GestorJsonRecepcionistas gestorJson;

    private GestionGenericaGimnasio<Recepcionista> lista;

    public MenuPrincipal() {
        this.recepcionista = new Recepcionista();
        this.gestorJson = new GestorJsonRecepcionistas();
        this.lista = new GestionGenericaGimnasio<>();

    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    /**
     * Esta metodo lee el archivo de recepcionistas, pide los datos para iniciar sesion,
     * verifica si existen, y abre el menu si los datos ingresados son correctos
     */
    public void IniciadoDeSesion() {
        lista = gestorJson.leerListaGenericaRecepcionistas();
        Scanner scanner = new Scanner(System.in);

        boolean iniciadoExitoso = false;

        do {
            System.out.println("Iniciar Sesion");
            System.out.println("Nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.println("Contraseña: ");
            String contrasenia = scanner.nextLine();

            limpiarConsola();

            for (Recepcionista r : lista.getGestionUsuario().values()) {
                if (r.getNombreUsuario().equals(nombreUsuario) &&
                        r.getContrasenia().equals(contrasenia)) {
                        recepcionista = r;
                    iniciadoExitoso = true;
                    mostrarMenu();
                    break;
                }
            }

            if (!iniciadoExitoso) {
                System.out.println("Error: el usuario o contraseña son incorrectos");
            }

        } while (!iniciadoExitoso);
    }

    @Override
    public void mostrarMenu() {
        // unico scanner
        Scanner scanner = new Scanner(System.in);

        int opcion;
        System.out.println("Iniciaste sesión...");

        do {
            lista = gestorJson.leerListaGenericaRecepcionistas();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            opcion = scanner.nextInt();

            limpiarConsola();

            switch (opcion) {
                case 1:
                    // Llama al menu entrenadores
                    MenuEntrenadores entr = new MenuEntrenadores();
                    entr.mostrarMenu();
                    limpiarConsola();
                    break;
                case 2:
                    // Llamar miembros
                    MenuMiembros miem = new MenuMiembros();
                    miem.mostrarMenu();
                    limpiarConsola();
                    break;
                case 3:
                    // Llamar personal de mantenimiento
                    MenuPersonalMantenimiento pers = new MenuPersonalMantenimiento();
                    pers.mostrarMenu();
                    limpiarConsola();
                    break;
                case 4:
                    //Carga los datos del gimnasio desde el archivo y los muestra
                    MenuMaquinas maq = new MenuMaquinas();
                    maq.mostrarMenu();
                    limpiarConsola();
                    break;

                case 5:
                    System.out.println("Perfil: \n"+recepcionista);
                    break;
                case 6:
                    System.out.println("Modificar perfil ");
                    gestorJson.modificarRecepcionista(recepcionista);

                    gestorJson.grabar(lista);
                    break;
                case 7:
                    System.out.println("Datos del gimnasio ");
                    break;
                case 8:
                    System.out.println("Modificar datos del gimnasio ");
                    break;
                case 9:
                    System.out.println("Agregar recepcionista ");
                    Recepcionista recepcionistaNuevo = gestorJson.crearRecepcionista();
                    Recepcionista.agregarDeLista(lista, recepcionistaNuevo.getDocumento(), recepcionistaNuevo);

                    break;
                case 0:
                    System.out.println("¡Nos vemos! cerrando programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
                    esperarTeclaParaContinuar();
            }
        } while (opcion != 0); // Se cierra el programa cuando se ingrese 0
    }

    @Override
    public String mostrarInterfaz() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMenú Principal: ");
        sb.append(" \n   1. Entrenadores ");
        sb.append(" \n   2. Miembros ");
        sb.append(" \n   3. Personal de mantenimiento ");
        sb.append(" \n   4. Maquinas");
        sb.append(" \n   5. Ver perfil ");
        sb.append(" \n   6. Modificar perfil ");
        sb.append(" \n   7. Ver datos del gimnasio ");
        sb.append(" \n   8. Modificar datos del gimnasio ");
        sb.append(" \n   9. Agregar recepcionista ");
        sb.append(" \n   0. Salir del programa ");
        sb.append(" \n ");
        sb.append("\nIngrese una opcion ");
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

