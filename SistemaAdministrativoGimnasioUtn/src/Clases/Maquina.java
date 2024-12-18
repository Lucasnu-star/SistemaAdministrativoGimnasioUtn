package Clases;
import Enums.eTipoMaquina;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase Maquina, esta clase representa una maquina del gimnasio, tiene atributos propios como la id (String), nombre(String),  eTipoMaquina tipoMaquina(Enum), estadoMaquina(Boolean)
 *
 * @version 1
 */
public final class Maquina {

    //Atributos
    private String id;
    private String nombre;
    private eTipoMaquina tipoMaquina;
    private boolean estadoMaquina; // True o False

    private static int autoIncremental = 0;

    //Constructor
    public Maquina(String nombre, eTipoMaquina tipoMaquina, boolean estadoMaquina) {
        this.id = String.valueOf(++autoIncremental);
        this.nombre = nombre;
        this.tipoMaquina = tipoMaquina;
        this.estadoMaquina = estadoMaquina;
    }

    // Constructor para continuar el autoincremental despues de cerrarse el programa
    public Maquina(int size) {
        this.id = String.valueOf(++size);
    }

    public Maquina() {
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public eTipoMaquina getTipoMaquina() {
        return tipoMaquina;
    }

    public boolean isEstadoMaquina() {
        return estadoMaquina;
    }

    public String getId() {
        return id;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoMaquina(eTipoMaquina tipoMaquina) {
        this.tipoMaquina = tipoMaquina;
    }

    public void setEstadoMaquina(boolean estadoMaquina) {
        this.estadoMaquina = estadoMaquina;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Equals && HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maquina maquina = (Maquina) o;
        return estadoMaquina == maquina.estadoMaquina && Objects.equals(nombre, maquina.nombre) && Objects.equals(tipoMaquina, maquina.tipoMaquina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tipoMaquina, estadoMaquina);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Maquina = ");
        sb.append(" id='").append(id).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", tipoMaquina=").append(tipoMaquina);
        sb.append(", estadoMaquina=").append(estadoMaquina);
        return sb.toString();
    }

    public void marcarComoDisponible(){
        setEstadoMaquina(true);
    }

    /**
     * Metodo para convertir de un archivo Json en un objeto Maquina
     * @param jsonMaquina;
     */
    public Maquina (JSONObject jsonMaquina){
        try {
            setId(jsonMaquina.getString("id"));
            setNombre(jsonMaquina.getString("nombre"));
            String tipo = jsonMaquina.getString("tipoMaquina");
            setTipoMaquina(eTipoMaquina.valueOf(tipo));
            setEstadoMaquina(jsonMaquina.getBoolean("estadoMaquina"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo para convertir maquina en un archivo JSON
     * @return jsonObject;
     */
    public JSONObject toJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", getId());
            jsonObject.put("nombre", getNombre());
            jsonObject.put("tipoMaquina", getTipoMaquina());
            jsonObject.put("estadoMaquina", isEstadoMaquina());
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }
}

