package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

/**
 * Esta clase representa un personal de mantenimiento, extiende de Empleado por lo tanto hereda sus atributos,
 * no contiene atributos propios y su unico trabajo es arreglar maquinas.
 *
 * @version 1
 */


public final class PersonalMantenimiento extends Empleado {

    //Constructor
    public PersonalMantenimiento(String nombre, String apellido, String documento, LocalDate fechaNacimiento, int salario, String horario) {
        super(nombre, apellido, documento, fechaNacimiento, salario, horario);
    }

    public PersonalMantenimiento() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n Empleado de Mantenimiento = "+super.toString());
        return sb.toString();
    }

    //Metodos,  Ver metodo de esta clase
    public static void arreglarMaquina(Maquina maquina) {
        if (maquina != null) {
            System.out.println("Reparando la máquina: " + maquina.getNombre());
            maquina.marcarComoDisponible();
        } else {
            System.out.println("La máquina proporcionada no es válida.");
        }
    }

    /**
     * Convertir de un Archivo JSON a un objeto PersonalMantenimiento
     * @param jsonPersonalM;
     */
    public PersonalMantenimiento(JSONObject jsonPersonalM) {
        try{
            setNombre(jsonPersonalM.getString("nombre"));
            setApellido(jsonPersonalM.getString("apellido"));
            setDocumento(jsonPersonalM.getString("documento"));
            String fechaNacimiento = jsonPersonalM.getString("fechaNacimiento");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            setFechaNacimiento(fechaNac);
            setSalario(jsonPersonalM.getInt("salario"));
            setHorario(jsonPersonalM.getString("horario"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo para convertir de un objeto a un Archivo JSON
     * @return jsonObject;
     */
    public JSONObject toJSON(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();

            jsonObject.put("nombre", getNombre());
            jsonObject.put("apellido", getApellido());
            jsonObject.put("documento", getDocumento());
            jsonObject.put("fechaNacimiento", getFechaNacimiento());
            jsonObject.put("salario", getSalario());
            jsonObject.put("horario", getHorario());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}

