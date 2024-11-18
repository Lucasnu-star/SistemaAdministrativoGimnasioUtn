package Clases;
import Enums.eTipoMaquina;

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

    //Constructor
    public Maquina(String nombre, eTipoMaquina tipoMaquina, boolean estadoMaquina) {
        this.nombre = nombre;
        this.tipoMaquina = tipoMaquina;
        this.estadoMaquina = estadoMaquina;
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

    //ToString
    @Override
    public String toString() {
        return "Maquina " +
                "nombre=" + nombre + '\'' +
                ", tipoMaquina=" + tipoMaquina +
                ", estadoMaquina=" + estadoMaquina;
    }

    public void marcarComoDisponible(){
        setEstadoMaquina(true);
    }
}
