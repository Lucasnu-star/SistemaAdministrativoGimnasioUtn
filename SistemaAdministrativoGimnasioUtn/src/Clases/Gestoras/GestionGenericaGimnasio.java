package Clases.Gestoras;

import java.util.TreeMap;

/**
 * Clase GestionGimnasio, esta clase tiene el trabajo de gestionar el gimnasio.
 * Tiene un TreeMap que garantiza con su key que todos los datos seran unicos y ordenados.
 * Acepta cualquier tipo de dato.
 *
 * @version 1
 */
public class GestionGenericaGimnasio<T>  {
    //Atributo
    TreeMap<String, T> gestionUsuario;

    //Contructor
    public GestionGenericaGimnasio() {
        this.gestionUsuario = new TreeMap<>();
    }

    //Getter
    public TreeMap<String, T> getGestionUsuario() {
        return gestionUsuario;
    }


    //ToString
    @Override
    public String toString() {
        return "\n"+gestionUsuario;
    }

    public void agregar(String clave, T obj) {
        gestionUsuario.put(clave, obj);
    }

    /**
     * Metodo para eliminar un elemento
     * @param clave;
     */
    public T eliminar(String clave) {
        return gestionUsuario.remove(clave);
    }

    /**
     * Metodo para mostrar elementos de la lista, por parametro se le pasa una clave(String)
     * @param clave;
     * @return T;
     */
    public T consultar (String clave){
        return gestionUsuario.get(clave);
    }











}

