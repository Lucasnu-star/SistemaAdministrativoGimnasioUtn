package Clases;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;
/**
 *  Clase Miembro, esta clase representa un Miembro, extiende de Usuario por lo tanto hereda sus atributos, ademas tiene atributos propios como membresia (Membresia),
 *  estadoMembresia(boolean) y fechaInscripcion(LocalDate)
 *
 *  @version 1
 */

//Clase Usuario
public final class Miembro extends Usuario{

    //Atributos
    private Membresia membresia;
    private boolean estadoMembresia; // True o False
    private LocalDate fechaIncripcion;
    private LocalDate fechaUltimoPago;

    //Constructor
    public Miembro(String nombre, String apellido, String documento, LocalDate fechaNacimiento, Membresia membresia, boolean estadoMembresia, LocalDate fechaIncripcion) {
        super(nombre, apellido, documento, fechaNacimiento);
        this.membresia = membresia;
        this.estadoMembresia = true;
        this.fechaIncripcion = fechaIncripcion;
        this.fechaUltimoPago = LocalDate.now(); // cada vez que se agrega un nuevo miembro se carga la fecha actual
    }
    public Miembro(){
    }

    //Getters

    public Membresia getMembresia() {
        return membresia;
    }

    public boolean isEstadoMembresia() {
        return estadoMembresia;
    }

    public LocalDate getFechaIncripcion() {
        return fechaIncripcion;
    }

    public LocalDate getFechaUltimoPago() {
        return fechaUltimoPago;
    }

    //Setters

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }


    public void setEstadoMembresia(boolean estadoMembresia) {
        this.estadoMembresia = estadoMembresia;
    }

    public void setFechaIncripcion(LocalDate fechaIncripcion) {
        this.fechaIncripcion = fechaIncripcion;
    }

    public void setFechaUltimoPago(LocalDate fechaUltimoPago) {
        this.fechaUltimoPago = fechaUltimoPago;
    }

    //Equals && HashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Miembro miembro = (Miembro) o;
        return estadoMembresia == miembro.estadoMembresia && Objects.equals(membresia, miembro.membresia) && Objects.equals(fechaIncripcion, miembro.fechaIncripcion) && Objects.equals(fechaUltimoPago, miembro.fechaUltimoPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), membresia, estadoMembresia, fechaIncripcion, fechaUltimoPago);
    }

    @Override
    public String toString() {
        return "Miembro{" +super.toString()+
                "membresia=" + membresia +
                ", estadoMembresia=" + estadoMembresia +
                ", fechaIncripcion=" + fechaIncripcion +
                ", fechaUltimoPago=" + fechaUltimoPago +
                "} ";
    }

    /**
     * Metodo para convertir de un Archivo Json a un objeto Miembro
     * @param jsonMiembro
     */
    public Miembro(JSONObject jsonMiembro) {
        try{
            setNombre(jsonMiembro.getString("nombre"));
            setApellido(jsonMiembro.getString("apellido"));
            setDocumento(jsonMiembro.getString("documento"));
            String fechaNacimiento = jsonMiembro.getString("fechaNacimiento");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            setFechaNacimiento(fechaNac);
            Membresia membresia1 = new Membresia(jsonMiembro.getJSONObject("membresia"));
            setMembresia(membresia1);
            setEstadoMembresia(jsonMiembro.getBoolean("estadoMembresia"));
            String fechaIns = jsonMiembro.getString("fechaInscripcion");
            setFechaIncripcion(LocalDate.parse(fechaIns));
            String fechaPago = jsonMiembro.getString("fechaPago");
            setFechaUltimoPago(LocalDate.parse(fechaPago));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para convertir de un Objeto Mimebro a un Archivo Json
     * @return
     */
    public JSONObject toJSON(){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();

            jsonObject.put("nombre", getNombre());
            jsonObject.put("apellido", getApellido());
            jsonObject.put("documento", getDocumento());
            jsonObject.put("fechaNacimiento", getFechaNacimiento());
            jsonObject.put("membresia", membresia.toJSON());
            jsonObject.put("estadoMembresia", isEstadoMembresia());
            jsonObject.put("fechaInscripcion", getFechaIncripcion());
            jsonObject.put("fechaPago", getFechaUltimoPago());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}

