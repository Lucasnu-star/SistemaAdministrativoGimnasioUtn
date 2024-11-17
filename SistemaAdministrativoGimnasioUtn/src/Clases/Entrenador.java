package Clases;
import Enums.eEspecialidad
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Clase Entrenador, esta clase representa un entrenador, extiende de Empleado por lo tanto hereda de sus atributos, ademas esta clase tiene dos listas y un atributo ENUM
 * Hashet<String> certificados (Una lista tipo HashSet donde se van a guardar los certificados, no permite elementos duplicados, permite elementos null, acceso rápido ).
 * List<Miembro> miembrosAsiganodos(Una lista tipo list que va a guardar los miembros que sean asigandos a un entrenador en especifico).
 * Enum eEspecialidad especialidad, la especialidad que va a ser asignada por medio del Enum.
 *
 * @version 1
 */


public final class Entrenador extends Empleado {

    //Atributos
    private eEspecialidad especialidad;
    private HashSet<String> certificados;
    private ArrayList<Miembro> miembrosAsignados;

    //Constuctor
    public Entrenador(String nombre, String apellido, String documento, LocalDate fechaNacimiento, int salario, String horario, Especialidad especialidad) {
        super(nombre, apellido, documento, fechaNacimiento, salario, horario);
        this.certificados = new HashSet<>(); // Ver
        this.especialidad = especialidad.getEspecialidad();
        this.miembrosAsignados = new ArrayList<>();
    }
    public Entrenador() {

    }


    //Getters

    public List<Miembro> getMiembrosAsignados() {
        return miembrosAsignados;
    }

    public eEspecialidad getEspecialidad() {
        return especialidad;
    }

    public HashSet<String> getCertificados() {
        return certificados;
    }

    //Setters

    public void setMiembrosAsignados(ArrayList<Miembro> miembrosAsignados) {
        this.miembrosAsignados = miembrosAsignados;
    }

    public void setEspecialidad(eEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setCertificados(HashSet<String> certificados) {
        this.certificados = certificados;
    }


    //Equals && HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(especialidad, that.especialidad) && Objects.equals(certificados, that.certificados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidad, certificados);
    }

    //ToString

    @Override
    public String toString() {
        return  "Entrenador: "+
                super.toString() +
                "especialidad=" + especialidad +
                ", certificados=" + certificados;
    }

    /**
     * Este metodo sirve para asignar un miembro a un entrenador en especifico, por paramtro va a recibir un miembrom, para poder añadirlo a la lista
     * @param miembro
     */
    public void asignarMiembro(Miembro miembro) {
        if (miembrosAsignados != null) {
            miembrosAsignados.add(miembro);
            System.out.println("Miembro " + miembro.getNombre() + " asignado al entrenador " + getNombre());
        }
    }

    /**
     * Este metodo sirve para consultar los miembros de un entrenador en especifico. Verifica si la lista de miembros del entrenador es null,
     * si es null lanza un mensaje que no hay miembros en ese entrenador, por otro lado si es distinto a null osea que hay miembros, muestra los miembrois mediante un for each.
     */
    public void consultarMiembros() {
        if (miembrosAsignados == null || miembrosAsignados.isEmpty()) {
            System.out.println("No hay miembros asignados a este entrenador.");
        } else {
            System.out.println("Lista de miembros asignados al entrenador " + getNombre() + ":");
            for (Miembro miembro : miembrosAsignados) {
                System.out.println("- Nombre: " + miembro.getNombre() + ", Estado Membresía: "
                        + miembro.isEstadoMembresia());
            }
        }
    }

    /**
     * Este metodo sirve para agregar un certiciado a un entrandor en especifico, por parameotr le pasamos un certificado (Strign).
     * @param certificado
     */
    public void agregarCertificado(String certificado) {
        if (certificado != null) {
            certificados.add(certificado);
            System.out.println("Certificado \"" + certificado + "\" agregado al entrenador " + getNombre());
        } else {
            System.out.println("El certificado no puede ser nulo o vacío.");
        }
    }

    /**
     * Este metodo sirve para eliminar un miembro de la lista especificada por entrenador, por parametro se le pasa un miembro y verifica por medio del constains si ese miembro
     * esta, si esta lo remueve, si no lanza un mensaje diciendo que el miembro no fue encontrado.
     * @param miembro
     */
    public void eliminarMiembro(Miembro miembro)
    {
        if (miembrosAsignados.contains(miembro))
        {
            miembrosAsignados.remove(miembro);
        }else
        {
            System.out.println("El miembro no fue encontrado en la lista de miembros del entrenador");
        }
    }

    /**
     * Este metodo cqalcula la cantidad de miembros por entrenador
     * @return retorna un int, cuantos miembros tiene un entrenador en especifico.
     */
    //calcular la cantidad de miembros por entrenador
    public int cantMiembrosxEntrenador(){return miembrosAsignados.size();}

    /*
    public JSONObject toJson()
    {
        JSONObject JSON = new JSONObject();
        JSON.put("nombre", getNombre());
        JSON.put("apellido" , getApellido());

        return JSON;
    }

     */
}


