package alfaroviquez.david.bl.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


public class Prestamo  {
    private Persona persona;
    private Material material;
    private Date fechaDevolucion;
    private Date fechaPrestamo;

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Prestamo() {
    }

    public Prestamo(Persona persona, Material material, Date fechaDevolucion, Date fechaPrestamo) {
        this.persona = persona;
        this.material = material;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "persona=" + persona +
                ", material=" + material +
                ", fechaDevolucion=" + fechaDevolucion +
                ", fechaPrestamo=" + fechaPrestamo +
                '}';
    }


}
