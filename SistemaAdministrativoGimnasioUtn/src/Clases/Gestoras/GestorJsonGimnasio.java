package Clases.Gestoras;

import Clases.Gimnasio;
import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Scanner;

/**
 * Esta clase sirve para gestionar el json del gimnasio y sus datos
 */
public class GestorJsonGimnasio {
    private static String nombreArchivo;

    public GestorJsonGimnasio() {
        nombreArchivo = "gimnasio.json";
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter el gimnasio pasado por parametro en el Archivo.
     * @param gimnasio;
     * @return String;
     */
    public String grabar(Gimnasio gimnasio) {
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, gimnasio.toJSON());
        return "Se ha escrito el archivo correctamente ";
    }


    /**
     * Este metodo sirve para leer un gimnasio
     * @return Gimnasio;
     */
    public Gimnasio leer() {
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        Gimnasio gimnasio = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonTokener);
            gimnasio = new Gimnasio(jsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gimnasio;

    }

    /**
     * Este metodo sirve para modificar los datos del gimnasio.
     * @param gimnasio;
     */
    public void modificarDatos(Gimnasio gimnasio) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Direccion");
            System.out.println("3. Capacidad");
            System.out.println("4. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Modificar nombre
                    String nombre = Validaciones.validarCadena("Ingrese el nuevo nombre del gimnasio", scanner);
                    gimnasio.setNombreGimnasio(nombre);
                    break;

                case 2:
                    // Modificar apellido
                    String direccion = Validaciones.validarCadena("Ingrese la nueva direccion ", scanner);
                    gimnasio.setDireccionGimnasio(direccion);
                    break;

                case 3:
                    // Modificar horario
                    int capacidad = Validaciones.validarEntero("Ingrese la nueva capacidad", scanner);
                    gimnasio.setCapacidadGimnasio(capacidad);
                    break;

                case 4:
                    // Salir
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }


    }

}
