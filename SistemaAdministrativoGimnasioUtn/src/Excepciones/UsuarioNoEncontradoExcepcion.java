package Excepciones;

/**
 * Esta exception sirve para cualquier metodo que tenga que buscar algun elemento
 * y no lo encuentre
 */
public class UsuarioNoEncontradoExcepcion extends RuntimeException {
    public UsuarioNoEncontradoExcepcion(String message) {
        super(message);
    }
}
