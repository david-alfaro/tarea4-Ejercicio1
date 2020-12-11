package alfaroviquez.david.bl.entidades;

import alfaroviquez.david.bl.interfaces.SerializacionCSV;

import java.util.Objects;

public abstract class Persona implements SerializacionCSV {
    protected String nombre;
    protected String apellido;
    protected String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Persona() {
    }

    public Persona(String nombre, String apellido, String ID) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ID = ID;
    }



    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) &&
                Objects.equals(apellido, persona.apellido) &&
                Objects.equals(ID, persona.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, ID);
    }
}
