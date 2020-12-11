package alfaroviquez.david.bl.entidades;

import alfaroviquez.david.bl.tipos.tipoNombramiento;

public class Administrativo extends Persona  {
    private tipoNombramiento nombramiento;
    private int horasAsignadas;

    public tipoNombramiento getNombramiento() {
        return nombramiento;
    }

    public void setNombramiento(tipoNombramiento nombramiento) {
        this.nombramiento = nombramiento;
    }

    public int getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(int horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }

    public Administrativo() {
    }

    public Administrativo(String nombre, String apellido, String ID, tipoNombramiento nombramiento, int horasAsignadas) {
        super(nombre, apellido, ID);
        this.nombramiento = nombramiento;
        this.horasAsignadas = horasAsignadas;
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "nombramiento=" + nombramiento +
                ", horasAsignadas=" + horasAsignadas +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ID='" + ID + '\'' +
                "} ";
    }

    @Override
    public String toCSVLine() {
        return this.nombre+","+this.apellido+","+this.ID+","+this.nombramiento+","+this.horasAsignadas;
    }
}
