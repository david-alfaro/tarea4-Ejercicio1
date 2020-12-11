package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Texto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TextoDAO {
    Connection con;

    private final String TEMPLATE_INSERTAR = "insert into texto (signatura,tema,fechaCompra,restringido,titulo,autor,fechaPublicacion,numeroPaginas,idioma)" +
            "values(?,?,?,?,?,?,?,?,?)";
    private final String TEMPLAT_QRY = "select * from texto";

    private PreparedStatement cmdInsertar;
    private PreparedStatement qryConsulta;

    public TextoDAO(Connection cnx){
        this.con=cnx;
        try {
            this.cmdInsertar=con.prepareStatement(TEMPLATE_INSERTAR);
            this.qryConsulta = con.prepareStatement(TEMPLAT_QRY);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void guardar(Texto texto) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setInt(1,texto.getSignatura());
            this.cmdInsertar.setString(2,texto.getTema());
            this.cmdInsertar.setDate(3,texto.getFechaCompra());
            this.cmdInsertar.setBoolean(4,texto.getRestringido());
            this.cmdInsertar.setString(5,texto.getTitulo());
            this.cmdInsertar.setString(6, texto.getNombreAutor());
            this.cmdInsertar.setDate(7,texto.getFechaPublicacion());
            this.cmdInsertar.setInt(8,texto.getNumeroPaginas());
            this.cmdInsertar.setString(9, texto.getIdioma());
            this.cmdInsertar.execute();
        }
    }
    public List<Texto> obtenerTextos(){
        ArrayList<Texto>listaTextos = new ArrayList<>();
        try{
            ResultSet resultSet = this.qryConsulta.executeQuery();
            while (resultSet.next()){
                Texto texto = new Texto();
                texto.setSignatura(resultSet.getInt("signatura"));
                texto.setTema(resultSet.getString("tema"));
                texto.setFechaCompra(resultSet.getDate("fechaCompra"));
                texto.setRestringido(resultSet.getBoolean("restringido"));
                texto.setTitulo(resultSet.getString("titulo"));
                texto.setNombreAutor(resultSet.getNString("autor"));
                texto.setFechaPublicacion(resultSet.getDate("fechaPublicacion"));
                texto.setNumeroPaginas(resultSet.getInt("numeroPaginas"));
                texto.setIdioma(resultSet.getString("idioma"));
                listaTextos.add(texto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaTextos;
    }
}
