import Clases.*;
import Clases.Gestoras.*;
import Enums.eEspecialidad;
import Enums.eTipoMaquina;
import Enums.eTipoMembresia;


import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase para cargar datos al gimnasio
 * se utilizo para inicializar todos los datos y son grabados en el archivo
 *
 * @version 1
 */

public class Data {

    public static void cargarDatos(){

        Gimnasio gimnasio = new Gimnasio();

        //Cargar Datos gimnasio
        gimnasio.setNombreGimnasio("GimnasioUTN");
        gimnasio.setCapacidadGimnasio(50);
        gimnasio.setDireccionGimnasio("Av. Dorrego 281");


        //Creacion instancias Recepcionistas
        Recepcionista recepcionista1 = new Recepcionista("Carlos", "Pérez", "32435456", LocalDate.of(1990, 5, 15), 5700, "08:00 a 12:00", "Carlos912", "123456789");
        Recepcionista recepcionista2 = new Recepcionista("Ana", "González", "30452389", LocalDate.of(1985, 12, 5), 4500, "12:00 a 16:00", "AnaGonzalez9", "1205");
        Recepcionista recepcionista3 = new Recepcionista("Luis", "Martínez", "40235326", LocalDate.of(2000, 7, 22), 3900, "16:00 a 20:00", "LuisMartinez10", "2207");

        // cargo los recepcionistas
        gimnasio.getGestionRecepcionistas().agregar(recepcionista1.getDocumento(), recepcionista1);
        gimnasio.getGestionRecepcionistas().agregar(recepcionista2.getDocumento(), recepcionista2);
        gimnasio.getGestionRecepcionistas().agregar(recepcionista3.getDocumento(), recepcionista3);

        GestorJsonRecepcionistas gestorJsonRecepcionistas = new GestorJsonRecepcionistas();
        gestorJsonRecepcionistas.grabar(gimnasio.getGestionRecepcionistas());


        //Creacion instancias Especialidades
        Especialidad especialidadFuncional = new Especialidad("Entrenamiento funcional", eEspecialidad.FUNCIONAL);
        Especialidad especialidadMusculacion = new Especialidad("Entrenamiento de musculación", eEspecialidad.MUSCULACICION);
        Especialidad especialidadBoxeo = new Especialidad("Entrenamiento de boxeo", eEspecialidad.BOXEO);

        gimnasio.agregarEspecialidad(especialidadFuncional);
        gimnasio.agregarEspecialidad(especialidadMusculacion);
        gimnasio.agregarEspecialidad(especialidadBoxeo);

        GestorJsonGimnasio gestorJsonGimnasio = new GestorJsonGimnasio();
        gestorJsonGimnasio.grabar(gimnasio);


        //Creacion instancias Entrenadores
        Entrenador entrenador1 = new Entrenador("Julian", "Sánchez", "30213214", LocalDate.of(1985, 3, 25), 5200, "09:00 - 17:00", especialidadFuncional.getEspecialidad());
        Entrenador entrenador2 = new Entrenador("Valeria", "López", "40342664", LocalDate.of(2000, 8, 14), 4600, "10:00 - 18:00", especialidadMusculacion.getEspecialidad());
        Entrenador entrenador3 = new Entrenador("Ricardo", "Martín", "36343267", LocalDate.of(1994, 2, 10), 4800, "08:00 - 16:00", especialidadMusculacion.getEspecialidad());
        Entrenador entrenador4 = new Entrenador("Eduardo", "Ramírez", "42324568", LocalDate.of(2002, 4, 12), 5000, "07:00 - 15:00", especialidadBoxeo.getEspecialidad());

        // Cargo entrenadores al gimnasio
        gimnasio.getGestionEntrenadores().agregar(entrenador1.getDocumento(), entrenador1);
        gimnasio.getGestionEntrenadores().agregar(entrenador2.getDocumento(), entrenador2);
        gimnasio.getGestionEntrenadores().agregar(entrenador3.getDocumento(), entrenador3);
        gimnasio.getGestionEntrenadores().agregar(entrenador4.getDocumento(), entrenador4);

        GestorJsonEntrenadores gestorJsonEntrenadores = new GestorJsonEntrenadores();
        gestorJsonEntrenadores.grabar(gimnasio.getGestionEntrenadores());


        //Creacion instancias Tipos de membresia
        Membresia membresiaBasica = new Membresia("Membresía básica con acceso limitado", eTipoMembresia.MEMBRESIA_BASICA, 1500);
        Membresia membresiaPremium = new Membresia("Membresía Premium con acceso completo", eTipoMembresia.MEMBRESIA_PREMIUM, 3000);

        // cargo las membresias
        ArrayList<Membresia> membresias = new ArrayList<>();
        membresias.add(membresiaBasica);
        membresias.add(membresiaPremium);

        GestorJsonMembresias gestorJsonMembresia = new GestorJsonMembresias();
        gestorJsonMembresia.grabar(membresias);


        // Instancias de Miembro
        Miembro miembro1 = new Miembro("Carlos", "Lopez", "37654321", LocalDate.of(1995, 1, 10), membresiaBasica, true, LocalDate.of(2023, 1, 1), LocalDate.of(2024, 10, 26));
        Miembro miembro2 = new Miembro("Ana", "Martinez", "49123456", LocalDate.of(2009, 2, 15), membresiaPremium, false, LocalDate.of(2023, 2, 15), LocalDate.of(2024, 2, 8));
        Miembro miembro3 = new Miembro("Luis", "Garcia", "30876543", LocalDate.of(1985, 3, 20), membresiaBasica, true, LocalDate.of(2023, 3, 5), LocalDate.of(2024, 8, 9));
        Miembro miembro4 = new Miembro("Lucia", "Fernandez", "42987654", LocalDate.of(2000, 4, 25), membresiaBasica, true, LocalDate.of(2023, 4, 10), LocalDate.of(2024, 11, 24));
        Miembro miembro5 = new Miembro("Miguel", "Diaz", "31543210", LocalDate.of(1989, 5, 30), membresiaBasica, false, LocalDate.of(2023, 5, 20), LocalDate.of(2024, 10, 20));
        Miembro miembro6 = new Miembro("Sofia", "Sanchez", "48765432", LocalDate.of(2008, 6, 5), membresiaBasica, true, LocalDate.of(2023, 6, 15), LocalDate.of(2024, 10, 24));
        Miembro miembro7 = new Miembro("Juan", "Ruiz", "37612345", LocalDate.of(1996, 7, 10), membresiaPremium, false, LocalDate.of(2023, 7, 5), LocalDate.of(2024, 10, 18));
        Miembro miembro8 = new Miembro("Elena", "Molina", "40987654", LocalDate.of(1999, 8, 15), membresiaPremium, true, LocalDate.of(2023, 8, 25), LocalDate.of(2024, 10, 14));
        Miembro miembro9 = new Miembro("Fernando", "Romero", "44101234", LocalDate.of(2004, 9, 20), membresiaBasica, true, LocalDate.of(2023, 9, 30), LocalDate.of(2024, 10, 16));
        Miembro miembro10 = new Miembro("Patricia", "Herrera", "32109876", LocalDate.of(1991, 10, 25), membresiaPremium, false, LocalDate.of(2023, 10, 15), LocalDate.of(2024, 11, 14));
        Miembro miembro11 = new Miembro("Valeria", "Gomez", "35098765", LocalDate.of(1994, 11, 10), membresiaBasica, false, LocalDate.of(2023, 11, 1), LocalDate.of(2024, 9, 30));
        Miembro miembro12 = new Miembro("Carlos", "Perez", "41987654", LocalDate.of(2000, 12, 20), membresiaPremium, true, LocalDate.of(2023, 12, 5), LocalDate.of(2024, 11, 1));
        Miembro miembro13 = new Miembro("Santiago", "Lopez", "30876512", LocalDate.of(1990, 5, 12), membresiaBasica, true, LocalDate.of(2023, 5, 15), LocalDate.of(2024, 6, 10));
        Miembro miembro14 = new Miembro("Camila", "Alvarez", "45012345", LocalDate.of(2004, 7, 22), membresiaPremium, false, LocalDate.of(2023, 7, 20), LocalDate.of(2024, 8, 18));
        Miembro miembro15 = new Miembro("Mateo", "Torres", "39098712", LocalDate.of(1999, 3, 19), membresiaBasica, true, LocalDate.of(2023, 3, 10), LocalDate.of(2024, 5, 15));

        // cargo los miembros
        gimnasio.getGestionMiembros().agregar(miembro1.getDocumento(), miembro1);
        gimnasio.getGestionMiembros().agregar(miembro2.getDocumento(), miembro2);
        gimnasio.getGestionMiembros().agregar(miembro3.getDocumento(), miembro3);
        gimnasio.getGestionMiembros().agregar(miembro4.getDocumento(), miembro4);
        gimnasio.getGestionMiembros().agregar(miembro5.getDocumento(), miembro5);
        gimnasio.getGestionMiembros().agregar(miembro6.getDocumento(), miembro6);
        gimnasio.getGestionMiembros().agregar(miembro7.getDocumento(), miembro7);
        gimnasio.getGestionMiembros().agregar(miembro8.getDocumento(), miembro8);
        gimnasio.getGestionMiembros().agregar(miembro9.getDocumento(), miembro9);
        gimnasio.getGestionMiembros().agregar(miembro10.getDocumento(), miembro10);
        gimnasio.getGestionMiembros().agregar(miembro11.getDocumento(), miembro11);
        gimnasio.getGestionMiembros().agregar(miembro12.getDocumento(), miembro12);
        gimnasio.getGestionMiembros().agregar(miembro13.getDocumento(), miembro13);
        gimnasio.getGestionMiembros().agregar(miembro14.getDocumento(), miembro14);
        gimnasio.getGestionMiembros().agregar(miembro15.getDocumento(), miembro15);

        GestorJsonMiembros gestorJsonMiembros = new GestorJsonMiembros();
        gestorJsonMiembros.grabar(gimnasio.getGestionMiembros());


        // creacion de maquinas

        // Máquinas de pecho
        Maquina maquina1 = new Maquina("press banca", eTipoMaquina.PECHO, true);
        Maquina maquina2 = new Maquina("press inclinado", eTipoMaquina.PECHO, true);
        Maquina maquina3 = new Maquina("máquina de pectorales", eTipoMaquina.PECHO, true);
        Maquina maquina4 = new Maquina("press declinado", eTipoMaquina.PECHO, true);

        // Máquinas de brazo
        Maquina maquina5 = new Maquina("máquina de bíceps", eTipoMaquina.BRAZO, true);
        Maquina maquina6 = new Maquina("máquina de tríceps", eTipoMaquina.BRAZO, true);
        Maquina maquina7 = new Maquina("cuerda para tríceps", eTipoMaquina.BRAZO, true);
        Maquina maquina8 = new Maquina("polea para bíceps", eTipoMaquina.BRAZO, true);

        // Máquinas de pierna
        Maquina maquina9 = new Maquina("extensiones de cuádriceps", eTipoMaquina.PIERNA, true);
        Maquina maquina10 = new Maquina("prensa inclinada", eTipoMaquina.PIERNA, true);
        Maquina maquina11 = new Maquina("máquina de abductores", eTipoMaquina.PIERNA, true);
        Maquina maquina12 = new Maquina("máquina de glúteos", eTipoMaquina.PIERNA, true);

        // Máquinas de espalda
        Maquina maquina13 = new Maquina("remo sentado", eTipoMaquina.ESPALDA, true);
        Maquina maquina14 = new Maquina("remo horizontal", eTipoMaquina.ESPALDA, false);
        Maquina maquina15 = new Maquina("polea alta", eTipoMaquina.ESPALDA, true);
        Maquina maquina16 = new Maquina("dominadas asistidas", eTipoMaquina.ESPALDA, true);

        // Máquinas de cardio
        Maquina maquina17 = new Maquina("cinta para correr", eTipoMaquina.CARDIO, false);
        Maquina maquina18 = new Maquina("bicicleta estática", eTipoMaquina.CARDIO, true);
        Maquina maquina19 = new Maquina("bicicleta elíptica", eTipoMaquina.CARDIO, true);
        Maquina maquina20 = new Maquina("escaladora", eTipoMaquina.CARDIO, true);

        //cargo Maquinas
        gimnasio.getGestionMaquinas().agregar(maquina1.getId(), maquina1);
        gimnasio.getGestionMaquinas().agregar(maquina2.getId(), maquina2);
        gimnasio.getGestionMaquinas().agregar(maquina3.getId(), maquina3);
        gimnasio.getGestionMaquinas().agregar(maquina4.getId(), maquina4);
        gimnasio.getGestionMaquinas().agregar(maquina5.getId(), maquina5);
        gimnasio.getGestionMaquinas().agregar(maquina6.getId(), maquina6);
        gimnasio.getGestionMaquinas().agregar(maquina7.getId(), maquina7);
        gimnasio.getGestionMaquinas().agregar(maquina8.getId(), maquina8);
        gimnasio.getGestionMaquinas().agregar(maquina9.getId(), maquina9);
        gimnasio.getGestionMaquinas().agregar(maquina10.getId(), maquina10);
        gimnasio.getGestionMaquinas().agregar(maquina11.getId(), maquina11);
        gimnasio.getGestionMaquinas().agregar(maquina12.getId(), maquina12);
        gimnasio.getGestionMaquinas().agregar(maquina13.getId(), maquina13);
        gimnasio.getGestionMaquinas().agregar(maquina14.getId(), maquina14);
        gimnasio.getGestionMaquinas().agregar(maquina15.getId(), maquina15);
        gimnasio.getGestionMaquinas().agregar(maquina16.getId(), maquina16);
        gimnasio.getGestionMaquinas().agregar(maquina16.getId(), maquina16);
        gimnasio.getGestionMaquinas().agregar(maquina17.getId(), maquina17);
        gimnasio.getGestionMaquinas().agregar(maquina18.getId(), maquina18);
        gimnasio.getGestionMaquinas().agregar(maquina19.getId(), maquina19);
        gimnasio.getGestionMaquinas().agregar(maquina20.getId(), maquina20);

        GestorJsonMaquinas gestorJsonMaquina = new GestorJsonMaquinas();
        gestorJsonMaquina.grabar(gimnasio.getGestionMaquinas());


        //Creacion instancias Personal de Mantenimiento
        PersonalMantenimiento mantenimiento1 = new PersonalMantenimiento("Juan", "Gómez", "27324689", LocalDate.of(1980, 3, 14), 2500, "08:00 - 16:00");
        PersonalMantenimiento mantenimiento2 = new PersonalMantenimiento("Laura", "Martínez", "30564278", LocalDate.of(1985, 7, 22), 2700, "10:00 - 18:00");

        // cargo el personal de mantenimiento
        gimnasio.getGestionPersonalMantenimiento().agregar(mantenimiento1.getDocumento(), mantenimiento1);
        gimnasio.getGestionPersonalMantenimiento().agregar(mantenimiento2.getDocumento(), mantenimiento2);

        GestorJsonPersonalMantenimiento gestorJsonPersonalMantenimiento = new GestorJsonPersonalMantenimiento();
        gestorJsonPersonalMantenimiento.grabar(gimnasio.getGestionPersonalMantenimiento());

        Reporte reporte1 = new Reporte("se rompio la cinta para correr", "17");
        Reporte reporte2 = new Reporte("se corto la polea", "14");

        GestionGenericaGimnasio<Reporte> reportes = new GestionGenericaGimnasio<>();
        reportes.agregar(reporte1.getDescripcion(), reporte1);
        reportes.agregar(reporte2.getDescripcion(), reporte2);

        GestorJsonReporte gestorJsonReporte = new GestorJsonReporte();
        gestorJsonReporte.grabar(reportes);

    }

}
