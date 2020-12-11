package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Profesor;
import alfaroviquez.david.bl.tipos.tipoContrato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {
    Connection cnx;

    private final String TEMPLATE_CMD_INSERT = "insert into profesores (cedula,nombre,apellido,fechaContratacion,tipoContrato)" +
            "values(?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSPROFES = "select * from profesores";

    private PreparedStatement cmdInsertar;
    private PreparedStatement qryTodosProfes;

    public ProfesorDAO(Connection cnx){
        this.cnx = cnx;
        try{
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERT);
            this.qryTodosProfes = cnx.prepareStatement(TEMPLATE_QRY_TODOSPROFES);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void guardar(Profesor profesor) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setString(1,profesor.getID());
            this.cmdInsertar.setString(2, profesor.getNombre());
            this.cmdInsertar.setString(3, profesor.getApellido());
            this.cmdInsertar.setDate(4, Date.valueOf(String.valueOf(profesor.getFechaContratacion())));
            this.cmdInsertar.setString(5, String.valueOf(profesor.getContrato()));
            this.cmdInsertar.execute();
        }
    }

    public List<Profesor> obtenerProfesores(){
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        try{
            ResultSet resultSet = this.qryTodosProfes.executeQuery();
            while (resultSet.next()){
                Profesor profesor = new Profesor();
                profesor.setID(resultSet.getString("cedula"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setApellido(resultSet.getString("apellido"));
                profesor.setFechaContratacion(resultSet.getDate("fechaContratacion"));
                profesor.setContrato(tipoContrato.valueOf(resultSet.getString("tipoContrato")));
                listaProfesores.add(profesor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaProfesores;
    }


}
