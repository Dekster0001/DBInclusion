package modelo;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cconexion {
    private Connection conectar = null;
    
    private final String usuario = "UserSQL";
    private final String contrasenia = "1234567890";
    private final String bd = "Microcreditos";
    private final String ip = "localhost";
    private final String puerto ="1433";
    
    private final String cadena ="jdbc:sqlserver://"+ip+":"+puerto+";databaseName="+bd+";encrypt=true;trustServerCertificate=true"; 
    
    public Connection establecerConexion(){
        try{
           if (conectar == null || conectar.isClosed()) {
                conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
                JOptionPane.showMessageDialog(null, "Conexión establecida correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.getMessage());
        }
        return conectar;
    }
    
    public void cerrarConexion() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
        }
    }
      
}
