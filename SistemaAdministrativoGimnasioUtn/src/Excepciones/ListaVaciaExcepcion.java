package Excepciones;

/**
 * Esta exception sirve por si se intenta leer alguna coleccion y no tiene elementos
 */
public class ListaVaciaExcepcion extends RuntimeException {
    public ListaVaciaExcepcion(String message) {
        super(message);
    }
}
