package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.PersonalMantenimiento;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Clase para gestionar el json del personal de mantenimiento, pero puede convertirse en una clase gestora general
 *
 *  @version 1
 */

public class GestorJsonPersonalMantenimiento {
    private static String nombreArchivo;

    public GestorJsonPersonalMantenimiento() {
        nombreArchivo = "personalMantenimiento.json";
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura.escribir donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @param personalM
     * @return
     */
    public String grabar(GestionGenericaGimnasio<PersonalMantenimiento> personalM){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, personalMToJsonObject(personalM));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo sirve para meter un JsonArray dentro de un JsonObject
     * @param personalM
     * @return
     */

    public JSONObject personalMToJsonObject(GestionGenericaGimnasio<PersonalMantenimiento> personalM){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaPersonalM", TojsonArray(personalM));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Metodo que pasa de un Object a un JsonArray
     * @param personalM
     * @return
     */
    public JSONArray TojsonArray(GestionGenericaGimnasio<PersonalMantenimiento> personalM){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (PersonalMantenimiento personalMantenimiento : personalM.getGestionUsuario().values()){
                jsonArray.put(personalMantenimiento.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    /**
     * Este metodo utiliza un metodo de la clase OperacionesLecturaEscritura donde se le pasa por parametro el nombre del archivo
     * y una lista, para meter la lista pasada por parametro en el Archivo.
     * @return una lista de Personal de Mantenimiento
     */
    public GestionGenericaGimnasio<PersonalMantenimiento> leerListaGenericaMiembros(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<PersonalMantenimiento> lista = null;

        try {
            lista = JsonObjectToPersonalM(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    /**
     * Este metodo convierte el JsonObject en una lista
     * @param jsonObject
     * @return una lista de Personal de Mantenimiento
     */

    public GestionGenericaGimnasio<PersonalMantenimiento> JsonObjectToPersonalM(JSONObject jsonObject){
        GestionGenericaGimnasio<PersonalMantenimiento> personalM = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaPersonalM");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                PersonalMantenimiento personalMantenimiento = new PersonalMantenimiento(jsonArray.getJSONObject(i));
                personalM.agregar(personalMantenimiento.getDocumento(), personalMantenimiento);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return personalM;
    }
}
