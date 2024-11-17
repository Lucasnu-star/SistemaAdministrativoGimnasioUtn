package Clases;

import Clases.Gestoras.GestionGenericaGimnasio;

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
        this.gestionMaquinas = new GestionGenericaGimnasio<>();
        this.especialidadesGimnasio =  new ArrayList<>(); // Ver
    }

    public Gimnasio() {
        this.gestionEntrenadores = new GestionGenericaGimnasio<>();
        this.gestionMiembros = new GestionGenericaGimnasio<>();
        this.gestionPersonalMantenimiento = new GestionGenericaGimnasio<>();
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
        return "Gimnasio " +
                "  nombreGimnasio='" + nombreGimnasio + '\'' +
                ", direccionGimnasio='" + direccionGimnasio + '\'' +
                ", capacidadGimnasio=" + capacidadGimnasio +
                ", especialidadesGimnasio=" + especialidadesGimnasio;
    }


}

