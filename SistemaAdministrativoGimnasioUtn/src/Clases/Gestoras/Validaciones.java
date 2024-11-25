package Clases.Gestoras;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Validaciones {

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

    public static void limpiarConsola() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

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
