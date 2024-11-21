package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.PersonalMantenimiento;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase para gestionar el json del personal de mantenimiento, pero puede convertirse en una clase gestora general
 *
 *  @version 1
 */

public class GestorJsonPersonalMantenimiento  {
    private static String nombreArchivo;

    //tiene los horarios disponibles, habria que ver un mejor lugar para tenerlo y crealo
    private static ArrayList<String> horarios;

    public GestorJsonPersonalMantenimiento() {
        nombreArchivo = "personalMantenimiento.json";
        horarios = new ArrayList<>();
        cargarHorarios();

    }

    /**
     * Metodo para cargar Horarios
     */
    //Ver donde puede ir, poner en data opcion
    public void cargarHorarios() {
        horarios.add("Lunes a viernes: 7-13");
        horarios.add("Lunes a viernes: 13-19");
    }


    public String grabar(GestionGenericaGimnasio<PersonalMantenimiento> personalM) {
            OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, personalMToJsonObject(personalM));
            return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     *
     * @param personalM
     * @return
     */



    /**
     * Este metodo sirve para meter un JsonArray dentro de un JsonObject
     *
     * @param personalM
     * @return
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
     * @param personalM
     * @return
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
     * y una lista, para meter la lista pasada por parametro en el Archivo.
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
     * @param jsonObject
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


    //Ver para moverlo a recepcionista
    public PersonalMantenimiento crearEmpleadoMantenimiento() {
        PersonalMantenimiento empleado = new PersonalMantenimiento();

        Scanner entrada = new Scanner(System.in);
        try {
            // Solicitar datos del miembro
            System.out.println("Ingrese el nombre del miembro:");
            empleado.setNombre(entrada.nextLine());
            System.out.println("Ingrese apellido del miembro:");
            empleado.setApellido(entrada.nextLine());
            System.out.println("Ingrese documento del miembro:");
            empleado.setDocumento(entrada.nextLine());
            System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD):");
            empleado.setFechaNacimiento(LocalDate.parse(entrada.nextLine()));

            System.out.println("Ingrese el salario");
            empleado.setSalario(entrada.nextInt());
            entrada.nextLine();
            System.out.println("ingrese el horario");
            int opc = elegirHorario();
            empleado.setHorario(horarios.get(opc));

        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return empleado;
    }

    public int elegirHorario () {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Seleccione un horario:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ". " + horarios.get(i));
        }

        int opcion = -1;
        boolean opcionValida = false;

        while (!opcionValida) {
            try {
                System.out.print("Ingrese el número de la opción deseada: ");
                opcion = Integer.parseInt(entrada.nextLine());

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
     * @param empleadoM
     */
    //Ver para moverlo a recepcionista
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
                    System.out.println("Ingrese el nuevo nombre:");
                    empleadoM.setNombre(scanner.nextLine());
                    break;

                case 2:
                    // Modificar apellido
                    System.out.println("Ingrese el nuevo apellido:");
                    empleadoM.setApellido(scanner.nextLine());
                    break;

                case 3:
                    // Modificar horario
                    System.out.println("Seleccione un horario:");
                    int horarioSeleccionado = elegirHorario();
                    empleadoM.setHorario(horarios.get(horarioSeleccionado));
                    break;

                case 4:
                    // Modificar salario
                    System.out.println("Ingrese el nuevo salario:");
                    empleadoM.setSalario(scanner.nextInt());
                    scanner.nextLine();
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
