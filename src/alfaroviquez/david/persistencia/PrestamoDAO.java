package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Material;
import alfaroviquez.david.bl.entidades.Persona;
import alfaroviquez.david.bl.entidades.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    Connection con;

    private final String TEMPLATE_INSERT = "insert into prestamo(identificacion,signatura,fecha)" +
            "values(?,?,?,?)";


    private PreparedStatement cmdInsertar;
    private PreparedStatement consulta;

    public PrestamoDAO(Connection cnx){
        this.con=cnx;
        try{
            this.cmdInsertar = con.prepareStatement(TEMPLATE_INSERT);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void guardarPrestamo( Prestamo prestamo) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setString(1,prestamo.getPersona().getID());
            this.cmdInsertar.setInt(2,prestamo.getMaterial().getSignatura());
            this.cmdInsertar.setDate(3,prestamo.getFechaDevolucion());
            this.cmdInsertar.setDate(4,prestamo.getFechaPrestamo());
            this.cmdInsertar.execute();
        }
    }

}
