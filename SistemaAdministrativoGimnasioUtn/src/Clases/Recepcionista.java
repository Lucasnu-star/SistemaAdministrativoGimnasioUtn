package Clases;

import Clases.Gestoras.GestionGenericaGimnasio;
import java.time.LocalDate;
import java.util.Scanner;

import Enums.eEspecialidad;
import Enums.eTipoMaquina;
import Enums.eTipoMembresia;
import Excepciones.ListaVaciaExcepcion;
import Excepciones.UsuarioNoEncontradoExcepcion;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *Clase Recepcionista, esta clase represnta un Recepcionista, extiende de Empleado por lo tanto hereda sus atributos, no contiene atributos propios
 * Esta clase es la que va a manejar casi todo ya que es la encargada de agregar, eliminar, consultar datos de las listas del gimnasio
 *
 * @version 1
 */
public final class Recepcionista extends Empleado {

    private String nombreUsuario;

    private String contrasenia;

    //Constructor
    public Recepcionista(String nombre, String apellido, String documento, LocalDate fechaNacimiento, int salario, String horario, String nombreUsuario, String contrasenia) {
        super(nombre, apellido, documento, fechaNacimiento, salario, horario);
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Recepcionista() {
    }

    //ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Recepcionista = " + super.toString());
        sb.append("\nnombreUsuario= ").append(nombreUsuario);
        sb.append("\ncontraseña= ").append(contrasenia);
        return sb.toString();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Convertir de un Archivo JSON a un objeto Recepcionista
     *
     * @param recepcionista;
     */
    public Recepcionista(JSONObject recepcionista) {
        try {
            setNombre(recepcionista.getString("nombre"));
            setApellido(recepcionista.getString("apellido"));
            setDocumento(recepcionista.getString("documento"));
            String fechaNacimiento = recepcionista.getString("fechaNacimiento");
            LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
            setFechaNacimiento(fechaNac);
            setSalario(recepcionista.getInt("salario"));
            setHorario(recepcionista.getString("horario"));
            setNombreUsuario(recepcionista.getString("nombreUsuario"));
            setContrasenia(recepcionista.getString("contrasenia"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo para convertir de un objeto a un Archivo JSON
     *
     * @return jsonObject
     */
    public JSONObject toJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();

            jsonObject.put("nombre", getNombre());
            jsonObject.put("apellido", getApellido());
            jsonObject.put("documento", getDocumento());
            jsonObject.put("fechaNacimiento", getFechaNacimiento());
            jsonObject.put("salario", getSalario());
            jsonObject.put("horario", getHorario());
            jsonObject.put("nombreUsuario", getNombreUsuario());
            jsonObject.put("contrasenia", getContrasenia());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    //Metodos

    /**
     * Este metodo sirve para simular que se paga la couta de un miembro en especifico
     *
     * @param miembro;
     * @return String;
     */
    public static String pagarCouta(Miembro miembro) {
        if (miembro.isEstadoMembresia()) {
            return "La couta ya ha sido pagada " + miembro.getFechaUltimoPago();
        }
        miembro.setEstadoMembresia(true);
        return "Se pago la couta de: " + miembro.getNombre() + " " + miembro.getApellido();
    }

    /**
     * Metodo generico agregar a lista, este metodo se hace que el recepcionista pueda agregar a una lista en este caso generica (podemos pasarle cualquir tipo de lista)
     * y que lo agregue a la lista especifica. La lista y el objeto de la lista tienen que ser de igual objeto ya que no podemos guardar en una lista objetos que no permitan esa lista
     *
     * @param lista;
     * @param clave;
     * @param obj;
     * @param <T>;
     */
    public static <T> void agregarDeLista(GestionGenericaGimnasio<T> lista, String clave, T obj) {
        lista.agregar(clave, obj);
    }

    /**
     * Este  metodo sirve para eliminar un obj mediante su clave(Dni) de una lista en este caso una lista generica pasada por parametro
     *
     * @param lista;
     * @param clave;
     * @param <T>;
     * @throws UsuarioNoEncontradoExcepcion;
     */
    public static <T> String eliminarDeLista(GestionGenericaGimnasio<T> lista, String clave) throws UsuarioNoEncontradoExcepcion {
        if (lista.eliminar(clave) == null) {
            throw new UsuarioNoEncontradoExcepcion("No se ha encontrado el elemento a eliminar ");
        }
        return "Se ha eliminado el elemento correctamente ";
    }

    /**
     * Este metodo sirve para consultar, por parametro le vamos a pasar una lista donde querramos consultar y un string clave(Documento)
     *
     * @param lista;
     * @param key;
     * @param <T>;
     * @return retorna el objeto que querramos consultar.
     */
    public static <T> T consultar(GestionGenericaGimnasio<T> lista, String key) throws UsuarioNoEncontradoExcepcion {
        T t = lista.consultar(key);
        if (t == null) {
            throw new UsuarioNoEncontradoExcepcion("No se ha encontrado el elemento a buscar ");
        }
        return t;
    }

    /**
     * Este metodo sirve para calcular el salario por entrenador, cada 5 miembros asignados se le suma un porcentaje
     *
     * @param gestionEntrenadores;
     * @param dni;
     */
    public static void calcularSalario(GestionGenericaGimnasio<Entrenador> gestionEntrenadores, String dni) {

        Entrenador entrenador = new Entrenador();
        try {
            entrenador = gestionEntrenadores.consultar(dni);

            double salarioBase = entrenador.getSalario();

            int cantidadMiembros = entrenador.getMiembrosAsignados().size();

            int incrementos = cantidadMiembros / 2;
            double salarioFinal = salarioBase * (1 + (0.10 * incrementos));

            System.out.println("Salario del entrenador después de bonificación: " + salarioFinal);


        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     * Este metodo sirve para que el recepcionista pueda mostrar los elementos de una lista en especifico pasado por parametro.
     *
     * @param gestion;
     * @param <T>;
     * @throws ListaVaciaExcepcion;
     */
    public static <T> void mostrarElementosLista(GestionGenericaGimnasio<T> gestion) throws ListaVaciaExcepcion {
        if (gestion.getGestionUsuario().isEmpty()) {
            throw new ListaVaciaExcepcion("La lista no tiene elementos ");
        }
        for (T elemento : gestion.getGestionUsuario().values()) {
            System.out.println(elemento);
        }
    }

    /**
     * Este metodo crea un reporte a un maquina especifica, la cual se marcara como no disponible
     * @param maquinas recibe la lista;
     * @return El reporte que luego sera guardado
     */
    public static Reporte crearReporte(GestionGenericaGimnasio<Maquina> maquinas) {
        Reporte reporte = new Reporte();
        Scanner scaner = new Scanner(System.in);
        System.out.println("indica el id de la maquina ");
        String id = scaner.nextLine();
        try {
            Maquina maquina = Recepcionista.consultar(maquinas, id);
            reporte.setIdMaquina(id);
            maquina.setEstadoMaquina(false);
            System.out.println("ingrese descripcion del reporte");
            reporte.setDescripcion(scaner.nextLine());

        }catch (UsuarioNoEncontradoExcepcion e){
            System.out.println(e.getMessage());
        }

        return reporte;
    }


    //METODOS PARA FILTRAR LISTAS SEGUN NOMBRE O ID/DOCUMENTO

    public static <T extends Usuario> void filtroPorNombre(GestionGenericaGimnasio<T> gestion, String filtro) {
        // Verificar que el filtro no sea null
        if (gestion == null || filtro == null) {
            System.out.println("Parámetros de entrada no válidos");
            return;
        }

        // Iterar a través de los usuarios y mostrar aquellos cuyo nombre contiene el filtro (sin distinguir entre mayúsculas y minúsculas)
        for (T elemento : gestion.getGestionUsuario().values()) {
            if (elemento != null && elemento.getNombre() != null &&
                    elemento.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                System.out.println(elemento);
            }
        }
    }

    public static <T extends Usuario> void filtroPorDocumento(GestionGenericaGimnasio<T> gestion, String filtro) {
        for (T elemento : gestion.getGestionUsuario().values()) {
            if (elemento.getDocumento().contains(filtro)) {
                System.out.println(elemento);
            }
        }
    }

    public static void miembroFiltroPorEstado(GestionGenericaGimnasio<Miembro> gestion, boolean filtro) {
        for (Miembro elemento : gestion.getGestionUsuario().values()) {
            if (elemento.isEstadoMembresia() == filtro) {
                System.out.println(elemento);
            }
        }
    }

    public static void miembroFiltroPorTipo(GestionGenericaGimnasio<Miembro> gestion, eTipoMembresia filtro) {
        for (Miembro elemento : gestion.getGestionUsuario().values()) {
            if (elemento.getMembresia().getTipomembresia() == filtro) {
                System.out.println(elemento);
            }
        }
    }

    public static void entrenadorFiltroPorTipo(GestionGenericaGimnasio<Entrenador> gestion, eEspecialidad filtro) {
        for (Entrenador elemento : gestion.getGestionUsuario().values()) {
            if (elemento.getEspecialidad() == filtro) {
                System.out.println(elemento);
            }
        }
    }


    public static void maquinaFiltroPorNombre(GestionGenericaGimnasio<Maquina> gestion, String filtro) {
        // Verificar que el filtro y gestion no sean null
        if (gestion == null || filtro == null) {
            System.out.println("Parámetros de entrada no válidos");
            return;
        }

        // Iterar a través de las máquinas y mostrar aquellas cuyo nombre contiene el filtro (sin distinguir entre mayúsculas y minúsculas)
        for (Maquina elemento : gestion.getGestionUsuario().values()) {
            if (elemento != null && elemento.getNombre() != null &&
                    elemento.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                System.out.println(elemento);
            }
        }
    }


    public static void maquinaFiltroPorTipo(GestionGenericaGimnasio<Maquina> gestion, eTipoMaquina filtro) {
        for (Maquina elemento : gestion.getGestionUsuario().values()) {
            if (elemento.getTipoMaquina() == filtro) {
                System.out.println(elemento);
            }
        }
    }

    public static void maquinaFiltroPorEstado(GestionGenericaGimnasio<Maquina> gestion, boolean filtro) {
        for (Maquina elemento : gestion.getGestionUsuario().values()) {
            if (elemento.isEstadoMaquina() == filtro) {
                System.out.println(elemento);
            }
        }
    }

}


