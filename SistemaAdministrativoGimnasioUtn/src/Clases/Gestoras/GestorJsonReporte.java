package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Maquina;
import Clases.Reporte;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GestorJsonReporte {

    private static String nombreArchivo;

    public GestorJsonReporte() {
        nombreArchivo = "reporte.json";
    }


    public String grabar(GestionGenericaGimnasio<Reporte> lista){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, reporteToJsonObject(lista));
        return "Se ha escrito el archivo correctamente ";
    }

    /**
     * Este metodo sirve para meter un JsonArray dentro de un JsonObject
     * @param *reportes
     * @return
     */

    public JSONObject reporteToJsonObject(GestionGenericaGimnasio<Reporte> lista){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaReportes", TojsonArray(lista));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    /**
     * Metodo que pasa de un Object a un JsonArray
     * @param *maquinas
     * @return
     */

    public JSONArray TojsonArray(GestionGenericaGimnasio<Reporte> lista){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Reporte reporte: lista.gestionUsuario.values()){
                jsonArray.put(reporte.toJSON());
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
    public GestionGenericaGimnasio<Reporte> leerListaGenericaReporte(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<Reporte> lista = null;

        try {
            lista = JsonObjectToReporte(new JSONObject(jsonTokener));

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

    public GestionGenericaGimnasio<Reporte> JsonObjectToReporte(JSONObject jsonObject) {
        GestionGenericaGimnasio<Reporte> lista = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaReportes");

            for (int i = 0; i < jsonArray.length(); i++) {
                Reporte reporte = new Reporte(jsonArray.getJSONObject(i));
                lista.agregar(reporte.getDescripcion(), reporte);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }



}

