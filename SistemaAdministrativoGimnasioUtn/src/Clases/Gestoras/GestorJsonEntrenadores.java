package Clases.Gestoras;

import Clases.Entrenador;
import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Enums.eEspecialidad;

/**
 * Clase Gestora de entrenadores: en esta clase la idea principal era que sea gestora del json, pero
 * le fui agregando metodos de gestios de datos: crear, modificar.
 * asi que o se deja como gestora general, o pasamos los otros metodos (no json) a recepcionista
 *
 * @version 1
 */

public class GestorJsonEntrenadores {
    // importante para cada gestor
    private static String nombreArchivo;

    //tiene los horarios disponibles, habria que ver un mejor lugar para tenerlo y crealo
    private static ArrayList<String> horarios;


    public GestorJsonEntrenadores() {
        nombreArchivo = "entrenadores.json";
        horarios = new ArrayList<>();
        cargarHorarios();
    }

    /**
     * Metodo para cargar Horarios
     */
    //Ver donde puede ir, poner en data opcion
    public void cargarHorarios(){
        horarios.add("Lunes a sabado: 9-17");
        horarios.add("Lunes a sabado: 10-18");
        horarios.add("Lunes a sabado: 8-16");
        horarios.add("Lunes a sabado: 7-15");
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @param entrenadores;
     * @return String;
     */

    //Probar hacerlo generico
    public String grabar(GestionGenericaGimnasio<Entrenador> entrenadores){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, entrenadoresToJsonObject(entrenadores));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo mete el JsonArray dentro de un JsonObject
     * @param entrenadores;
     * @return jsonObject;
     */

    //Probar hacerlo generico
    public JSONObject entrenadoresToJsonObject(GestionGenericaGimnasio<Entrenador> entrenadores){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaEntrenadores", TojsonArray(entrenadores));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Convierte la lista en un JsonArray
     * @param entrenadores;
     * @return JsonArray;
     */
    //Probar hacerlo generico
    public JSONArray TojsonArray(GestionGenericaGimnasio<Entrenador> entrenadores){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Entrenador entrenador : entrenadores.getGestionUsuario().values()){
                jsonArray.put(entrenador.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo sirve para leer la lista de entrenadores
     * @return GestionGenericaGimnasio<Entrenador>;
     */
    //Probar hacerlo generico
    public GestionGenericaGimnasio<Entrenador> leerListaGenericaEntrenadores(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<Entrenador> entrenadores = null;

        try {
            entrenadores = JsonObjectToEntrenadores(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return entrenadores;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     * @param jsonObject;
     * @return GestionGenericaGimnasio<Entrenador>;
     */
    //Probar hacerlo generico
    public GestionGenericaGimnasio<Entrenador> JsonObjectToEntrenadores(JSONObject jsonObject){
        GestionGenericaGimnasio<Entrenador> entrenadores = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaEntrenadores");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                Entrenador entrenador = new Entrenador(jsonArray.getJSONObject(i));
                entrenadores.agregar(entrenador.getDocumento(), entrenador);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return entrenadores;
    }


    /**
     * Este metodo sirve para crear un entrenador
     * @return un ojecto Entrenador
     */
    //Ver para moverlo a recepcionista
    public Entrenador crearEntrenador() {
        Entrenador entrenador = new Entrenador();
        Scanner entrada = new Scanner(System.in);

        String nombre = Validaciones.validarCadena("Ingrese el nombre del entrenador:", entrada);
        entrenador.setNombre(nombre);

        String apellido = Validaciones.validarCadena("Ingrese el apellido del entrenador:", entrada);
        entrenador.setApellido(apellido);

        String documento = Validaciones.validarDocumento("Ingrese el documento del entrenador (8 dígitos):", entrada);
        entrenador.setDocumento(documento);

        LocalDate fechaNacimiento = Validaciones.validarFecha("Ingrese la fecha de nacimiento (YYYY-MM-DD):", entrada);
        entrenador.setFechaNacimiento(fechaNacimiento);

        entrenador.setSalario(5000);

        int opc = elegirHorario(entrada);
        entrenador.setHorario(horarios.get(opc));

        System.out.println("Seleccione la especialidad:");
        entrenador.setEspecialidad(elegirEspecialidad(entrada));

        return entrenador;
    }



    /**
     * Este metodo sirve para mostrar y elegir un horario
     * @return la opcion elegida
     */
    public int elegirHorario(Scanner entrada) {

        System.out.println("Seleccione un horario:");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println((i + 1) + ". " + horarios.get(i));
        }

        int opcion = -1;
        boolean opcionValida = false;

        do{
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
        }while (!opcionValida);

        return opcion - 1; // Devuelve el índice del horario seleccionado.
    }


    /**
     * Este metodo sirve para mostrar y elegir una especialidad
     * @return una especialidad
     */
    // Intentar modularizarlo
    public eEspecialidad elegirEspecialidad(Scanner entrada) {

        // Mostrar las especialidades disponibles
        System.out.println("Seleccione una especialidad:");
        eEspecialidad[] especialidades = eEspecialidad.values();
        for (int i = 0; i < especialidades.length; i++) {
            System.out.println((i + 1) + ". " + especialidades[i].name());
        }

        int opcion = -1;
        boolean opcionValida = false;

        // Validar la entrada del usuario
        do{
            try {
                System.out.print("Ingrese el número de la opción deseada: ");
                opcion = Integer.parseInt(entrada.nextLine());

                if (opcion >= 1 && opcion <= especialidades.length) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y " + especialidades.length + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }while (!opcionValida);

        return especialidades[opcion - 1]; // Devuelve la especialidad seleccionada.
    }



    /**
     * Este metodo sirve para modificar un entrenador.
     * @param entrenador;
     */
    //Ver para moverlo a recepcionista
    public void modificarEntrenador(Entrenador entrenador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione el dato que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Horario");
            System.out.println("4. Salario");
            System.out.println("5. Especialidad");
            System.out.println("6. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Modificar nombre
                    String nombre = Validaciones.validarCadena("Ingrese el nuevo nombre ", scanner);
                    entrenador.setNombre(nombre);
                    break;

                case 2:
                    // Modificar apellido
                    String apellido = Validaciones.validarCadena("Ingrese el nuevo apellido ", scanner);
                    entrenador.setApellido(apellido);
                    break;

                case 3:
                    // Modificar horario
                    System.out.println("Seleccione un horario:");
                    int horarioSeleccionado = elegirHorario(scanner);
                    entrenador.setHorario(horarios.get(horarioSeleccionado));
                    break;

                case 4:
                    // Modificar salario
                    int salario = Validaciones.validarEntero("Ingrese el nuevo salario", scanner);
                    entrenador.setSalario(salario);
                    break;

                case 5:
                    // Modificar especialidad
                    eEspecialidad eEspecialidad = elegirEspecialidad(scanner);

                    // Establecer la especialidad seleccionada
                    entrenador.setEspecialidad(eEspecialidad);
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

