package Clases;
import Clases.Gestoras.GestionGenericaGimnasio;
import Enums.eEspecialidad;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * Esta clase representa una Especialidad, tiene una descripcion(String)  y una eEspecialidad especialidad(Enum)
 *
 * @version 1
 */
public final class Especialidad {

    // Atributos
    private String descripcion;
    private eEspecialidad especialidad;

    // Constructor
    public Especialidad(String descripcion, eEspecialidad especialidad) {
        this.descripcion = descripcion;
        this.especialidad = especialidad;
    }

    // Getters
    public String getDescripcion() {
        return descripcion;
    }

    public eEspecialidad getEspecialidad() {
        return especialidad;
    }

    // Setters
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEspecialidad(eEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    // Equals && HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Especialidad that = (Especialidad) o;
        return Objects.equals(descripcion, that.descripcion) && especialidad == that.especialidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, especialidad);
    }

    // ToString


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nEspecialidad ");
        sb.append(especialidad);
        sb.append(", descripcion = ").append(descripcion);
        return sb.toString();
    }

    /**
     * Este metodo es para mostrar un entrenador por su especialidad, se le pasa por parametro la especialidad requerida y (Ver porque se le pasa eso, esta bien?)
     * @param tipo
     * @param gestionGym
     */

    public void mostrarEntrenadorPorEspecialidad(Especialidad tipo, GestionGenericaGimnasio gestionGym) {
        System.out.println("Entrenadores de la especialidad: " + tipo);
        List<Entrenador> todosEntrenadores = gestionGym.consultarPorClase(Entrenador.class);

        for (Entrenador entrenador : todosEntrenadores) {
            if (entrenador.getEspecialidad().equals(tipo.getEspecialidad())) {
                System.out.println(entrenador);
            }
        }
    }


    public JSONObject toJSON (){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("especialidad", getEspecialidad());
            jsonObject.put("descripcion", getDescripcion());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }


    /**
     * Metodo que pasa este objeto de un Json a un objeto devuelta
     */
    //Ver si se sigue usando o se va a usar
    public Especialidad (JSONObject jsonObject){
        try {
            String especialidad = jsonObject.getString("especialidad");
            setEspecialidad(eEspecialidad.valueOf(especialidad));
            setDescripcion(jsonObject.getString("descripcion"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}


