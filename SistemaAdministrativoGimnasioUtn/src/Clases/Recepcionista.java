package Clases;

import Clases.Gestoras.GestionGenericaGimnasio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import Interfaces.iReportarMaquina;
import Excepciones.MembresiaExpiradaExcepcion;

/**
 *Clase Recepcionista, esta clase represnta un Recepcionista, extiende de Empleado por lo tanto hereda sus atributos, no contiene atributos propios
 * Esta clase es la que va a manejar casi todo ya que es la encargada de agregar, eliminar, consultar datos de las listas del gimnasio
 *
 * @version 1
 */
public final class Recepcionista extends Empleado {


    //Constructor

    public Recepcionista(String nombre, String apellido, String documento, LocalDate fechaNacimiento, int salario, String horario) {
        super(nombre, apellido, documento, fechaNacimiento, salario, horario);
    }

    public Recepcionista() {
    }

    //ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        return sb.toString();
    }


    //Metodos


    /**
     * Esta metodo verifica la membresia por miembro, se le pasa por parametro un Miembro
     * Contiene una excepcion personalizada y otra excepcion llamada IllegalArgumentException que verifica que el miembro no puede ser Null.
     * @param gimnasio
     * @param Dni
     * @throws MembresiaExpiradaExcepcion
     */

    //Ver este metodo
    public void verificarMembresia(Gimnasio gimnasio , String Dni) throws MembresiaExpiradaExcepcion {
        Miembro miembro = gimnasio.getGestionMiembros().getGestionUsuario().get(Dni);
        if (miembro == null) {
            throw new IllegalArgumentException("El miembro no puede ser nulo.");
        }
        if (miembro.isEstadoMembresia() == true) {
            System.out.println("La membresia del miembro esta activa");
        }else
        {
            throw new MembresiaExpiradaExcepcion("La membresia esta expirada");
        }

    }

    /**
     * Metodo generico agregar a lista, este metodo se hace que el recepcionista pueda agregar a una lista en este caso generica (podemos pasarle cualquir tipo de lista)
     * y que lo agregue a la lista especifica. La lista y el objeto de la lista tienen que ser de igual objeto ya que no podemos guardar en una lista objetos que no permitan esa lista
     *
     * @param lista
     * @param clave
     * @param obj
     * @param <T>
     */
    public static <T> void agregarDeLista(GestionGenericaGimnasio<T> lista, String clave, T obj) {
        lista.agregar(clave, obj);
    }

    /**
     * Este  metodo sirve para eliminar un obj mediante su clave(Dni) de una lista en este caso una lista generica pasada por parametro
     * @param lista
     * @param clave
     * @param <T>
     */
    public static <T> void eliminarDeLista(GestionGenericaGimnasio<T> lista, String clave) {
        lista.eliminar(clave);
    }

    /**
     * Este metodo sirve para consultar, por parametro le vamos a pasar una lista donde querramos consultar y un string clave(Documento)
     * @param lista
     * @param key
     * @return retorna el objeto que querramos consultar.
     * @param <T>
     */
    public static <T> T consultar(GestionGenericaGimnasio<T> lista, String key) {
        T t = lista.consultar(key);
        return t;
    }

    /**
     * Este metodo sirve para calcular el salario por entrenador, cada 5 miembros asignados se le suma un porcentaje
     * @param gestionEntrenadores
     * @param dni
     */
    //Ver de que otra forma lo podemos hacer para que sea mas sencillo mostrarlo y no tener que cargar 5 miembros para que le sume el porcentaje

    public static void calcularSalario(GestionGenericaGimnasio<Entrenador> gestionEntrenadores, String dni) {

        Entrenador entrenador= new Entrenador();
        try
        {


            for (Map.Entry<String, Entrenador> entrenadorr : gestionEntrenadores.getGestionUsuario().entrySet()) {
                if(Objects.equals(entrenadorr.getValue().getDocumento(), dni))
                {
                    double salarioBase = entrenador.getSalario();

                    int cantidadMiembros = entrenador.getMiembrosAsignados().size();

                    int incrementos = cantidadMiembros / 2;
                    double salarioFinal = salarioBase * (1 + (0.10 * incrementos));

                    System.out.println("Salario del entrenador después de bonificación: " + salarioFinal);

                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }



    /**
     * Este metodo sirve para que el recepcionisa le puedo asignar un miembro a un entrenador en especifico, mediante el dni del entrenador, la lista y el miembro que se le quiere agregar.
     * @param listaEntrenadores
     * @param miembro
     * @param dniEntrenador
     */
    public void agregarMiembroAEntrenador(GestionGenericaGimnasio<Entrenador> listaEntrenadores, Miembro miembro, String dniEntrenador) {
        Entrenador entrenador = listaEntrenadores.consultar(dniEntrenador);
        if (entrenador != null) {
            entrenador.asignarMiembro(miembro);
            System.out.println("Miembro asignado al entrenador con DNI: " + dniEntrenador);
        } else {
            System.out.println("Entrenador no encontrado.");
        }
    }


    /**
     * Este metodo sirve para que el recepcionista reporte una maquina en especifico
     * @param gestionMaquinas
     * @param desc
     * @param idMaquina
     * @param dni
     */

    //Ver este metodo que esta raro
    public void reportarMaquina(GestionGenericaGimnasio<? extends iReportarMaquina> gestionMaquinas, String desc, String idMaquina, String dni) {
        iReportarMaquina maquina = gestionMaquinas.consultar(idMaquina);
        if (maquina != null) {
            Reporte reporte = new Reporte(desc, idMaquina, dni);
            maquina.reportarMaquina();
            System.out.println("Reporte generado para la máquina: " + idMaquina);
        } else {
            System.out.println("Máquina no encontrada.");
        }
    }

    /**
     * Este metodo sirve para que el recepcionista pueda mostrar los elementos de una lista en especifico pasado por parametro.
     * @param gestion
     * @param <T>
     */
    public static <T> void mostrarElementosLista(GestionGenericaGimnasio<T> gestion) {
        System.out.println("Lista de elementos en GestionGym:");
        for (Map.Entry<String, T> rec : gestion.getGestionUsuario().entrySet()) {
            String clave = rec.getKey();  // Obtiene la clave del mapa
            T valor = rec.getValue();     // Obtiene el valor del mapa
            System.out.println("Clave: " + clave + ", Valor: " + valor);
        }
    }

    /**
     * Este metodo sirve para modificar un entrenador, se le pasa por parametro el dni del entrenador y la lista donde se encuentra, adentro vamos a pregunarle los datos nuevos
     * y realizar el cambio
     * @param dni
     * @param lista
     */
    public static void modificarEntrenador(String dni, GestionGenericaGimnasio<Entrenador> lista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nuevo nombre:");
        String nuevoNombre = scanner.nextLine();

        boolean entrenadorEncontrado = false;

        for (Map.Entry<String, Entrenador> rec : lista.getGestionUsuario().entrySet()) {
            if (rec.getKey().equals(dni)) {
                // Obtener el entrenador actual
                Entrenador entrenador = rec.getValue();
                entrenador.setNombre(nuevoNombre); // Modificar el nombre del entrenador
                entrenadorEncontrado = true;
                System.out.println("Nombre del entrenador actualizado: " + entrenador);
                break;
            }
        }

        if (!entrenadorEncontrado) {
            System.out.println("No se encontró un entrenador con el documento: " + dni);
        }
    }

    /**
     * Este metodo sirve para que el recepcionista modifque el miembro, una vez pasado los parametros se solicita el nombre, la fecha de nacimiento nueva y realiza el cambio.
     * @param dni
     * @param lista
     */

    //Ver si este y la del entrenador no se puede hacer un metodo solo para los dos
    public static void modificarMiembro(String dni, GestionGenericaGimnasio<Miembro> lista) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nuevo nombre
        System.out.println("Ingresa el nuevo nombre:");
        String nuevoNombre = scanner.nextLine();

        // Solicitar la nueva fecha de nacimiento (formato: yyyy-MM-dd)
        System.out.println("Ingresa la nueva fecha de nacimiento (formato: yyyy-MM-dd):");
        String nuevaFechaNacimientoStr = scanner.nextLine();

        // Convertir la cadena de texto a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoStr, formatter);

        boolean miembroEncontrado = false;

        // Buscar al miembro en la lista por su DNI
        for (Map.Entry<String, Miembro> entry : lista.getGestionUsuario().entrySet()) {
            if (entry.getKey().equals(dni)) {

                Miembro miembro = entry.getValue();
                miembro.setNombre(nuevoNombre); // Modificar el nombre del miembro
                miembro.setFechaNacimiento(nuevaFechaNacimiento); // Modificar la fecha de nacimiento
                miembroEncontrado = true;
                System.out.println("Miembro actualizado: " + miembro);
                break;
            }
        }

        if (!miembroEncontrado) {
            System.out.println("No se encontró un miembro con el documento: " + dni);
        }
    }

    /**
     * Este metodo sirve para que el recepcionista modifque el Personal de mantenimiento, una vez pasado los parametros se solicita el nombre y realiza el cambio.
     * @param dni
     * @param lista
     */
    public static void modificarPersonaldeMantenimiento (String dni, GestionGenericaGimnasio<PersonalMantenimiento> lista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nuevo nombre:");
        String nuevoNombre = scanner.nextLine();

        boolean personalEncontrado = false;

        for (Map.Entry<String, PersonalMantenimiento> rec : lista.getGestionUsuario().entrySet()) {
            if (rec.getKey().equals(dni)) {
                // Obtener el entrenador actual
                PersonalMantenimiento personalMantenimiento = rec.getValue();
                personalMantenimiento.setNombre(nuevoNombre); // Modificar el nombre del entrenador
                personalEncontrado = true;
                System.out.println("Nombre del Personal actualizado: " + personalMantenimiento);
                break;
            }
        }

        if (!personalEncontrado) {
            System.out.println("No se encontró un entrenador con el documento: " + dni);
        }
    }

    /**
     * Este metodo sirve para que el recepcionista modifique una maquina, se le pasa por parametro el di y la lista de las maquinas despues se le pide el nuevo nombre y realiza el cambio
     * @param id
     * @param lista
     */
    public static void modificarMaquina(String id, GestionGenericaGimnasio<Maquina> lista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nuevo nombre:");
        String nuevoNombre = scanner.nextLine();

        boolean maquinaEncontrada = false;

        for (Map.Entry<String, Maquina> rec : lista.getGestionUsuario().entrySet()) {
            if (rec.getKey().equals(id)) {
                // Obtener el entrenador actual
                Maquina maq = rec.getValue();
                maq.setNombre(nuevoNombre); // Modificar el nombre del entrenador
                maquinaEncontrada = true;
                System.out.println("Nombre de la maquina actualizado: " + maq.getNombre());
                break;
            }
        }

    }
}


