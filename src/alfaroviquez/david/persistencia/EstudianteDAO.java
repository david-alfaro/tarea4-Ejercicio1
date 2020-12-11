package alfaroviquez.david.persistencia;

import alfaroviquez.david.bl.entidades.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    Connection cnx;

    private final String TEMPLATE_CMD_INSERTAR = "insert into estudiantes(carne,nombre,apellido,carrera,creditos)" +
            "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSESTUDIANTES = "select * from estudiantes";

    private PreparedStatement cmdInsertar;
    private PreparedStatement qryTodoEstudiantes;

     public EstudianteDAO(Connection cnx){
         this.cnx=cnx;
         try{
             this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
             this.qryTodoEstudiantes = cnx.prepareStatement(TEMPLATE_QRY_TODOSESTUDIANTES);
         }catch (SQLException e){
             e.printStackTrace();
         }
     }

     public void guardar(Estudiante estudiante) throws SQLException {
         if(this.cmdInsertar!=null){
             this.cmdInsertar.setString(1,estudiante.getID());
             this.cmdInsertar.setString(2, estudiante.getNombre());
             this.cmdInsertar.setString(3,estudiante.getApellido());
             this.cmdInsertar.setString(4,estudiante.getCarrera());
             this.cmdInsertar.setInt(5,estudiante.getCreditos());
             this.cmdInsertar.execute();
         }
     }

     public List<Estudiante> obtenerEstudiantes()  {
         ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
         try {
             ResultSet resultSet = this.qryTodoEstudiantes.executeQuery();
             while (resultSet.next()) {
                 Estudiante estudiante = new Estudiante();
                 estudiante.setID(resultSet.getString("carne"));
                 estudiante.setNombre(resultSet.getString("nombre"));
                 estudiante.setApellido(resultSet.getString("apellido"));
                 estudiante.setCarrera(resultSet.getString("carrera"));
                 estudiante.setCreditos(resultSet.getInt("creditos"));
                 listaEstudiantes.add(estudiante);
             }
         }catch (SQLException e) {
             e.printStackTrace();
         }


         return listaEstudiantes;
     }
}
