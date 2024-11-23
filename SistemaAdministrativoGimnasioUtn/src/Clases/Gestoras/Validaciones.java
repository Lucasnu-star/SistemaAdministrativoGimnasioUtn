package Clases.Gestoras;

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

    public static int validarSalario(String mensaje, Scanner entrada) {
        int salario;
        do {
            try {
                System.out.println(mensaje);
                salario = Integer.parseInt(entrada.nextLine().trim());
                if (salario > 0) {
                    return salario;
                }
                System.out.println("El salario tiene que ser un número positivo ");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Tiene que ser un número entero ");
            }
        } while (true);
    }
}
