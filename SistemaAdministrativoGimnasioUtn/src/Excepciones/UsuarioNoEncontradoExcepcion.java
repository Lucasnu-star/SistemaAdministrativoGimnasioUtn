package Excepciones;

public class UsuarioNoEncontradoExcepcion extends RuntimeException {
    public UsuarioNoEncontradoExcepcion(String message) {
        super(message);
    }
}
