package alfaroviquez.david.bl.entidades;

import alfaroviquez.david.bl.tipos.formatoVideo;

import java.sql.Date;
import java.time.LocalDate;

public class Video extends Material {
    private formatoVideo formato;
    private int duracion;
    private String idioma;
    private String director;

    public formatoVideo getFormato() {
        return formato;
    }

    public void setFormato(formatoVideo formato) {
        this.formato = formato;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Video() {
    }

    public Video(int signatura, Boolean restringido, String tema, Date fechaCompra, formatoVideo formato, int duracion, String idioma, String director) {
        super(signatura, restringido, tema, fechaCompra);
        this.formato = formato;
        this.duracion = duracion;
        this.idioma = idioma;
        this.director = director;
    }


    @Override
    public String toString() {
        return "Video{" +
                "formato=" + formato +
                ", duracion=" + duracion +
                ", idioma='" + idioma + '\'' +
                ", director='" + director + '\'' +
                ", signatura='" + signatura + '\'' +
                ", restringido=" + restringido +
                ", tema='" + tema + '\'' +
                ", fechaCompra=" + fechaCompra +
                "} " + super.toString();
    }

    @Override
    public String toCSVLine() {
        return this.signatura+","+this.restringido+","+this.tema+","+this.fechaCompra+","+this.formato+","+this.duracion+","+this.idioma+","+this.director;
    }
}
