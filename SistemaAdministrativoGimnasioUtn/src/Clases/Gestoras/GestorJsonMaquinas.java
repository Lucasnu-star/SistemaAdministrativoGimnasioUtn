package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Maquina;
import Clases.Miembro;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

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
