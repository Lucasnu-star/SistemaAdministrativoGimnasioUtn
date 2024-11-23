package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Membresia;
import Clases.Miembro;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import Enums.eTipoMembresia;

/**
 * Clase para gestionar el json de los miembros, pero puede convertirse en una clase gestora general
 *
 *  @version 1
 */

public class GestorJsonMiembros {
    private static String nombreArchivo;

    public GestorJsonMiembros() {
        nombreArchivo = "miembros.json";
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @param miembros;
     * @return String;
     */
    public String grabar(GestionGenericaGimnasio<Miembro> miembros){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, miembrosToJsonObject(miembros));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo sirve para meter un JsonArray dentro de un JsonObject
     * @param miembros;
     * @return JSONObject;
     */

    public JSONObject miembrosToJsonObject(GestionGenericaGimnasio<Miembro> miembros){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaMiembros", TojsonArray(miembros));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Metodo que pasa de un Object a un JsonArray
     * @param miembros;
     * @return JSONArray;
     */
    public JSONArray TojsonArray(GestionGenericaGimnasio<Miembro> miembros){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Miembro miembro : miembros.getGestionUsuario().values()){
                jsonArray.put(miembro.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @return una lista de miembros
     */
    public GestionGenericaGimnasio<Miembro> leerListaGenericaMiembros(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<Miembro> lista = null;

        try {
            lista = JsonObjectToMiembros(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     * @param jsonObject;
     * @return una lista de miembros
     */

    public GestionGenericaGimnasio<Miembro> JsonObjectToMiembros(JSONObject jsonObject){
        GestionGenericaGimnasio<Miembro> miembros = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaMiembros");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                Miembro miembro = new Miembro(jsonArray.getJSONObject(i));
                miembros.agregar(miembro.getDocumento(), miembro);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return miembros;
    }

    /**
     * Metodo para crear un miembro
     * @return Miembro;
     */
    //Ver para moverlo a recepcionista
    public Miembro crearMiembro() {
        Miembro miembro = new Miembro();
        Membresia membresia = new Membresia();  // Crear instancia de Membresia
        Scanner entrada = new Scanner(System.in);

        String nombre = Validaciones.validarCadena("Ingrese el nombre del miembro:", entrada);
        miembro.setNombre(nombre);

        String apellido = Validaciones.validarCadena("Ingrese el apellido del miembro:", entrada);
        miembro.setApellido(apellido);

        String documento = Validaciones.validarDocumento("Ingrese el documento del miembro (8 dígitos):", entrada);
        miembro.setDocumento(documento);

        LocalDate fechaNacimiento = Validaciones.validarFecha("Ingrese la fecha de nacimiento (YYYY-MM-DD):", entrada);
        miembro.setFechaNacimiento(fechaNacimiento);

        miembro.setFechaIncripcion(LocalDate.now());

        miembro.setFechaUltimoPago(LocalDate.now());

        GestorJsonMembresias gestorJsonMembresia = new GestorJsonMembresias();
        ArrayList<Membresia> membresias = gestorJsonMembresia.leerListaMembresia();

        int opc = elegirMembresia(entrada, membresias);
        miembro.setMembresia(membresias.get(opc));

        return miembro;
    }


    /**
     * Este metodo sirve para modificar un miembro
     * @param miembro;
     */
    //Ver para moverlo a recepcionista
    public void modificarMiembro(Miembro miembro) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione el dato que desea modificar:");
            System.out.println("   1. Nombre");
            System.out.println("   2. Apellido");
            System.out.println("   3. Membresía");
            System.out.println("   4. Salir");

            try {
                System.out.print("Ingrese la opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        // Modificar nombre
                        System.out.println("Nombre actual: " + miembro.getNombre());
                        String nombre = Validaciones.validarCadena("Ingrese el nuevo nombre", scanner);
                        miembro.setNombre(nombre);
                        break;

                    case 2:
                        // Modificar apellido
                        System.out.println("Apellido actual: " + miembro.getApellido());
                        String apellido = Validaciones.validarCadena("Ingrese el nuevo apellido", scanner);
                        miembro.setApellido(apellido);
                        break;

                    case 3:
                        // Modificar membresía
                        System.out.println("Membresía actual: " + miembro.getMembresia());

                        GestorJsonMembresias gestorJsonMembresia = new GestorJsonMembresias();
                        ArrayList<Membresia> membresias = gestorJsonMembresia.leerListaMembresia();

                        int opc = elegirMembresia(scanner, membresias);
                        miembro.setMembresia(membresias.get(opc));

                        break;

                    case 4:
                        // Salir
                        salir = true;
                        System.out.println("Modificación finalizada.");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }

    public int elegirMembresia(Scanner entrada, ArrayList<Membresia> membresias) {
        System.out.println("Seleccione una membresía:");
        for (int i = 0; i < membresias.size(); i++) {
            System.out.println((i + 1) + ". " + membresias.get(i));
        }

        int opcion = -1;
        boolean opcionValida = false;

        // Bucle para pedir la opción hasta que sea válida
        do {
            try {
                System.out.print("Ingrese el número de la opción deseada: ");
                opcion = Integer.parseInt(entrada.nextLine());

                // Validar que la opción esté en el rango correcto
                if (opcion >= 1 && opcion <= membresias.size()) {
                    opcionValida = true;
                } else {
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y " + membresias.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        } while (!opcionValida);

        // Devuelve el índice de la membresía seleccionada (restando 1 para hacer coincidir el índice con la opción elegida)
        return opcion - 1;
    }


    /**
     * Este metodo sirve llamar al metodo que actualiza el estado de membresia de todos los
     * miembros y graba la lista actualizada
     */
    public void actualizarMembresiasMiembros(){
        GestionGenericaGimnasio<Miembro> listaMiembros = leerListaGenericaMiembros();
        actualizarMembresias(listaMiembros);
        grabar(listaMiembros);
    }

    /**
     * Este metodo sirve para actualizar el estado de membresia de todos los miembros
     * los comparara de acuerdo a la fecha del ultimo pago sumandole un mes con la fecha actual
     * @param miembros;
     */
    public void actualizarMembresias(GestionGenericaGimnasio<Miembro> miembros){
        Iterator<Miembro> iterator = miembros.getGestionUsuario().sequencedValues().iterator();

        while (iterator.hasNext()){
            Miembro miembro = iterator.next();
            LocalDate fechaParaPagar = miembro.getFechaUltimoPago().plusMonths(1);

            if (fechaParaPagar.compareTo(LocalDate.now()) > 0){
                miembro.setEstadoMembresia(true);
            }else{
                miembro.setEstadoMembresia(false);
            }
        }
    }


}

