package alfaroviquez.david.bl.entidades;

public class Estudiante extends Persona {


    private String carrera;
    private int creditos;



    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String ID, String carrera, int creditos) {
        super(nombre, apellido, ID);
        this.carrera = carrera;
        this.creditos = creditos;
    }


    @Override
    public String toString() {
        return "Estudiante{" +
                "carrera='" + carrera + '\'' +
                ", creditos=" + creditos +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ID='" + ID + '\'' +
                "} " ;
    }

    @Override
    public String toCSVLine() {
        return this.nombre+","+this.apellido+","+this.ID+","+this.carrera+","+this.creditos;
    }
}
