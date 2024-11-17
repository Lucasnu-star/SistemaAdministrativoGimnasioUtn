package Clases;
import Enums.eTipoMembresia;

import java.util.Objects;

/**
 * Clase Membresia, esta clase representa una membresia, donde contiene atributos propios, descripcion(String), eTIPOMEMBERSIA eTipoMembresia(Enum) y costoMensual(long)
 */


public final class Membresia {

    //Atributos
    private String descripcion;
    private eTipoMembresia eTipomembresia;
    private long costoMensual;

    //Constuctor
    public Membresia(String descripcion, eTipoMembresia eTipomembresia, long constoMensual) {
        this.descripcion = descripcion;
        this.eTipomembresia = eTipomembresia;
        this.costoMensual = constoMensual;
    }

    public Membresia() {
    }

    //Getters

    public String getDescripcion() {
        return descripcion;
    }

    public eTipoMembresia getTipomembresia() {
        return eTipomembresia;
    }

    public long getConstoMensual() {
        return costoMensual;
    }

    //Setters

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipomembresia(eTipoMembresia eTipomembresia) {
        this.eTipomembresia = eTipomembresia;
    }

    public void setCostoMensual(long costoMensual) {
        this.costoMensual = costoMensual;
    }

    //Equals && HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membresia membresia = (Membresia) o;
        return costoMensual == membresia.costoMensual && Objects.equals(descripcion, membresia.descripcion) && Objects.equals(eTipomembresia, membresia.eTipomembresia);
    }
    @Override
    public int hashCode() {
        return Objects.hash(descripcion, eTipomembresia, costoMensual);
    }

    //ToString
    @Override
    public String toString() {
        return "Membresia " +
                "descripcion='" + descripcion + '\'' +
                ", tipomembresia=" + eTipomembresia +
                ", constoMensual=" + costoMensual;
    }
}

