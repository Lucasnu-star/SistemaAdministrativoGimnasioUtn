package Clases.Gestoras;


import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Membresia;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.util.ArrayList;

/**
 * Clase Gestora de membresia: en esta clase la idea principal era que sea gestora del json, pero
 * le fui agregando metodos de gestios de datos: crear, modificar.
 * asi que o se deja como gestora general, o pasamos los otros metodos (no json) a recepcionista
 *
 * @version 1
 */

public class GestorJsonMembresias {
    private static String nombreArchivo;

    public GestorJsonMembresias() {
        nombreArchivo = "membresias.json";
    }

    public String grabar(ArrayList<Membresia> membresias){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, membresiasToJsonObject(membresias));
        return "Se ha escrito el archivo correctamente ";
    }

    // mete el jsonArray dentro de un jsonObject
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

    // convierte la lista en un jsonArray
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
     * Metodo para leer la lista de membresias
     * @return
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

    // convierte el jsonObject a lista
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

