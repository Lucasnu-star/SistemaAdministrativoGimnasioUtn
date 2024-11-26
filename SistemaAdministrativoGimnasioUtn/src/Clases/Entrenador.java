package Clases;
import Enums.eEspecialidad;
import Excepciones.ListaVaciaExcepcion;
import Excepciones.UsuarioNoEncontradoExcepcion;
import org.json.JSONArray;
import org.json.JSONException;
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
    //Modificar para que el salario del entrenador sea un salario por defecto
    public Entrenador(String nombre, String apellido, String documento, LocalDate fechaNacimiento, int salario, String horario, eEspecialidad especialidad) {
        super(nombre, apellido, documento, fechaNacimiento, salario, horario);
        this.certificados = new HashSet<>(); // Ver
        this.especialidad = especialidad;
        this.miembrosAsignados = new ArrayList<>();
    }
    public Entrenador() {
        this.certificados= new HashSet<>();
        this.miembrosAsignados= new ArrayList<>();

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
        StringBuilder sb = new StringBuilder("\nEntrenador = "+super.toString());
        sb.append("\nEspecialidad=").append(especialidad);

        // Miembros
        if (miembrosAsignados.isEmpty()) {
            sb.append("\nMiembros: Sin miembros asignados");
        } else {
            sb.append("\nCantidad de miembros: ").append(miembrosAsignados.size());

        }

        // Certificados
        if (certificados.isEmpty()) {
            sb.append("\nCertificados: Sin certificados disponibles");
        } else {
            sb.append("\nCertificados:");
            for (String certificado : certificados) {
                sb.append("\n  - ").append(certificado);
            }
        }

        return sb.toString();
    }


    /**
     * Este metodo sirve para asignar un miembro a un entrenador en especifico, por paramtro va a recibir un miembrom, para poder añadirlo a la lista
     * @param miembro;
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
    public void consultarMiembros() throws ListaVaciaExcepcion{
        if (miembrosAsignados == null || miembrosAsignados.isEmpty()) {
            throw new ListaVaciaExcepcion("El entrenador no tiene miembros asignados ");
        } else {
            System.out.println("Lista de miembros asignados al entrenador " + getNombre() + ":");
            for (Miembro miembro : miembrosAsignados) {
                System.out.println("- Nombre: " + miembro.getNombre() + ", Dni: " + miembro.getDocumento()+ ", Estado Membresía: "
                        + miembro.isEstadoMembresia());
            }
        }
    }

    /**
     * Este metodo sirve para agregar un certiciado a un entrandor en especifico, por parameotr le pasamos un certificado (Strign).
     * @param certificado;
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
     * @param miembro;
     */
    public String eliminarMiembro(Miembro miembro) throws UsuarioNoEncontradoExcepcion
    {
        if (!miembrosAsignados.contains(miembro)){
           throw new UsuarioNoEncontradoExcepcion("El miembro no fue encontrado en la lista ");
        }
        miembrosAsignados.remove(miembro);
        return "Miembro eliminado correctamente.";
    }

    /**
     * Este metodo cqalcula la cantidad de miembros por entrenador
     * @return retorna un int, cuantos miembros tiene un entrenador en especifico.
     */
    //calcular la cantidad de miembros por entrenador
    public int cantMiembrosxEntrenador(){return miembrosAsignados.size();}

    /**
     * Convertir de un Archivo JSON a un objeto Entrenador
     * @param jsonEntrenador;
     */

    public Entrenador(JSONObject jsonEntrenador) {
        try{
            setNombre(jsonEntrenador.getString("nombre"));
            setApellido(jsonEntrenador.getString("apellido"));
            setDocumento(jsonEntrenador.getString("documento"));
            String fechaNacimiento = jsonEntrenador.getString("fechaNacimiento");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            setFechaNacimiento(fechaNac);
            setSalario(jsonEntrenador.getInt("salario"));
            setHorario(jsonEntrenador.getString("horario"));
            String especialidad = jsonEntrenador.getString("especialidad");
            eEspecialidad eEspecialidad = Enums.eEspecialidad.valueOf(especialidad);
            setEspecialidad(eEspecialidad);

            JSONArray jsonArray = jsonEntrenador.getJSONArray("certificados");

            // Convertir directamente el JSONArray al HashSet
            HashSet<String> certificaciones = new HashSet<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                certificaciones.add(jsonArray.getString(i));
            }
            setCertificados(certificaciones);


            // convierto el jsonArray a Arraylist
            miembrosAsignados = new ArrayList<>();
            JSONArray jsonArray1 = jsonEntrenador.getJSONArray("miembrosAsignados");

            if (jsonArray1 != null) {
                for (int i = 0; i < jsonArray1.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray1.getJSONObject(i);
                        Miembro miembro = new Miembro(jsonObject); // Asegúrate de que el constructor maneje excepciones.
                        miembrosAsignados.add(miembro);
                    } catch (JSONException e) {
                        System.err.println("Error al convertir un miembro asignado: " + e.getMessage());
                    }
                }
            }

            setMiembrosAsignados(miembrosAsignados);


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
            jsonObject.put("especialidad", getEspecialidad());


            // convierto la lista a jsonArray
            JSONArray jsonArray = new JSONArray();
            for (String certificado : certificados){
                jsonArray.put(certificado);
            }

            jsonObject.put("certificados", jsonArray);

            JSONArray miembrosJson = new JSONArray();
            for (Miembro miembro : miembrosAsignados) {
                miembrosJson.put(miembro.toJSON());
            }
            jsonObject.put("miembrosAsignados", miembrosJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}


