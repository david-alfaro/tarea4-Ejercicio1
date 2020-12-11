package alfaroviquez.david.bl.entidades;

import alfaroviquez.david.bl.tipos.tipoContrato;

import java.sql.Date;
import java.time.LocalDate;

public class Profesor extends Persona {

    private Date fechaContratacion;
    private tipoContrato contrato;



    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public tipoContrato getContrato() {
        return contrato;
    }

    public void setContrato(tipoContrato contrato) {
        this.contrato = contrato;
    }

    public Profesor() {
    }

    public Profesor(String nombre, String apellido, String ID, Date fechaContratacion, tipoContrato contrato) {
        super(nombre, apellido, ID);
        this.fechaContratacion = fechaContratacion;
        this.contrato = contrato;
    }


    @Override
    public String toString() {
        return "Profesor{" +
                "fechaContratacion=" + fechaContratacion +
                ", contrato=" + contrato +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ID='" + ID + '\'' +
                "} ";
    }

    @Override
    public String toCSVLine() {
        return this.nombre+","+this.apellido+","+this.ID+","+this.contrato+","+this.fechaContratacion;
    }
}
