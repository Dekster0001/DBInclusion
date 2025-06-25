package modelo;
//persona
import java.sql.*;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import vista.PersonaVista01;
import vista.PersonaVista02;
import vista.PersonaVista03;
import controlador.PersonaControlador;

        
//Persona
public class Persona {
    //atributos de la identidad
    private int id;
    private double monto;
    private double ingresos;
    private String proposito;
    private boolean garantia;
    private String estado;
    private String dni;
    private int meses;
    private double cuotaMensual;
    private double montoTotal;
    
    
    //Constructor
    public Persona(double monto, double ingresos, String proposito, boolean garantia){
        this.monto = monto;
        this.ingresos = ingresos;
        this.proposito = Objects.requireNonNull(proposito, "El propósito no puede ser nulo");
        this.garantia = garantia;
        this.estado = "Pendiente"; // Valor por defecto
    
    }
    
    //Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public double getIngresos() { return ingresos; }
    public void setIngresos(double ingresos) { this.ingresos = ingresos; }

    public String getProposito() { return proposito; }
    public void setProposito(String proposito) { this.proposito = proposito; }

    public boolean isGarantia() { return garantia; }
    public void setGarantia(boolean garantia) { this.garantia = garantia; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    
    public int getMeses() { return meses; }
    public void setMeses(int meses) { this.meses = meses; }
    
    public double getCuotaMensual() { return cuotaMensual; }
    public void setCuotaMensual(double cuotaMensual) { this.cuotaMensual = cuotaMensual; }
    
    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }
    

    // Métodos de base de datos usando Cconexion
    public static Persona obtenerUltimaSolicitud() throws SQLException {
        String sql = "SELECT TOP 1 * FROM tblSolicitudes ORDER BY id DESC";
        try (Connection conn = new Cconexion().establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                Persona p = new Persona(
                    rs.getDouble("monto"),
                    rs.getDouble("ingresos"),
                    rs.getString("proposito"),
                    rs.getBoolean("garantia")
                );
                p.id = rs.getInt("id");
                p.estado = rs.getString("estado");
                return p;
            }
        }
        return null;
    }

    public boolean guardar() throws SQLException {
        String sql = "INSERT INTO tblSolicitudes (monto, ingresos, proposito, garantia, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new Cconexion().establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setDouble(1, this.monto);
            pstmt.setDouble(2, this.ingresos);
            pstmt.setString(3, this.proposito);
            pstmt.setBoolean(4, this.garantia);
            pstmt.setString(5, this.estado);
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo guardar la solicitud");
            }
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    this.id = rs.getInt(1);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean actualizarEstado(int id, String nuevoEstado) throws SQLException {
        String sql = "UPDATE tblSolicitudes SET estado = ? WHERE id = ?";
        try (Connection conn = new Cconexion().establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nuevoEstado);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public static Persona obtenerSolicitud(int id){
        String sql = "SELECT * FROM tblSolicitudes WHERE id = ?";
        Cconexion conexion = new Cconexion();

        try (Connection conn = conexion.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Persona p = new Persona(
                    rs.getDouble("monto"),
                    rs.getDouble("ingresos"),
                    rs.getString("proposito"),
                    rs.getBoolean("garantia")
                );
                p.id = rs.getInt("id");
                p.estado = rs.getString("estado");
                return p;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int crearSolicitud(Persona solicitud) throws SQLException {
        String sql =  "INSERT INTO tblSolicitudes (monto, ingresos, proposito, garantia, estado) " +
                    "OUTPUT INSERTED.id " +  // Alternativa para obtener el ID
                    "VALUES (?, ?, ?, ?, ?)";
        Cconexion conexion = new Cconexion();

        try (Connection conn = conexion.establecerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            
            pstmt.setDouble(1, solicitud.getMonto());
            pstmt.setDouble(2, solicitud.getIngresos());
            pstmt.setString(3, solicitud.getProposito());
            pstmt.setBoolean(4, solicitud.isGarantia());
            pstmt.setString(5, solicitud.getEstado());

            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("No se pudo crear la solicitud");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else{
                    throw new SQLException("No se obtuvo el ID generado");
                }
            }
        }
    }
    
    public boolean insertarPersona() {
        try{
            int idGenerado = Persona.crearSolicitud(this);
            if (idGenerado > 0) {
            this.id = idGenerado;
            return true;
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
                
        
    return false;
    }
    
}

