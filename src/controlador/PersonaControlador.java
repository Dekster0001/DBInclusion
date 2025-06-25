package controlador;

import modelo.Persona; // Asegúrate que esta clase exista y esté bien importada
import modelo.main;
import modelo.Cconexion;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PersonaControlador {

    
    public Map<String, String> obtenerUltimaSolicitud() {
        Map<String, String> datos = new HashMap<>();
        
        try {
            Persona ultimaSolicitud = Persona.obtenerUltimaSolicitud();
            if (ultimaSolicitud != null) {
                datos.put("ingresos", String.valueOf(ultimaSolicitud.getIngresos()));
                datos.put("monto", String.valueOf(ultimaSolicitud.getMonto()));
                datos.put("proposito", ultimaSolicitud.getProposito());
                datos.put("garantia", ultimaSolicitud.isGarantia() ? "Sí" : "No"); // Cambiado a isGarantia()
                
                // Determinar estado basado en garantía
                String estado = ultimaSolicitud.isGarantia() ? "Aprobado" : "Rechazado"; // Cambiado a isGarantia()
                Persona.actualizarEstado(ultimaSolicitud.getId(), estado);
                datos.put("estado", estado);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la última solicitud: " + e.getMessage());
        }
        
        return datos;
    }
    
    public boolean registrarSolicitud(double monto, double ingresos, String proposito, boolean garantia) {
        try {
            Persona nuevaSolicitud = new Persona(monto, ingresos, proposito, garantia);
            return nuevaSolicitud.guardar();
        } catch (SQLException e) {
            System.err.println("Error al registrar solicitud: " + e.getMessage());
            return false;
        }
    }
    
    public boolean registrarFirma(int idSolicitud, String dniFirmante) throws SQLException {
    String sql = "UPDATE tblSolicitudes SET dni_firmante = ?, fecha_firma = GETDATE(), " +
                "estado = 'Firmado' WHERE id = ?";
    
    try (Connection conn = new Cconexion().establecerConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, dniFirmante);
        pstmt.setInt(2, idSolicitud);
        
        int filasAfectadas = pstmt.executeUpdate();
        return filasAfectadas > 0;
    }
}
    public boolean actualizarEstado(int idSolicitud, String estado) throws SQLException {
    String sql = "UPDATE tblSolicitudes SET estado = ? WHERE id = ?";
    
    try (Connection conn = new Cconexion().establecerConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, estado);
        pstmt.setInt(2, idSolicitud);
        
        return pstmt.executeUpdate() > 0;
    }
}
}        

