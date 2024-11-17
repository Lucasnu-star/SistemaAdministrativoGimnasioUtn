package Clases;

/**
 * Esta clase representa un Reporte con una descripcion(String), idMaquina(String) y el dniUsuario(String) donde se va a guardar un reporte de la maquina.
 *
 * @version 1
 */
public class Reporte {
    private String descripcion;
    private String idMaquina;
    private String DNIusuario;

    public Reporte(String descripcion, String idMaquina, String DNIusuario) {
        this.descripcion = descripcion;
        this.idMaquina = idMaquina;
        this.DNIusuario = DNIusuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getDNIusuario() {
        return DNIusuario;
    }

    public void setDNIusuario(String DNIusuario) {
        this.DNIusuario = DNIusuario;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "descripcion='" + descripcion + '\'' +
                ", idMaquina='" + idMaquina + '\'' +
                ", DNIusuario='" + DNIusuario + '\'' +
                '}';
    }
}
