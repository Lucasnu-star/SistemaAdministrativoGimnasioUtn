package Clases.Gestoras;


import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Membresia;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.util.ArrayList;

/**
 * Clase Gestora de membresia: gestiona el json de las membresias y sus datos
 *
 * @version 1
 */

public class GestorJsonMembresias {
    private static String nombreArchivo;

    public GestorJsonMembresias() {
        nombreArchivo = "membresias.json";
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @param membresias;
     * @return String;
     */
    public String grabar(ArrayList<Membresia> membresias){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, membresiasToJsonObject(membresias));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo sirve para meter una lista dentro de un JsonObject
     * @param membresias;
     * @return JSONObject;
     */
    public JSONObject membresiasToJsonObject(ArrayList<Membresia> membresias){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaMembresias", TojsonArray(membresias));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Metodo que pasa de un Object a un JsonArray
     * @param membresias;
     * @return JSONArray;
     */
    public JSONArray TojsonArray(ArrayList<Membresia> membresias){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Membresia membresia : membresias){
                jsonArray.put(membresia.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura donde se le pasa por parametro el nombre del archivo
     * y retorna un JsonTokener que luego se convertira en una lista
     * @return una lista de membresias
     */
    public ArrayList<Membresia> leerListaMembresia(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        ArrayList<Membresia> lista = null;

        try {
            lista = JsonObjectToMembresia(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     * @param jsonObject;
     * @return una lista de membresias
     */
    public ArrayList<Membresia> JsonObjectToMembresia(JSONObject jsonObject){
        ArrayList<Membresia> membresias = new ArrayList<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaMembresias");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                Membresia membresia = new Membresia(jsonArray.getJSONObject(i));
                membresias.add(membresia);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return membresias;
    }
}

