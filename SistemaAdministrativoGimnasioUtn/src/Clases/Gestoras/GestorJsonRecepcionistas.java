package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Recepcionista;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase Gestora de recepcionista: gestiona los datos de recepcionistas para convertirlos en json
 *
 * @version 1
 */

public class GestorJsonRecepcionistas {

    private static String nombreArchivo;

    //tiene los horarios disponibles, habria que ver un mejor lugar para tenerlo y crealo
    private static ArrayList<String> horarios;

    public GestorJsonRecepcionistas() {
        nombreArchivo = "recepcionistas.json";
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

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @param recepcionistas
     * @return
     */

    //Probar hacerlo generico
    public String grabar(GestionGenericaGimnasio<Recepcionista> recepcionistas){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, RecepcionistasToJsonObject(recepcionistas));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo mete el JsonArray dentro de un JsonObject
     * @param recepcionistas
     * @return
     */

    //Probar hacerlo generico
    public JSONObject RecepcionistasToJsonObject(GestionGenericaGimnasio<Recepcionista> recepcionistas){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaRecepcionistas", TojsonArray(recepcionistas));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Convierte la lista en un JsonArray
     * @param recepcionistas
     * @return
     */
    //Probar hacerlo generico
    public JSONArray TojsonArray(GestionGenericaGimnasio<Recepcionista> recepcionistas){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Recepcionista recepcionista : recepcionistas.getGestionUsuario().values()){
                jsonArray.put(recepcionista.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo sirve para leer la lista de recepcionistas
     * @return
     */
    //Probar hacerlo generico
    public GestionGenericaGimnasio<Recepcionista> leerListaGenericaRecepcionistas(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<Recepcionista> recepcionistas = null;

        try {
            recepcionistas = JsonObjectToRecepcionistas(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return recepcionistas;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     * @param jsonObject
     * @return
     */
    //Probar hacerlo generico
    public GestionGenericaGimnasio<Recepcionista> JsonObjectToRecepcionistas(JSONObject jsonObject){
        GestionGenericaGimnasio<Recepcionista> recepcionistas = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaRecepcionistas");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                Recepcionista recepcionista = new Recepcionista(jsonArray.getJSONObject(i));
                recepcionistas.agregar(recepcionista.getDocumento(), recepcionista);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return recepcionistas;
    }


    //Ver para moverlo a recepcionista
    public Recepcionista crearRecepcionista() {
        Recepcionista recepcionista = new Recepcionista();

        Scanner entrada = new Scanner(System.in);

        String nombre = Validaciones.validarCadena("Ingrese el nombre del empleado de mantenimiento:", entrada);
        recepcionista.setNombre(nombre);

        String apellido = Validaciones.validarCadena("Ingrese el apellido del empleado de mantenimiento:", entrada);
        recepcionista.setApellido(apellido);

        String documento = Validaciones.validarDocumento("Ingrese el documento del empleado de mantenimiento (8 dígitos):", entrada);
        recepcionista.setDocumento(documento);

        LocalDate fechaNacimiento = Validaciones.validarFecha("Ingrese la fecha de nacimiento (YYYY-MM-DD):", entrada);
        recepcionista.setFechaNacimiento(fechaNacimiento);

        int salario = Validaciones.validarEntero("Ingrese el salario:", entrada);
        recepcionista.setSalario(salario);

        int opc = elegirHorario(entrada);
        recepcionista.setHorario(horarios.get(opc));

        return recepcionista;
    }

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


    //Ver para moverlo a recepcionista
    public void modificarRecepcionista(Recepcionista recepcionista) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Horario");
            System.out.println("4. Nombre de usuario");
            System.out.println("5. Contraseña");
            System.out.println("6. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Modificar nombre
                    String nombre = Validaciones.validarCadena("Ingrese el nuevo nombre del empleado de mantenimiento:", scanner);
                    recepcionista.setNombre(nombre);
                    break;

                case 2:
                    // Modificar apellido
                    String apellido = Validaciones.validarCadena("Ingrese el nuevo apellido del empleado de mantenimiento:", scanner);
                    recepcionista.setApellido(apellido);
                    break;

                case 3:
                    // Modificar horario
                    System.out.println("Seleccione un horario:");
                    int horarioSeleccionado = elegirHorario(scanner);
                    recepcionista.setHorario(horarios.get(horarioSeleccionado));
                    break;

                case 4:
                    // Modificar nombreUsuario
                    String nombreUsuario = Validaciones.noVacio("Ingrese el nuevo nombre de usuario", scanner);
                    recepcionista.setNombreUsuario(nombreUsuario);
                    break;
                case 5:
                    // Modificar contraseña
                    String contrasenia = Validaciones.noVacio("Ingrese la nueva contraseña", scanner);
                    recepcionista.setContrasenia(contrasenia);
                    break;

                case 6:
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
