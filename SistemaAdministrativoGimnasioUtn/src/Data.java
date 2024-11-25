import Clases.*;
import Clases.Gestoras.*;
import Enums.eEspecialidad;
import Enums.eTipoMaquina;
import Enums.eTipoMembresia;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase para cargar datos al gimnasio
 * tiene que recibirlo por parametro
 *
 * @version 1
 */

public class Data {

    public static void cargarDatos(Gimnasio gimnasio){

        //Cargar Datos gimnasio
        gimnasio.setNombreGimnasio("GimnasioUTN");
        gimnasio.setCapacidadGimnasio(50);
        gimnasio.setDireccionGimnasio("Av. Dorrego 281");

        //Creacion instancias Recepcionistas
        Recepcionista recepcionista1 = new Recepcionista("Carlos", "Pérez", "12345678", LocalDate.of(1990, 5, 15), 5700, "08:00 a 12:00", "Carlos912", "123456789");
        Recepcionista recepcionista2 = new Recepcionista("Ana", "González", "87654321", LocalDate.of(1985, 12, 5), 4500, "12:00 a 16:00", "AnaGonzalez9", "1205");
        Recepcionista recepcionista3 = new Recepcionista("Luis", "Martínez", "11223344", LocalDate.of(2000, 7, 22), 3900, "16:00 a 20:00", "LuisMartinez", "2207");

        //Creacion instancias Especialidades
        Especialidad especialidadFuncional = new Especialidad("Entrenamiento funcional", eEspecialidad.FUNCIONAL);
        Especialidad especialidadMusculacion = new Especialidad("Entrenamiento de musculación", eEspecialidad.MUSCULACICION);
        Especialidad especialidadBoxeo = new Especialidad("Entrenamiento de boxeo", eEspecialidad.BOXEO);

        gimnasio.agregarEspecialidad(especialidadFuncional);
        gimnasio.agregarEspecialidad(especialidadMusculacion);
        gimnasio.agregarEspecialidad(especialidadBoxeo);

        //Creacion instancias Tipos de membresia
        Membresia membresiaBasica = new Membresia("Membresía básica con acceso limitado", eTipoMembresia.MEMBRESIA_BASICA, 1500);
        Membresia membresiaPremium = new Membresia("Membresía Premium con acceso completo", eTipoMembresia.MEMBRESIA_PREMIUM, 3000);

        //Creacion instancias Entrenadores
        Entrenador entrenador1 = new Entrenador("Julian", "Sánchez", "11122333", LocalDate.of(1983, 3, 25), 5200, "09:00 - 17:00", especialidadFuncional.getEspecialidad());
        Entrenador entrenador2 = new Entrenador("Valeria", "López", "44556677", LocalDate.of(1987, 8, 14), 4600, "10:00 - 18:00", especialidadMusculacion.getEspecialidad());
        Entrenador entrenador3 = new Entrenador("Ricardo", "Martín", "88990011", LocalDate.of(1991, 2, 10), 4800, "08:00 - 16:00", especialidadMusculacion.getEspecialidad());
        Entrenador entrenador4 = new Entrenador("Eduardo", "Ramírez", "33445566", LocalDate.of(1982, 4, 12), 5000, "07:00 - 15:00", especialidadBoxeo.getEspecialidad());

        // Instancias de Miembro
        Miembro miembro1 = new Miembro("Carlos", "Lopez", "11111111", LocalDate.of(1995, 1, 10), membresiaBasica, true, LocalDate.of(2023, 1, 1), LocalDate.now());
        Miembro miembro2 = new Miembro("Ana", "Martinez", "22222222", LocalDate.of(1992, 2, 15), membresiaPremium, false, LocalDate.of(2023, 2, 15), LocalDate.of(2024, 2, 8));
        Miembro miembro3 = new Miembro("Luis", "Garcia", "33333333", LocalDate.of(1998, 3, 20), membresiaBasica, true, LocalDate.of(2023, 3, 5), LocalDate.now());
        Miembro miembro4 = new Miembro("Lucia", "Fernandez", "44444444", LocalDate.of(1993, 4, 25), membresiaBasica, true, LocalDate.of(2023, 4, 10), LocalDate.now());
        Miembro miembro5 = new Miembro("Miguel", "Diaz", "55555555", LocalDate.of(1997, 5, 30), membresiaBasica, false, LocalDate.of(2023, 5, 20), LocalDate.now());
        Miembro miembro6 = new Miembro("Sofia", "Sanchez", "66666666", LocalDate.of(1994, 6, 5), membresiaBasica, true, LocalDate.of(2023, 6, 15), LocalDate.now());
        Miembro miembro7 = new Miembro("Juan", "Ruiz", "77777777", LocalDate.of(1996, 7, 10), membresiaPremium, false, LocalDate.of(2023, 7, 5), LocalDate.now());
        Miembro miembro8 = new Miembro("Elena", "Molina", "88888888", LocalDate.of(1999, 8, 15), membresiaPremium, true, LocalDate.of(2023, 8, 25), LocalDate.now());
        Miembro miembro9 = new Miembro("Fernando", "Romero", "99999999", LocalDate.of(1995, 9, 20), membresiaBasica, true, LocalDate.of(2023, 9, 30), LocalDate.now());
        Miembro miembro10 = new Miembro("Patricia", "Herrera", "10101010", LocalDate.of(1991, 10, 25), membresiaPremium, false, LocalDate.of(2023, 10, 15), LocalDate.now());


        // creacion de maquinas
        Maquina maquina1 = new Maquina("dorsalera", eTipoMaquina.ESPALDA,true);
        //cargo Maquinas
        gimnasio.getGestionMaquinas().agregar(maquina1.getNombre(), maquina1);
        GestorJsonMaquinas gestorJsonMaquina = new GestorJsonMaquinas();
        gestorJsonMaquina.grabar(gimnasio.getGestionMaquinas());

        //Creacion instancias Personal de Mantenimiento
        PersonalMantenimiento mantenimiento1 = new PersonalMantenimiento("Juan", "Gómez", "100112233", LocalDate.of(1980, 3, 14), 2500, "08:00 - 16:00");
        PersonalMantenimiento mantenimiento2 = new PersonalMantenimiento("Laura", "Martínez", "223344556", LocalDate.of(1985, 7, 22), 2700, "10:00 - 18:00");

        // Cargo entrenadores al gimnasio
        gimnasio.getGestionEntrenadores().agregar(entrenador1.getDocumento(), entrenador1);
        gimnasio.getGestionEntrenadores().agregar(entrenador2.getDocumento(), entrenador2);

        gimnasio.getGestionMiembros().agregar(miembro1.getDocumento(), miembro1);
        gimnasio.getGestionMiembros().agregar(miembro2.getDocumento(), miembro2);

        // cargo las membresias aca porque no se donde ponerlas
        ArrayList<Membresia> membresias = new ArrayList<>();
        membresias.add(membresiaBasica);
        membresias.add(membresiaPremium);

        GestorJsonMembresias gestorJsonMembresia = new GestorJsonMembresias();
        gestorJsonMembresia.grabar(membresias);

        // cargo el personal de mantenimiento
        gimnasio.getGestionPersonalMantenimiento().agregar(mantenimiento1.getDocumento(), mantenimiento1);
        gimnasio.getGestionPersonalMantenimiento().agregar(mantenimiento2.getDocumento(), mantenimiento2);

        GestorJsonPersonalMantenimiento gestorJsonPersonalMantenimiento = new GestorJsonPersonalMantenimiento();
        gestorJsonPersonalMantenimiento.grabar(gimnasio.getGestionPersonalMantenimiento());

        // cargo las recepcionistas
        gimnasio.getGestionRecepcionistas().agregar(recepcionista1.getDocumento(), recepcionista1);
        GestorJsonRecepcionistas gestorJsonRecepcionistas = new GestorJsonRecepcionistas();
        gestorJsonRecepcionistas.grabar(gimnasio.getGestionRecepcionistas());


    }

}
