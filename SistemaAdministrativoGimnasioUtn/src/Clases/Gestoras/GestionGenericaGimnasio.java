package Clases.Gestoras;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Clase GestionGimnasio, esta clase tiene el trabajo de gestionar el gimnasio
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
     * Metodo para consultar los elementos de un tipo especifico
     * @param clase;
     * @return List<T>
     */
    public List<T> consultarPorClase(Class<T> clase) {
        List<T> result = new ArrayList<>();
        for (T obj : gestionUsuario.values()) {
            if (clase.isInstance(obj)) {
                result.add(obj);
            }
        }
        return result;
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

