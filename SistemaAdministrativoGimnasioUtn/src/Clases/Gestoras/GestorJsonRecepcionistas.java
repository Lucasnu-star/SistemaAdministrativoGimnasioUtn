package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Recepcionista;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Clase Gestora de recepcionista: gestiona los datos de recepcionistas para convertirlos en json
 *
 * @version 1
 */

public class GestorJsonRecepcionistas {

    private static String nombreArchivo;

    public GestorJsonRecepcionistas() {
        nombreArchivo = "recepcionistas.json";
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
}
