package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.OtroMaterial;
import alfaroviquez.david.bl.entidades.Video;
import alfaroviquez.david.bl.tipos.formatoVideo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtroDAO {
    Connection con;

    private final String TEMPLATE_INSERT = "insert into otrosMateriales (signatura,tema,fechaCompra,restringido,descripcion)" +
            "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY = "select * from otrosMateriales";

    private PreparedStatement cmdInsertar;
    private PreparedStatement consulta;

    public OtroDAO(Connection cnx){
        this.con=cnx;
        try{
            this.cmdInsertar = con.prepareStatement(TEMPLATE_INSERT);
            this.consulta = con.prepareStatement(TEMPLATE_QRY);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void guardar(OtroMaterial otro) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setInt(1,otro.getSignatura());
            this.cmdInsertar.setString(2, otro.getTema());
            this.cmdInsertar.setDate(3,otro.getFechaCompra());
            this.cmdInsertar.setBoolean(4, otro.getRestringido());
            this.cmdInsertar.setString(5, otro.getDescripcion());
            this.cmdInsertar.execute();
        }
    }
    public List<OtroMaterial> obtenerOtros(){
        ArrayList<OtroMaterial> listaOtros = new ArrayList<>();
        try {
            ResultSet resultSet = this.consulta.executeQuery();
            while (resultSet.next()){
                OtroMaterial otro = new OtroMaterial();
                otro.setSignatura(resultSet.getInt("signatura"));
                otro.setTema(resultSet.getString("tema"));
                otro.setFechaCompra(resultSet.getDate("fechaCompra"));
                otro.setRestringido(resultSet.getBoolean("restringido"));
                otro.setDescripcion(resultSet.getString("descripcion"));
                listaOtros.add(otro);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaOtros;
    }
}
