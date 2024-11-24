package Clases;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase Empleado, esta clase representa un empleado que extiende de Usuario, al extender hereda sus atributos, ademas esta clase tiene dos atributos propios
 * salario (int) y horario (String)
 * No contiene metodos
 *
 * @version 1
 */
public abstract class Empleado  extends Usuario{

    //Atributos
    private int salario;
    private String horario; // Ver como plasmar horarios

    //Constuctor
    public Empleado(String nombre, String apellido, String documento, LocalDate fechaNacimiento, int salario, String horario) {
        super(nombre, apellido, documento, fechaNacimiento);
        this.salario = salario;
        this.horario = horario;
    }
    public Empleado() {
    }


    //Getters
    public int getSalario() {
        return salario;
    }
    public String getHorario() {
        return horario;
    }

    //Setters

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    //Equals && HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Empleado empleado = (Empleado) o;
        return salario == empleado.salario && Objects.equals(horario, empleado.horario);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salario, horario);
    }

    //ToString
    @Override
    public String toString() {
        return super.toString() +
                "\nsalario=" + salario +
                "\nhorario=" + horario ;
    }
    // Convertir Empleado a JSONObject (incluye atributos de Persona)
    @Override

    public JSONObject toJson() {
        JSONObject json = super.toJson(); // Llama a la implementación de Persona
        json.put("horario", horario);
        json.put("salario", salario);
        return json;
    }

    // Convertir JSONObject a Empleado
    public static Empleado fromJson(JSONObject json) {
        // Usa super para manejar los atributos de Persona
        Usuario persona = Usuario.fromJson(json);
        String horario = json.getString("horario");
        int salarioo = json.getInt("salario");
        Empleado empleado = null;
        empleado.setNombre(persona.getNombre());
        empleado.setApellido(persona.getApellido());
        empleado.setDocumento(persona.getDocumento());
        empleado.setFechaNacimiento(persona.getFechaNacimiento());
        empleado.setHorario(horario);
        empleado.setSalario(salarioo);
        // Crea y retorna un Empleado con los datos
        return empleado;
    }
}

