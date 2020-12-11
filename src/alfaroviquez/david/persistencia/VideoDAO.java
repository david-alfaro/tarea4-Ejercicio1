package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Video;
import alfaroviquez.david.bl.tipos.formatoVideo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {

    Connection con;

    private final String TEMPLATE_INSERT = "insert into video (signatura,tema,fechaCompra,restringido,formato,duracion,idioma,director)" +
            "values (?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY = "select * from video";

    private PreparedStatement cmdInsertar;
    private PreparedStatement consulta;

    public VideoDAO(Connection cnx){
        this.con=cnx;
        try{
            this.cmdInsertar = con.prepareStatement(TEMPLATE_INSERT);
            this.consulta = con.prepareStatement(TEMPLATE_QRY);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void guardar(Video video) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setInt(1,video.getSignatura());
            this.cmdInsertar.setString(2, video.getTema());
            this.cmdInsertar.setDate(3,video.getFechaCompra());
            this.cmdInsertar.setBoolean(4, video.getRestringido());
            this.cmdInsertar.setString(5, String.valueOf(video.getFormato()));
            this.cmdInsertar.setInt(6,video.getDuracion());
            this.cmdInsertar.setString(7, video.getIdioma());
            this.cmdInsertar.setString(8, video.getDirector());
            this.cmdInsertar.execute();
        }
    }
    public List<Video> obtenerVideos(){
        ArrayList<Video> listaVideos = new ArrayList<>();
        try {
            ResultSet resultSet = this.consulta.executeQuery();
            while (resultSet.next()){
                Video video = new Video();
                video.setSignatura(resultSet.getInt("signatura"));
                video.setTema(resultSet.getString("tema"));
                video.setFechaCompra(resultSet.getDate("fechaCompra"));
                video.setRestringido(resultSet.getBoolean("restringido"));
                video.setFormato(formatoVideo.valueOf(resultSet.getString("formato")));
                video.setDuracion(resultSet.getInt("duracion"));
                video.setIdioma(resultSet.getString("idioma"));
                video.setDirector(resultSet.getString("director"));
                listaVideos.add(video);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaVideos;
    }
}
