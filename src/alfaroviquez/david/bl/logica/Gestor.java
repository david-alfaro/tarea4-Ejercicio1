package alfaroviquez.david.bl.logica;

import alfaroviquez.david.bl.entidades.*;
import alfaroviquez.david.bl.tipos.formatoAudio;
import alfaroviquez.david.bl.tipos.formatoVideo;
import alfaroviquez.david.bl.tipos.tipoContrato;
import alfaroviquez.david.bl.tipos.tipoNombramiento;
import alfaroviquez.david.persistencia.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Gestor {

    private Connection connection;
    private String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private String USER = "root";
    private String PSW = "12345";

    private EstudianteDAO estudianteDAO;
    private ProfesorDAO profesorDAO;
    private AdministrativoDAO adminDAO;

    private AudioDAO audioDAO;
    private TextoDAO textoDAO;
    private VideoDAO videoDAO;
    private OtroDAO otroDAO;

    private PrestamoDAO prestamoDAO;

    private ArrayList<Persona> listaPersonas = new ArrayList<>();
    private ArrayList<Material> listaMateriales = new ArrayList<>();

    public Gestor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(URL, USER, PSW);
            this.estudianteDAO = new EstudianteDAO(this.connection);
            this.profesorDAO = new ProfesorDAO(this.connection);
            this.adminDAO = new AdministrativoDAO(this.connection);
            this.audioDAO = new AudioDAO(this.connection);
            this.textoDAO = new TextoDAO(this.connection);
            this.videoDAO = new VideoDAO(this.connection);
            this.otroDAO = new OtroDAO(this.connection);
            this.prestamoDAO = new PrestamoDAO(this.connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se puede conectar a la base de datos");
        }
    }

    public void crearEstudiante(String nombre, String apellido, String ID, String carrera, int creditos) {
        Estudiante nuevoEstudiante = new Estudiante(nombre, apellido, ID, carrera, creditos);
        try {
            estudianteDAO.guardar(nuevoEstudiante);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaPersonas.add(nuevoEstudiante);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteDAO.obtenerEstudiantes();
    }

    public void crearProfesor(String nombre, String apellido, String ID, Date fechaContratacion, String tipocontrato) {
        tipoContrato contrato = tipoContrato.valueOf(tipocontrato);
        Profesor nuevoProfesor = new Profesor(nombre, apellido, ID, fechaContratacion, contrato);
        try {
            profesorDAO.guardar(nuevoProfesor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaPersonas.add(nuevoProfesor);
    }

    public List<Profesor> listarProfesores() {

        return profesorDAO.obtenerProfesores();
    }

    public void crearAdministrativo(String nombre, String apellido, String ID, String nombramiento, int horasAsignadas) {
        tipoNombramiento tipoNombramiento = alfaroviquez.david.bl.tipos.tipoNombramiento.valueOf(nombramiento);
        Administrativo nuevoAdministrativo = new Administrativo(nombre, apellido, ID, tipoNombramiento, horasAsignadas);
        try {
            adminDAO.guardar(nuevoAdministrativo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaPersonas.add(nuevoAdministrativo);
    }

    public List<Administrativo> listarAdministrativos() {
        return adminDAO.obtenerAdministrativos();
    }

    public void crearMaterialTexto(int signatura, Boolean restringido, String tema, Date fechaCompra, String titulo, String nombreAutor, Date fechaPublicacion, int numeroPaginas, String idioma) {
        Texto nuevoTexto = new Texto(signatura, restringido, tema, fechaCompra, titulo, nombreAutor, fechaPublicacion, numeroPaginas, idioma);
        try {
            textoDAO.guardar(nuevoTexto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaMateriales.add(nuevoTexto);
    }

    public List<Texto> listarTextos() {
        return textoDAO.obtenerTextos();
    }

    public void crearMaterialAudio(int signatura, Boolean restringido, String tema, Date fechaCompra, String formato, int duracion, String idioma) {
        formatoAudio tipoFormato = formatoAudio.valueOf(formato);
        Audio nuevoAudio = new Audio(signatura, restringido, tema, fechaCompra, tipoFormato, duracion, idioma);
        try {
            audioDAO.guardar(nuevoAudio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaMateriales.add(nuevoAudio);
    }

    public List<Audio> listarAudios() {
        return audioDAO.obtenerAudios();
    }

    public void crearMaterialVideo(int signatura, Boolean restringido, String tema, Date fechaCompra, String formato, int duracion, String idioma, String director) {
        formatoVideo tipoFormato = formatoVideo.valueOf(formato);
        Video nuevoVideo = new Video(signatura, restringido, tema, fechaCompra, tipoFormato, duracion, idioma, director);
        try {
            videoDAO.guardar(nuevoVideo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaMateriales.add(nuevoVideo);
    }

    public List<Video> listarVideos() {

        return videoDAO.obtenerVideos();
    }

    public void crearOtroMaterial(int signatura, Boolean restringido, String tema, Date fechaCompra, String descripcion) {
        OtroMaterial nuevoOtro = new OtroMaterial(signatura, restringido, tema, fechaCompra, descripcion);
        try {
            otroDAO.guardar(nuevoOtro);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaMateriales.add(nuevoOtro);

    }

    public List<OtroMaterial> listarOtros() {
        return otroDAO.obtenerOtros();
    }

    public void registrarPrestamo(Persona per, Material mat, Date fechaDevol, Date fechaPrestamo) {
        Prestamo nuevoPrestamo = new Prestamo(per,mat,fechaDevol,fechaPrestamo);
        try {
            prestamoDAO.guardarPrestamo(nuevoPrestamo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Persona buscarPersonaPorID(String id) {
        for (Persona unaPersona : this.listaPersonas) {
            if (unaPersona.getID().equals(id)) {
                return unaPersona;
            }
        }
        return null;
    }

    public Material buscarMaterialPorSignatura(int signatura) {
        for (Material unMaterial : this.listaMateriales
        ) {
            if (unMaterial.getSignatura() == signatura) {
                return unMaterial;
            }
        }
        return null;
    }


}
