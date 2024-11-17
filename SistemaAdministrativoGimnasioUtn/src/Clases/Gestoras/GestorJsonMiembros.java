package Clases.Gestoras;

import Clases.ManejoArchivos.OperacionesLecturaEscritura;
import Clases.Membresia;
import Clases.Miembro;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Enums.eTipoMembresia;

/**
 * Clase para gestionar el json de los miembros, pero puede convertirse en una clase gestora general
 *
 *  @version 1
 */

public class GestorJsonMiembros {
    private static String nombreArchivo;

    public GestorJsonMiembros() {
        nombreArchivo = "miembros.json";
    }

    public String grabar(GestionGenericaGimnasio<Miembro> miembros){
        OperacionesLecturaEscritura.escribirArchivo(nombreArchivo, miembrosToJsonObject(miembros));
        return "Se ha escrito el archivo correctamente ";
    }

    // mete el jsonArray dentro de un jsonObject
    public JSONObject miembrosToJsonObject(GestionGenericaGimnasio<Miembro> miembros){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("ListaMiembros", TojsonArray(miembros));
        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonObject;
    }

    // convierte la lista en un jsonArray
    public JSONArray TojsonArray(GestionGenericaGimnasio<Miembro> miembros){
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Miembro miembro : miembros.getGestionUsuario().values()){
                jsonArray.put(miembro.toJSON());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public GestionGenericaGimnasio<Miembro> leerListaGenericaMiembros(){
        JSONTokener jsonTokener = OperacionesLecturaEscritura.leerArchivo(nombreArchivo);
        GestionGenericaGimnasio<Miembro> lista = null;

        try {
            lista = JsonObjectToMiembros(new JSONObject(jsonTokener));

        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }

    // convierte el jsonObject a lista
    public GestionGenericaGimnasio<Miembro> JsonObjectToMiembros(JSONObject jsonObject){
        GestionGenericaGimnasio<Miembro> miembros = new GestionGenericaGimnasio<>();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("ListaMiembros");

            for (int i = 0 ; i < jsonArray.length() ; i++){
                Miembro miembro = new Miembro(jsonArray.getJSONObject(i));
                miembros.agregar(miembro.getDocumento(), miembro);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return miembros;
    }

    ///crear miembro
    public Miembro crearMiembro() {
        Miembro miembro = new Miembro();
        Membresia membresia = new Membresia();  // Crear instancia de Membresia
        Scanner entrada = new Scanner(System.in);

        // Solicitar datos del miembro
        System.out.println("Ingrese el nombre del miembro:");
        miembro.setNombre(entrada.nextLine());
        System.out.println("Ingrese apellido del miembro:");
        miembro.setApellido(entrada.nextLine());
        System.out.println("Ingrese documento del miembro:");
        miembro.setDocumento(entrada.nextLine());
        System.out.println("Ingrese fecha de nacimiento (YYYY-MM-DD):");
        miembro.setFechaNacimiento(LocalDate.parse(entrada.nextLine()));
        System.out.println("ingrese su fecha de inscripcion (YYYY-MM-DD):");
        miembro.setFechaIncripcion(LocalDate.parse(entrada.nextLine()));

        // Selección de membresía
        System.out.println("Seleccione su membresía:");
        eTipoMembresia[] membresias = eTipoMembresia.values();
        for (int i = 0; i < membresias.length; i++) {
            System.out.println((i + 1) + ". " + membresias[i].name());
        }

        int opcionMembresia = entrada.nextInt();
        while (opcionMembresia < 1 || opcionMembresia > membresias.length) {
            System.out.println("Opción no válida. Por favor seleccione una opción válida.");
            opcionMembresia = entrada.nextInt();
        }

        // Asignar el valor de membresía al objeto Membresia
        membresia.setTipomembresia(membresias[opcionMembresia - 1]); // Asignamos el tipo de membresía

        // Asignamos el costo mensual dependiendo del tipo de membresía
        switch (membresias[opcionMembresia - 1]) {
            case membresiaBasica:
                membresia.setCostoMensual(300);
                membresia.setDescripcion("Membresia de 3 dias a la semana con profesor personal");// Costo mensual para membresía anual
                break;
            case membresiaPremium:
                membresia.setCostoMensual(700);
                membresia.setDescripcion("Membresia libre con profesor personal y baños con ducha");// Costo mensual para membresía semestral
                break;
            default:
                System.out.println("Opción de membresía no válida.");
                break;
        }

        // Asignamos la membresía al miembro
        miembro.setMembresia(membresia);


        return miembro;
    }


    public void modificarMiembro(Miembro miembro) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        boolean salir = false;

        while (!salir) {
            System.out.println("\nSeleccione el dato que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Membresía");
            System.out.println("4. Salir");

            try {
                System.out.print("Ingrese la opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        // Modificar nombre
                        System.out.println("Nombre actual: " + miembro.getNombre());
                        System.out.print("Ingrese el nuevo nombre: ");
                        miembro.setNombre(scanner.nextLine());
                        System.out.println("Nombre actualizado.");
                        break;

                    case 2:
                        // Modificar apellido
                        System.out.println("Apellido actual: " + miembro.getApellido());
                        System.out.print("Ingrese el nuevo apellido: ");
                        miembro.setApellido(scanner.nextLine());
                        System.out.println("Apellido actualizado.");
                        break;

                    case 3:
                        // Modificar membresía
                        System.out.println("Membresía actual: " + miembro.getMembresia());
                        System.out.println("Seleccione una nueva membresía:");

                        GestorJsonMembresias gestorJsonMembresia = new GestorJsonMembresias();
                        ArrayList<Membresia> membresias = gestorJsonMembresia.leerListaMembresia();

                        for (int i = 0; i < membresias.size(); i++) {
                            System.out.println((i + 1) + ". " + membresias.get(i));
                        }

                        System.out.print("Ingrese el número de membresía: ");
                        int seleccion = Integer.parseInt(scanner.nextLine());

                        if (seleccion > 0 && seleccion <= membresias.size()) {
                            miembro.setMembresia(membresias.get(seleccion - 1));
                            System.out.println("Membresía actualizada.");
                        } else {
                            System.out.println("Opción inválida.");
                        }
                        break;

                    case 4:
                        // Salir
                        salir = true;
                        System.out.println("Modificación finalizada.");
                        break;

                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
    }


}

