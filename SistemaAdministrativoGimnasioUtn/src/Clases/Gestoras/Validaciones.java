package Clases.Gestoras;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Esta clase solo tiene metodos staticos que sirven para que el usuario ingrese datos
 * y se verifique si son validos
 */
public class Validaciones {

    /**
     * Valida cualquier palabra que se ingrese. Tiene que tener solo letras
     * @param mensaje lo que imprime antes de ingresar los datos;
     * @param entrada el scanner;
     * @return la palabra que fue validada
     */
    public static String validarCadena(String mensaje, Scanner entrada) {
        String cadena;
        do {
            System.out.println(mensaje);
            cadena = entrada.nextLine().trim();
            if (cadena.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return cadena;
            }
            System.out.println("Entrada inválida. Solo se permiten letras y espacios.");
        } while (true);
    }

    /**
     * Valida el documento ingresado. Tiene que tener 8 digitos
     * @param mensaje lo que imprime antes de ingresar los datos;
     * @param entrada el scanner;
     * @return el documento que fue validado
     */
    public static String validarDocumento(String mensaje, Scanner entrada) {
        String documento;
        do {
            System.out.println(mensaje);
            documento = entrada.nextLine().trim();
            if (documento.matches("\\d{8}")) {
                return documento;
            }
            System.out.println("Documento invalido. Tiene que tener 8 digitos ");
        } while (true);
    }

    /**
     * Valida la fecha ingresada. Tiene que ser de formato YYYY-MM-DD
      * @param mensaje lo que imprime antes de ingresar los datos;
     * @param entrada el scanner;
     * @return la fecha que fue validada
     */
  public static LocalDate validarFecha(String mensaje, Scanner entrada) {
        do {
            try {
                System.out.println(mensaje);
                String fecha = entrada.nextLine().trim();
                return LocalDate.parse(fecha);
            } catch (Exception e) {
                System.out.println("Fecha invalida. Tiene que ser en el formato YYYY-MM-DD ");
            }
        } while (true);
    }

    /**
     * Valida el numero entero que fue ingresado. Tiene que ser positivo
     * @param mensaje lo que imprime antes de ingresar los datos;
     * @param entrada el scanner;
     * @return el numero entero que fue validado
     */
    public static int validarEntero(String mensaje, Scanner entrada) {
        int numero;
        do {
            try {
                System.out.println(mensaje);
                numero = Integer.parseInt(entrada.nextLine().trim());
                if (numero > 0) {
                    return numero;
                }
                System.out.println("Ingrese un número positivo ");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tiene que ser un número entero ");
            }
        } while (true);
    }

    /**
     * Valida cualquier string que se ingrese. No tiene que ser vacio. Acepta cualquier simbolo
     * @param mensaje lo que imprime antes de ingresar los datos;
     * @param entrada el scanner;
     * @return el String que fue validado
     */
    public static String noVacio(String mensaje, Scanner entrada){
        String palabra;
        do {
            System.out.println(mensaje);
            palabra = entrada.nextLine().trim();
            if (!palabra.isEmpty()){
                return palabra;
            }
            System.out.println("Entrada invalida. Tiene que tener caracteres ");
        }while (true);
    }

    /**
     * Este metodo sirve para mover los datos que ya no se quieren mostrar en la consola
     */
    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    /**
     * Este metodo sirve para esperar una confirmacion del usuario para continuar, antes de
     * que la consola sea limpiada
     */
    public static void esperarTeclaParaContinuar(){
        System.out.println("\nPresione cualquier numero o simbolo para continuar...");
        try {
            System.in.read(); // Espera una entrada
            System.in.read(); // Limpia el salto de línea residual
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
