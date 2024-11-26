package Clases;

import Clases.Gestoras.GestionGenericaGimnasio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase Gimnasio, esta clase es la que contiene todos los datos del gimnasio. Tiene atributos propios como el nombreGimnasio, direccionGimasio y la capacidad.
 * Ademas tiene las listas para la gestion del gimnasio y una lista de especialidades de ese gimnasio en especifico.
 *
 * @version 1
 */


public final class Gimnasio {

    //Atributos
    private String nombreGimnasio;
    private String direccionGimnasio;
    private int capacidadGimnasio;

    private GestionGenericaGimnasio<Entrenador> gestionEntrenadores;
    private GestionGenericaGimnasio<Miembro> gestionMiembros;
    private GestionGenericaGimnasio<PersonalMantenimiento> gestionPersonalMantenimiento;
    private GestionGenericaGimnasio<Recepcionista> gestionRecepcionistas;
    private GestionGenericaGimnasio<Maquina> gestionMaquinas;
    private List<Especialidad> especialidadesGimnasio;


    //Constructores
    public Gimnasio(String nombreGimnasio, int capacidadGimnasio, String direccionGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
        this.capacidadGimnasio = capacidadGimnasio;
        this.direccionGimnasio = direccionGimnasio;
        this.gestionEntrenadores = new GestionGenericaGimnasio<>();
        this.gestionMiembros = new GestionGenericaGimnasio<>();
        this.gestionPersonalMantenimiento = new GestionGenericaGimnasio<>();
        this.gestionRecepcionistas = new GestionGenericaGimnasio<>();
        this.gestionMaquinas = new GestionGenericaGimnasio<>();
        this.especialidadesGimnasio =  new ArrayList<>(); // Ver
    }

    public Gimnasio() {
        this.gestionEntrenadores = new GestionGenericaGimnasio<>();
        this.gestionMiembros = new GestionGenericaGimnasio<>();
        this.gestionPersonalMantenimiento = new GestionGenericaGimnasio<>();
        this.gestionRecepcionistas = new GestionGenericaGimnasio<>();
        this.gestionMaquinas = new GestionGenericaGimnasio<>();
        this.especialidadesGimnasio =  new ArrayList<>();
    }

    //Getters
    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    public String getDireccionGimnasio() {
        return direccionGimnasio;
    }

    public int getCapacidadGimnasio() {
        return capacidadGimnasio;
    }

    public GestionGenericaGimnasio<Entrenador> getGestionEntrenadores() {
        return gestionEntrenadores;
    }

    public GestionGenericaGimnasio<Miembro> getGestionMiembros() {
        return gestionMiembros;
    }

    public GestionGenericaGimnasio<PersonalMantenimiento> getGestionPersonalMantenimiento() {
        return gestionPersonalMantenimiento;
    }

    public GestionGenericaGimnasio<Recepcionista> getGestionRecepcionistas() {
        return gestionRecepcionistas;
    }

    public GestionGenericaGimnasio<Maquina> getGestionMaquinas() {
        return gestionMaquinas;
    }

    public List<Especialidad> getEspecialidadesGimnasio() {
        return especialidadesGimnasio;
    }

    //Setters
    public void setNombreGimnasio(String nombreGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
    }

    public void setDireccionGimnasio(String direccionGimnasio) {
        this.direccionGimnasio = direccionGimnasio;
    }

    public void setCapacidadGimnasio(int capacidadGimnasio) {
        this.capacidadGimnasio = capacidadGimnasio;
    }

    public void setGestionEntrenadores(GestionGenericaGimnasio<Entrenador> gestionEntrenadores) {
        this.gestionEntrenadores = gestionEntrenadores;
    }

    public void setGestionMiembros(GestionGenericaGimnasio<Miembro> gestionMiembros) {
        this.gestionMiembros = gestionMiembros;
    }

    public void setGestionRecepcionistas(GestionGenericaGimnasio<Recepcionista> gestionRecepcionistas) {
        this.gestionRecepcionistas = gestionRecepcionistas;
    }

    public void setGestionMaquinas(GestionGenericaGimnasio<Maquina> gestionMaquinas) {
        this.gestionMaquinas = gestionMaquinas;
    }

    public void setEspecialidadesGimnasio(List<Especialidad> especialidadesGimnasio) {
        this.especialidadesGimnasio = especialidadesGimnasio;
    }

    public void setGestionPersonalMantenimiento(GestionGenericaGimnasio<PersonalMantenimiento> gestionPersonalMantenimiento) {
        this.gestionPersonalMantenimiento = gestionPersonalMantenimiento;
    }

    //HashCode && Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gimnasio gimnasio = (Gimnasio) o;
        return capacidadGimnasio == gimnasio.capacidadGimnasio && Objects.equals(nombreGimnasio, gimnasio.nombreGimnasio) && Objects.equals(direccionGimnasio, gimnasio.direccionGimnasio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreGimnasio, direccionGimnasio, capacidadGimnasio);
    }

    //ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gimnasio ");
        sb.append("nombre gimnasio='").append(nombreGimnasio).append('\'');
        sb.append(", direccion gimnasio='").append(direccionGimnasio).append('\'');
        sb.append(", capacidad gimnasio=").append(capacidadGimnasio);
        sb.append("\nEspecialidadesGimnasio = ");
        for (Especialidad especialidad : especialidadesGimnasio){
            sb.append(especialidad);
        }
        return sb.toString();
    }

    public void agregarEspecialidad(Especialidad especialidad){
        especialidadesGimnasio.add(especialidad);
    }

    public void eliminarEspecialidad(Especialidad especialidad){
        especialidadesGimnasio.remove(especialidad);
    }

    /**
     * Metodo para convertir de un archivo Json en un objeto Gimnasio, usa el nombre, direccion, capacidad, y especialidades
     * @param jsonObjectGimnasio;
     */
    public Gimnasio (JSONObject jsonObjectGimnasio){
        try {
            setNombreGimnasio(jsonObjectGimnasio.getString("nombre"));
            setDireccionGimnasio(jsonObjectGimnasio.getString("direccion"));
            setCapacidadGimnasio(jsonObjectGimnasio.getInt("capacidad"));

            JSONArray jsonArray = jsonObjectGimnasio.getJSONArray("especialidades");

            especialidadesGimnasio = new ArrayList<>();

            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Especialidad especialidad = new Especialidad(jsonObject); // Constructor robusto que maneje excepciones
                        especialidadesGimnasio.add(especialidad);
                    } catch (JSONException e) {
                        System.err.println("Error al procesar un miembro asignado en el índice " + i + ": " + e.getMessage());
                    }
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo para convertir maquina en un archivo JSON, usa el nombre, direccion, capacidad, y especialidades
     * @return jsonObject;
     */
    public JSONObject toJSON(){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("nombre", getNombreGimnasio());
            jsonObject.put("direccion", getDireccionGimnasio());
            jsonObject.put("capacidad", getCapacidadGimnasio());

            JSONArray especialidadesJson = new JSONArray();
            for (Especialidad especialidad : especialidadesGimnasio){
                especialidadesJson.put(especialidad.toJSON());
            }

            jsonObject.put("especialidades", especialidadesJson);

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }



}

