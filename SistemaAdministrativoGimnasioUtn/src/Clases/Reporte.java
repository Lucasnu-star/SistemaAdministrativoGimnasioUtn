package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Esta clase representa un Reporte con una descripcion(String) y un idMaquina(String), donde se va a guardar un reporte de la maquina.
 *
 * @version 1
 */
public class Reporte {
    private String descripcion;
    private String idMaquina;

    // Constructor
    public Reporte(String descripcion, String idMaquina) {
        this.descripcion = descripcion;
        this.idMaquina = idMaquina;
    }

    public Reporte() {
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reporte reporte = (Reporte) o;
        return Objects.equals(descripcion, reporte.descripcion) && Objects.equals(idMaquina, reporte.idMaquina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, idMaquina);
    }

    @Override
    public String toString() {
        return "Reporte = " +
                "descripcion='" + descripcion + '\'' +
                ", idMaquina='" + idMaquina + '\'';
    }

    /**
     * Convertir de un Archivo JSON a un objeto Recepcionista
     *
     * @param jsonObject;
     */
    public Reporte(JSONObject jsonObject) {
        try {
            setIdMaquina(jsonObject.getString("idm"));
            setDescripcion(jsonObject.getString("desc"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo para convertir de un objeto a un Archivo JSON
     *
     * @return jsonObject
     */
    public JSONObject toJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("idm", getIdMaquina());
            jsonObject.put("desc", getDescripcion());
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }


}
