package Clases.Gestoras;

import Clases.Entrenador;
import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Maquina;
import Clases.Miembro;
import Enums.eEspecialidad;
import Enums.eTipoMaquina;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Scanner;

/**
 * Clase para gestionar el json de las maquinas, pero puede convertirse en una clase gestora general
 *
 *  @version 1
 */

public class GestorJsonMaquinas {
    private static String nombreArchivo;

    public GestorJsonMaquinas() {
        nombreArchivo = "maquinas.json";
    }

    public Maquina crearMaquina() {
        Maquina maquina = new Maquina();
        Scanner entrada = new Scanner(System.in);
        try {
            System.out.println("Ingrese el nombre de la maquina:");
            maquina.setNombre(entrada.nextLine());
            System.out.println("Ingrese id de la maquina:");
            maquina.setId(entrada.nextLine());
            System.out.println("Seleccione tipo de maquina:");
            eTipoMaquina tipo = seleccionarTipo();
            maquina.setTipoMaquina(tipo);
            System.out.println("Seleccione estado de maquina");
            System.out.println("1. Funcional");
            System.out.println("2. Fuera de servicio");

            boolean opcionValida = false;

            while (!opcionValida) {
                int opcion =entrada.nextInt();
                if (opcion == 1) {
                    maquina.setEstadoMaquina(true);
                    opcionValida = true;
                } else if (opcion == 2) {
                    maquina.setEstadoMaquina(false);
                    opcionValida = true;
                } else {
                    System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y 2");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return maquina;

    }
    public eTipoMaquina seleccionarTipo(){
        Scanner entrada = new Scanner(System.in);
        //Mostrar tipos de maquinas disponibles
    eTipoMaquina[] tipo = eTipoMaquina.values();
            for (int i = 0; i < tipo.length; i++) {
        System.out.println((i + 1) + ". " +tipo[i].name());
    }
    int opcion = -1;
    boolean opcionValida = false;

    // Validar la entrada del usuario
            while (!opcionValida) {
        try {
            System.out.print("Ingrese el número de la opción deseada: ");
            opcion = Integer.parseInt(entrada.nextLine());

            if (opcion >= 1 && opcion <= tipo.length) {
                opcionValida = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione un número entre 1 y " + tipo.length + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida. Por favor, ingrese un número.");
        }
    }
            return tipo[opcion - 1];
}

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @param maquinas
     * @return
     */
    public String grabar(GestionGenericaGimnasio<Maquina> maquinas){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, maquinasToJsonObject(maquinas));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo sirve para meter un JsonArray dentro de un JsonObject
     * @param maquinas
     * @return
     */

    public JSONObject maquinasToJsonObject(GestionGenericaGimnasio<Maquina> maquinas){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaMaquinas", TojsonArray(maquinas));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Metodo que pasa de un Object a un JsonArray
     * @param maquinas
     * @return
     */

    public JSONArray TojsonArray(GestionGenericaGimnasio<Maquina> maquinas){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Maquina maquina : maquinas.gestionUsuario.values()){
                jsonArray.put(maquina.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @return una lista de maquinas
     */
    public GestionGenericaGimnasio<Maquina> leerListaGenericaMaquina(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<Maquina> lista = null;

        try {
            lista = JsonObjectToMaquina(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     * @param jsonObject
     * @return una lista de maquinas
     */

    public GestionGenericaGimnasio<Maquina> JsonObjectToMaquina(JSONObject jsonObject){
        GestionGenericaGimnasio<Maquina> maquinas = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaMaquinas");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                Maquina maquina = new Maquina(jsonArray.getJSONObject(i));
                maquinas.agregar(maquina.getNombre(), maquina);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
        return maquinas;
    }

}
