package Clases.ManejoArchivos;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta clase tiene el trabajo de contener metodos para escritura y lectura de archivos
 */
public class OperacionesLecturaEscritura {

    public OperacionesLecturaEscritura() {
    }

    /**
     * Metodo para escribir un archivo
     * @param nombreArchivo;
     * @param jsonObject que se va a escribir;
     */
    public static void escribirArchivo(String nombreArchivo, JSONObject jsonObject) {
        try {
            FileWriter fileWriter = new FileWriter(nombreArchivo);
            fileWriter.write(jsonObject.toString(4));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para leer un archivo
     * @param nombreArchivo;
     * @return JSONTokener;
     */
    public static JSONTokener leerArchivo(String nombreArchivo) {
        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new FileReader(nombreArchivo));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonTokener;
    }

}