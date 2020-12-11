package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Administrativo;
import alfaroviquez.david.bl.tipos.tipoNombramiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoDAO {

    Connection cnx;

    private final String TEMPLATE_CMD_INSERTAR = "insert into administrativos(cedula,nombre,apellido,tipoNombramiento,horas)" +
            "values(?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSADMIN = "select * from administrativos";

    private PreparedStatement cmdInsertar;
    private PreparedStatement qryTodosAdmin;

    public AdministrativoDAO(Connection cnx){
        this.cnx=cnx;
        try{
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.qryTodosAdmin = cnx.prepareStatement(TEMPLATE_QRY_TODOSADMIN);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void guardar(Administrativo admin) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setString(1,admin.getID());
            this.cmdInsertar.setString(2, admin.getNombre());
            this.cmdInsertar.setString(3, admin.getApellido());
            this.cmdInsertar.setString(4, String.valueOf(admin.getNombramiento()));
            this.cmdInsertar.setInt(5,admin.getHorasAsignadas());
            this.cmdInsertar.execute();
        }
    }
    public List<Administrativo> obtenerAdministrativos(){
        ArrayList<Administrativo>listaAdministrativos = new ArrayList<>();
        try{
            ResultSet resultSet = this.qryTodosAdmin.executeQuery();
            while (resultSet.next()){
                Administrativo admin = new Administrativo();
                admin.setID(resultSet.getString("cedula"));
                admin.setNombre(resultSet.getString("nombre"));
                admin.setApellido(resultSet.getString("apellido"));
                admin.setNombramiento(tipoNombramiento.valueOf(resultSet.getString("tipoNombramiento")));
                admin.setHorasAsignadas(resultSet.getInt("horas"));
                listaAdministrativos.add(admin);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaAdministrativos;
    }
}
