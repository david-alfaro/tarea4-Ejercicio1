package alfaroviquez.david.controlador;

import alfaroviquez.david.bl.entidades.*;
import alfaroviquez.david.bl.logica.Gestor;
import alfaroviquez.david.iu.IU;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class Controlador {
    private IU interfaz = new IU();
    private Gestor gestor = new Gestor();

    public void ejecutarPrograma() {
        int opcion = 0;
        do {
            interfaz.imprimirMenu();
            opcion = interfaz.leerNumero();
            procesarOpcion(opcion);
        } while (opcion != 6);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                int opcion2 = 0;
                do {
                    interfaz.imprimirMenu2();
                    opcion2 = interfaz.leerNumero();
                    procesarOpcion2(opcion2);
                } while (opcion2 != 4);
                break;
            case 2:
                int opcion3 = 0;
                do {
                    interfaz.imprimirMenu3();
                    opcion3 = interfaz.leerNumero();
                    procesarOpcion3(opcion3);
                } while (opcion3 != 5);
                break;
            case 3:
                interfaz.imprimirMensaje("*** Lista de Estudiantes ***");
                listarEstudiantes();
                interfaz.imprimirMensaje("*** Lista de Profesores ***");
                listarProfesores();
                interfaz.imprimirMensaje("*** Lista de Personal Administrativo ***");
                listarAdministrativos();
                break;
            case 4:
                interfaz.imprimirMensaje("*** Lista de Materiales de Texto ***");
                listarTextos();
                interfaz.imprimirMensaje("*** Lista Materiales de Audio ***");
                listarAudios();
                interfaz.imprimirMensaje("*** Lista de Materiales de Video ***");
                listarVideos();
                interfaz.imprimirMensaje("*** Otros ***");
                listarOtroMaterial();
                break;
            case 5:
                crearPrestamo();
                break;
            case 6:
                break;
            default:
                interfaz.imprimirMensaje("Opcion invalida");
        }
    }

    private void procesarOpcion3(int opcion3) {
        switch (opcion3) {
            case 1:
               registrarTexto();
                break;
            case 2:
                registrarAudio();
                break;
            case 3:
                registrarVideo();
                break;
            case 4:
                registrarOtroMaterial();
                break;
            case 5:
                break;
            default:
                interfaz.imprimirMensaje("Opcion invalida");
        }
    }

    private void procesarOpcion2(int opcion2) {
        switch (opcion2) {
            case 1:
                registrarEstudiante();
                break;
            case 2:
                registrarProfesor();
                break;
            case 3:
                registrarAdministrativo();
                break;
            case 4:
                break;
            default:
                interfaz.imprimirMensaje("Opcion invalida");
        }
    }

    private void registrarEstudiante() {
        interfaz.imprimirMensaje("REGISTRAR ESTUDIANTE");
        interfaz.imprimirMensaje("Nombre del estudiante: ");
        String nombre = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Apellido del estudiante: ");
        String apellido = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Carnet: ");
        String carne = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Carrera matriculada: ");
        String carrera = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Creditos matriculados: ");
        int creditos = interfaz.leerNumero();
        gestor.crearEstudiante(nombre, apellido, carne, carrera, creditos);
        interfaz.imprimirMensaje("Estudiante registrado con exito");
    }

    private void listarEstudiantes() {
        for (Persona unEstudiante : gestor.listarEstudiantes()
        ) {
            interfaz.imprimirMensaje(unEstudiante.toCSVLine());

        }
    }

    private void registrarProfesor() {
        interfaz.imprimirMensaje("REGISTRAR PROFESOR");
        interfaz.imprimirMensaje("Nombre del profesor: ");
        String nombre = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Apellido del profesor: ");
        String apellido = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Cedula (ID): ");
        String ID = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Fecha de contratacion (yyyy-MM-dd): ");
        String fecha = interfaz.leerMensaje();
        LocalDate fechaContratacion = obtenerFecha(fecha);
        interfaz.imprimirMensaje("Tipo de contrato (TIEMPO_COMPLETO, MEDIO_TIEMPO): ");
        String contrato = interfaz.leerMensaje();
        gestor.crearProfesor(nombre, apellido, ID, Date.valueOf(fechaContratacion), contrato);
        interfaz.imprimirMensaje("Profesor registrado con exito");
    }

    private void listarProfesores() {
        for (Persona unProfesor : gestor.listarProfesores()
        ) {
            interfaz.imprimirMensaje(unProfesor.toCSVLine());
        }
    }

    private void registrarAdministrativo() {
        interfaz.imprimirMensaje("REGISTRAR Administrativo");
        interfaz.imprimirMensaje("Nombre: ");
        String nombre = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Apellido: ");
        String apellido = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Cedula (ID): ");
        String ID = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Tipo de nombramiento (A,B,C): ");
        String tipoNombramiento = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Horas semanales asignadas: ");
        int cantHoras = interfaz.leerNumero();
        gestor.crearAdministrativo(nombre, apellido, ID, tipoNombramiento, cantHoras);
        interfaz.imprimirMensaje("Persona Administrativa registrada con exito");

    }

    private void listarAdministrativos() {
        for (Persona unAdministrarivo : gestor.listarAdministrativos()
        ) {
            interfaz.imprimirMensaje(unAdministrarivo.toCSVLine());
        }
    }

    private void registrarTexto() {
        interfaz.imprimirMensaje("REGISTRAR MATERIAL DE TEXTO");
        interfaz.imprimirMensaje("Ingrese la signatura (ID): ");
        int signatura = interfaz.leerNumero();
        interfaz.imprimirMensaje("Material restringido ? (y/n): ");
        String respuesta = interfaz.leerMensaje();
        boolean restringido = false;
        if (respuesta.equalsIgnoreCase("y")) {
            restringido = true;
        } else {
            restringido = false;
        }
        interfaz.imprimirMensaje("Tema : ");
        String tema = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Fecha de compra (yyyy-MM-dd): ");
        String fecha = interfaz.leerMensaje();
        LocalDate fechaCompra = obtenerFecha(fecha);
        interfaz.imprimirMensaje("Titulo del texto: ");
        String titulo = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Nombre del autor: ");
        String autor = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Idioma: ");
        String idioma = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Fecha de publicacion (yyyy-MM-dd): ");
        String publicacion = interfaz.leerMensaje();
        LocalDate fechapublicacion = obtenerFecha(publicacion);
        interfaz.imprimirMensaje("Cantidad de paginas: ");
        int paginas = interfaz.leerNumero();
        gestor.crearMaterialTexto(signatura, restringido, tema, Date.valueOf(fechaCompra), titulo, autor, Date.valueOf(fechapublicacion), paginas, idioma);
        interfaz.imprimirMensaje("Material registrado con exito");
    }

    private void listarTextos() {
        for (Material unTexto : gestor.listarTextos()
        ) {
            interfaz.imprimirMensaje(unTexto.toCSVLine());
        }
    }

    private void registrarAudio() {
        interfaz.imprimirMensaje("REGISTRAR MATERIAL DE AUDIO");
        interfaz.imprimirMensaje("Ingrese la signatura (ID): ");
        int signatura = interfaz.leerNumero();
        interfaz.imprimirMensaje("Material restringido ? (y/n): ");
        String respuesta = interfaz.leerMensaje();
        boolean restringido = false;
        if (respuesta.equalsIgnoreCase("y")) {
            restringido = true;
        } else {
            restringido = false;
        }
        interfaz.imprimirMensaje("Tema : ");
        String tema = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Fecha de compra (yyyy-MM-dd): ");
        String fecha = interfaz.leerMensaje();
        LocalDate fechaCompra = obtenerFecha(fecha);
        interfaz.imprimirMensaje("Idioma: ");
        String idioma = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Duracion: ");
        int duracion = interfaz.leerNumero();
        interfaz.imprimirMensaje("Formato (CASETE,CD,DVD): ");
        String formato = interfaz.leerMensaje();
        gestor.crearMaterialAudio(signatura, restringido, tema, Date.valueOf(fechaCompra), formato, duracion, idioma);
        interfaz.imprimirMensaje("Material registrado con exito");
    }

    private void listarAudios() {
        for (Material unAudio : gestor.listarAudios()
        ) {
            interfaz.imprimirMensaje(unAudio.toCSVLine());
        }
    }

    private void registrarVideo() {
        interfaz.imprimirMensaje("REGISTRAR MATERIAL DE VIDEO");
        interfaz.imprimirMensaje("Ingrese la signatura (ID): ");
        int signatura = interfaz.leerNumero();
        interfaz.imprimirMensaje("Material restringido ? (y/n): ");
        String respuesta = interfaz.leerMensaje();
        boolean restringido = false;
        if (respuesta.equalsIgnoreCase("y")) {
            restringido = true;
        } else {
            restringido = false;
        }
        interfaz.imprimirMensaje("Tema : ");
        String tema = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Fecha de compra (yyyy-MM-dd): ");
        String fecha = interfaz.leerMensaje();
        LocalDate fechaCompra = obtenerFecha(fecha);
        interfaz.imprimirMensaje("Idioma: ");
        String idioma = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Duracion: ");
        int duracion = interfaz.leerNumero();
        interfaz.imprimirMensaje("Formato (VHS,VCD,DVD): ");
        String formato = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Nombre del director: ");
        String director = interfaz.leerMensaje();
        gestor.crearMaterialVideo(signatura, restringido, tema, Date.valueOf(fechaCompra), formato, duracion, idioma, director);
        interfaz.imprimirMensaje("Material registrado exitosamente");
    }

    private void listarVideos() {
        for (Material unvideo : gestor.listarVideos()
        ) {
            interfaz.imprimirMensaje(unvideo.toCSVLine());
        }
    }

    private void registrarOtroMaterial() {
        interfaz.imprimirMensaje("REGISTRAR OTRO TIPO DE MATERIAL");
        interfaz.imprimirMensaje("Ingrese la signatura (ID): ");
        int signatura = interfaz.leerNumero();
        interfaz.imprimirMensaje("Material restringido ? (y/n): ");
        String respuesta = interfaz.leerMensaje();
        boolean restringido = false;
        if (respuesta.equalsIgnoreCase("y")) {
            restringido = true;
        } else {
            restringido = false;
        }
        interfaz.imprimirMensaje("Tema : ");
        String tema = interfaz.leerMensaje();
        interfaz.imprimirMensaje("Fecha de compra (yyyy-MM-dd): ");
        String fecha = interfaz.leerMensaje();
        LocalDate fechaCompra = obtenerFecha(fecha);
        //Date fechaC = convertTOLocalDateViaSqlDate(fechaCompra);
        interfaz.imprimirMensaje("Descripcion: ");
        String descripcion = interfaz.leerMensaje();
        gestor.crearOtroMaterial(signatura, restringido, tema, Date.valueOf(fechaCompra), descripcion);
        interfaz.imprimirMensaje("Material registrado exitosamente");
    }

    private void listarOtroMaterial() {
        for (Material unMaterial : gestor.listarOtros()
        ) {
            interfaz.imprimirMensaje(unMaterial.toCSVLine());
        }
    }

    private void crearPrestamo() {
        interfaz.imprimirMensaje("RESGISTRAR PRESTAMO");
        interfaz.imprimirMensaje("Lista de Estudiantes");
        listarEstudiantes();
        interfaz.imprimirMensaje("Lista de Profesores");
        listarProfesores();
        interfaz.imprimirMensaje("Lista de Administrativos");
        listarAdministrativos();
        interfaz.imprimirMensaje("---------------------------------");
        interfaz.imprimirMensaje("Ingrese el ID de la persona: ");
        String id = interfaz.leerMensaje();
        Persona persona = gestor.buscarPersonaPorID(id);
        interfaz.imprimirMensaje("*** LISTA DE MATERIALES ***");
        interfaz.imprimirMensaje("*** Lista de Materiales de Texto ***");
        listarTextos();
        interfaz.imprimirMensaje("*** Lista Materiales de Audio ***");
        listarAudios();
        interfaz.imprimirMensaje("*** Lista de Materiales de Video ***");
        listarVideos();
        interfaz.imprimirMensaje("*** Otros ***");
        listarOtroMaterial();
        interfaz.imprimirMensaje("---------------------------------");
        interfaz.imprimirMensaje("Ingrese la signatura del material");
        int signatura = interfaz.leerNumero();
        Material material = gestor.buscarMaterialPorSignatura(signatura);
        interfaz.imprimirMensaje("Ingrese la fecha de devolucion (yyyy-MM-dd): ");
        String fecha = interfaz.leerMensaje();
        LocalDate fechaDevolucion = obtenerFecha(fecha);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        String fechaPrestamo = formatter.format(date);
        LocalDate fechaPres = obtenerFecha(fechaPrestamo);
        gestor.registrarPrestamo(persona,material,Date.valueOf(fechaDevolucion),Date.valueOf(fechaPres));
        interfaz.imprimirMensaje("Prestamo registrado");

    }





    private LocalDate obtenerFecha(String fecha) {
        return LocalDate.parse(fecha);
    }

    public java.sql.Date convertTOLocalDateViaSqlDate(java.util.Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime());
    }
}
