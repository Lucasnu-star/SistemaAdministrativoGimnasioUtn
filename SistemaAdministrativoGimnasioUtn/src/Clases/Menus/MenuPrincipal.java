package Clases.Menus;
import Clases.Gestoras.GestionGenericaGimnasio;
import Clases.Gestoras.GestorJsonGimnasio;
import Clases.Gestoras.GestorJsonRecepcionistas;
import Clases.Gestoras.Validaciones;
import Clases.Gimnasio;
import Clases.Recepcionista;
import Interfaces.iMenu;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Clase para acceder al menu principal.
 * Te presenta las distintas secciones.
 * Tiene sus gestores y listas para gestionar los datos que necesita.
 *
 *  @version 1
 */
public class MenuPrincipal implements iMenu{

    private Recepcionista recepcionista;

    private final GestorJsonRecepcionistas gestorJson;

    private GestionGenericaGimnasio<Recepcionista> listaRecepcionistas;

    private final GestorJsonGimnasio gestorGimnasio;

    private Gimnasio gimnasio;


    public MenuPrincipal() {
        this.recepcionista = new Recepcionista();
        this.gestorJson = new GestorJsonRecepcionistas();
        this.listaRecepcionistas = new GestionGenericaGimnasio<>();
        this.gestorGimnasio = new GestorJsonGimnasio();
        this.gimnasio = new Gimnasio();
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    /**
     * Esta metodo lee el archivo de recepcionistas, pide los datos para iniciar sesion,
     * verifica si existen, y abre el menu si los datos ingresados son correctos
     */
    public void IniciadoDeSesion() {
        // lee la lista recepcionistas
        listaRecepcionistas = gestorJson.leerListaGenericaRecepcionistas();
        Scanner scanner = new Scanner(System.in);

        boolean iniciadoExitoso = false;

        do {
            System.out.println("===== Iniciar Sesion =====");
            System.out.println("Nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.println("Contraseña: ");
            String contrasenia = scanner.nextLine();

            Validaciones.limpiarConsola();

            for (Recepcionista r : listaRecepcionistas.getGestionUsuario().values()) {
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

        int opcion = -1;
        System.out.println("Iniciaste sesión...");

        do {
            listaRecepcionistas = gestorJson.leerListaGenericaRecepcionistas();
            gimnasio = gestorGimnasio.leer();

            // Muestra las opciones
            System.out.println(mostrarInterfaz());

            try {
                opcion = scanner.nextInt(); // Podría lanzar InputMismatchException
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpia el buffer del Scanner
                continue; // Vuelve a mostrar el menú sin avanzar
            }

            Validaciones.limpiarConsola();

            switch (opcion) {
                case 1:
                    // Llama al menu entrenadores
                    MenuEntrenadores entr = new MenuEntrenadores();
                    entr.mostrarMenu();
                    Validaciones.limpiarConsola();

                    break;
                case 2:
                    // Llamar miembros
                    MenuMiembros miem = new MenuMiembros();
                    miem.mostrarMenu();
                    Validaciones.limpiarConsola();

                    break;
                case 3:
                    // Llamar personal de mantenimiento
                    MenuPersonalMantenimiento pers = new MenuPersonalMantenimiento();
                    pers.mostrarMenu();
                    Validaciones.limpiarConsola();

                    break;
                case 4:
                    //Carga los datos del gimnasio desde el archivo y los muestra
                    MenuMaquinas maq = new MenuMaquinas();
                    maq.mostrarMenu();
                    Validaciones.limpiarConsola();

                    break;

                case 5:
                    System.out.println("Perfil: \n"+recepcionista);
                    Validaciones.esperarTeclaParaContinuar();
                    Validaciones.limpiarConsola();

                    break;
                case 6:
                    System.out.println("Modificar perfil ");
                    gestorJson.modificarRecepcionista(recepcionista);

                    gestorJson.grabar(listaRecepcionistas);
                    Validaciones.limpiarConsola();

                    break;
                case 7:
                    System.out.println("Datos del gimnasio ");
                    System.out.println(gimnasio);
                    Validaciones.esperarTeclaParaContinuar();
                    Validaciones.limpiarConsola();

                    break;
                case 8:
                    System.out.println("Modificar datos del gimnasio ");
                    gestorGimnasio.modificarDatos(gimnasio);

                    gestorGimnasio.grabar(gimnasio);
                    Validaciones.limpiarConsola();

                    break;
                case 9:
                    System.out.println("Agregar recepcionista ");
                    Recepcionista recepcionistaNuevo = gestorJson.crearRecepcionista();
                    Recepcionista.agregarDeLista(listaRecepcionistas, recepcionistaNuevo.getDocumento(), recepcionistaNuevo);

                    gestorJson.grabar(listaRecepcionistas);
                    Validaciones.limpiarConsola();

                    break;
                case 0:
                    System.out.println("¡Nos vemos! cerrando programa...");
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
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


}

