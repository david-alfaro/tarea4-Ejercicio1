package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Audio;
import alfaroviquez.david.bl.tipos.formatoAudio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AudioDAO {
    Connection cnx;

    private final String TEMPLATE_INSERTAR = "insert into audio(signatura,tema,fechaCompra,restringido,formato,duracion,idioma)" +
            "values(?,?,?,?,?,?,?)";
    private final String TEMPLAT_QRY = "select * from audio";

    private PreparedStatement cmdInsertar;
    private PreparedStatement qryConsulta;

    public AudioDAO(Connection cnx){
        this.cnx=cnx;
        try {
            this.cmdInsertar=cnx.prepareStatement(TEMPLATE_INSERTAR);
            this.qryConsulta= cnx.prepareStatement(TEMPLAT_QRY);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void guardar(Audio audio) throws SQLException {
        if(this.cmdInsertar!=null){
            this.cmdInsertar.setInt(1,audio.getSignatura());
            this.cmdInsertar.setString(2,audio.getTema());
            this.cmdInsertar.setDate(3, audio.getFechaCompra());
            this.cmdInsertar.setBoolean(4,audio.getRestringido());
            this.cmdInsertar.setString(5, String.valueOf(audio.getFormato()));
            this.cmdInsertar.setInt(6,audio.getDuracion());
            this.cmdInsertar.setString(7, audio.getIdioma());
            this.cmdInsertar.execute();
        }
    }

    public List<Audio> obtenerAudios(){
        ArrayList<Audio> listaAudios = new ArrayList<>();
        try{
            ResultSet resultSet = this.qryConsulta.executeQuery();
            while (resultSet.next()){
                Audio  audio = new Audio();
                audio.setSignatura(resultSet.getInt("signatura"));
                audio.setTema(resultSet.getString("tema"));
                audio.setFechaCompra(resultSet.getDate("fechaCompra"));
                audio.setRestringido(resultSet.getBoolean("restringido"));
                audio.setFormato(formatoAudio.valueOf(resultSet.getString("formato")));
                audio.setDuracion(resultSet.getInt("duracion"));
                audio.setIdioma(resultSet.getString("idioma"));
                listaAudios.add(audio);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaAudios;
    }
}
