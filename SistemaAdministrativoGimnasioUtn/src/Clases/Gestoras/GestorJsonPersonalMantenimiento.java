package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.PersonalMantenimiento;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase para gestionar el json del personal de mantenimiento y sus datos
 *
 *  @version 1
 */

public class GestorJsonPersonalMantenimiento  {
    private static String nombreArchivo;

    //tiene los horarios disponibles
    private static ArrayList<String> horarios;

    public GestorJsonPersonalMantenimiento() {
        nombreArchivo = "personalMantenimiento.json";
        horarios = new ArrayList<>();
        cargarHorarios();
    }

    /**
     * Metodo para cargar Horarios
     */
    public void cargarHorarios() {
        horarios.add("Lunes a viernes: 7-13");
        horarios.add("Lunes a viernes: 13-19");
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     *
     * @param personalM;
     * @return String;
     */
    public String grabar(GestionGenericaGimnasio<PersonalMantenimiento> personalM) {
            OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, personalMToJsonObject(personalM));
            return "Se ha escrito el archivo correctamente ";
    }


    /**
     * Este metodo sirve para meter una lista dentro de un JsonObject
     *
     * @param personalM;
     * @return JSONObject;
     */
    public JSONObject personalMToJsonObject(GestionGenericaGimnasio<PersonalMantenimiento> personalM) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaPersonalM", TojsonArray(personalM));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Metodo que pasa de un Object a un JsonArray
     *
     * @param personalM;
     * @return JSONArray;
     */
    public JSONArray TojsonArray(GestionGenericaGimnasio<PersonalMantenimiento> personalM) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (PersonalMantenimiento personalMantenimiento : personalM.getGestionUsuario().values()) {
                jsonArray.put(personalMantenimiento.toJSON());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura donde se le pasa por parametro el nombre del archivo
     * y retorna un JsonTokener que se convertira en una lista
     *
     * @return una lista de Personal de Mantenimiento
     */
    public GestionGenericaGimnasio<PersonalMantenimiento> leerListaGenericaPersonalM() {
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<PersonalMantenimiento> lista = null;

        try {
            lista = JsonObjectToPersonalM(new JSONObject(jsonTokener));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     *
     * @param jsonObject;
     * @return una lista de Personal de Mantenimiento
     */
    public GestionGenericaGimnasio<PersonalMantenimiento> JsonObjectToPersonalM(JSONObject jsonObject) {
        GestionGenericaGimnasio<PersonalMantenimiento> personalM = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaPersonalM");

            for (int i = 0; i < jsonArray.length(); i++) {
                PersonalMantenimiento personalMantenimiento = new PersonalMantenimiento(jsonArray.getJSONObject(i));
                personalM.agregar(personalMantenimiento.getDocumento(), personalMantenimiento);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return personalM;
    }


    /**
     * Este metodo sirve para crear un empleado de mantenimiento
     * @return un empleado de mantenimiento
     */
    public PersonalMantenimiento crearEmpleadoMantenimiento() {
        PersonalMantenimiento empleadoM = new PersonalMantenimiento();

        Scanner entrada = new Scanner(System.in);

        String nombre = Validaciones.validarCadena("Ingrese el nombre del empleado de mantenimiento:", entrada);
        empleadoM.setNombre(nombre);

        String apellido = Validaciones.validarCadena("Ingrese el apellido del empleado de mantenimiento:", entrada);
        empleadoM.setApellido(apellido);

        String documento = Validaciones.validarDocumento("Ingrese el documento del empleado de mantenimiento (8 dígitos):", entrada);
        empleadoM.setDocumento(documento);

        LocalDate fechaNacimiento = Validaciones.validarFecha("Ingrese la fecha de nacimiento (YYYY-MM-DD):", entrada);
        empleadoM.setFechaNacimiento(fechaNacimiento);

        empleadoM.setSalario(3000);

        int opc = elegirHorario(entrada);
        empleadoM.setHorario(horarios.get(opc));

        return empleadoM;
    }

    /**
     * Este metodo sirve para elegir un horario para el empleado de mantenimiento
     * @param scanner;
     * @return la opcion elegida;
     */
    public int elegirHorario (Scanner scanner) {
        System.out.println("Seleccione un horario:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ". " + horarios.get(i));
        }

        int opcion = -1;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("Ingrese el número de la opción deseada: ");
                opcion = Integer.parseInt(scanner.nextLine());

                if (opcion >= 1 && opcion <= horarios.size()) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y " + horarios.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }

        return opcion - 1; // Devuelve el índice del horario seleccionado.
    }

    /**
     * Este metodo sirve para modificar un entrenador.
     * @param empleadoM a modificar;
     */
    public void modificarEmpladoM(PersonalMantenimiento empleadoM) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Horario");
            System.out.println("4. Salario");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Modificar nombre
                    String nombre = Validaciones.validarCadena("Ingrese el nuevo nombre del empleado de mantenimiento:", scanner);
                    empleadoM.setNombre(nombre);
                    break;

                case 2:
                    // Modificar apellido
                    String apellido = Validaciones.validarCadena("Ingrese el nuevo apellido del empleado de mantenimiento:", scanner);
                    empleadoM.setApellido(apellido);
                    break;

                case 3:
                    // Modificar horario
                    System.out.println("Seleccione un horario:");
                    int horarioSeleccionado = elegirHorario(scanner);
                    empleadoM.setHorario(horarios.get(horarioSeleccionado));
                    break;

                case 4:
                    // Modificar salario
                    int salario = Validaciones.validarEntero("Ingrese el salario:", scanner);
                    empleadoM.setSalario(salario);
                    break;

                case 5:
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
