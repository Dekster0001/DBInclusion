package vista;

import modelo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

public class PersonaVista03 extends javax.swing.JFrame {


    private Persona persona;

    public PersonaVista03() {
        initComponents();
        setLocationRelativeTo(null);
    }
 public PersonaVista03(Persona persona) {
     this.persona = persona; // Primero asignar la persona
    initComponents(); // Esto debe ir después de asignar la variable
    setLocationRelativeTo(null);
    configurarTablas();
    mostrarInformacionTransferencia();
    generarCalendarioPagos();
}

 private void cargarDatosFaltantesDesdeBD() {
    try (Connection conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=DBInclusion;"
            + "encrypt=true;trustServerCertificate=true;",
            "UserSQL", "1234567890");
            
         PreparedStatement pstmt = conn.prepareStatement(
            "SELECT dni_firmante, estado FROM tblSolicitudes WHERE id = ?")) {
        
        pstmt.setInt(1, persona.getId());
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Corregido: usar dni_firmante en lugar de dni
                if (persona.getDni() == null || persona.getDni().isEmpty()) {
                    persona.setDni(rs.getString("dni_firmante"));
                }
                if (persona.getEstado() == null || persona.getEstado().isEmpty()) {
                    persona.setEstado(rs.getString("estado"));
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this,
            "Error al cargar datos: " + ex.getMessage(),
            "Error de Base de Datos",
            JOptionPane.ERROR_MESSAGE);
        System.err.println("Error SQL:");
        ex.printStackTrace();
    }
}
 private void configurarTablas() {
    // Configurar tabla de transferencia (izquierda)
    DefaultTableModel modelTransferencia = new DefaultTableModel(
        new Object[][]{}, 
        new String[]{"Concepto", "Valor"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Hacer que no sea editable
        }
    };
    tblTransferencia.setModel(modelTransferencia);
    
    // Configurar tabla de calendario (derecha)
    DefaultTableModel modelCalendario = new DefaultTableModel(
        new Object[][]{},
        new String[]{"N° Cuota", "Fecha Pago", "Monto"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    tblCalendario.setModel(modelCalendario);
    
    // Ajustar anchos de columnas
    tblTransferencia.getColumnModel().getColumn(0).setPreferredWidth(150);
    tblTransferencia.getColumnModel().getColumn(1).setPreferredWidth(100);
    
    tblCalendario.getColumnModel().getColumn(0).setPreferredWidth(50);
    tblCalendario.getColumnModel().getColumn(1).setPreferredWidth(120);
    tblCalendario.getColumnModel().getColumn(2).setPreferredWidth(80);
}
private void mostrarInformacionTransferencia() {
    DefaultTableModel model = (DefaultTableModel) tblTransferencia.getModel();
    model.setRowCount(0); // Limpiar tabla existente
    
    double monto = persona.getMonto();
    double interes = monto * 0.10; // 10% de interés
    double total = monto + interes;
    int meses = calcularPlazo(monto);
    double cuota = total / meses;
    
    // Agregar filas a la tabla
    model.addRow(new Object[]{"Monto solicitado", String.format("S/%.2f", monto)});
    model.addRow(new Object[]{"Interés (10%)", String.format("S/%.2f", interes)});
    model.addRow(new Object[]{"Total a pagar", String.format("S/%.2f", total)});
    model.addRow(new Object[]{"Plazo", meses + " meses"});
    model.addRow(new Object[]{"Cuota mensual", String.format("S/%.2f", cuota)});
    
    // Actualizar objeto persona
    persona.setMeses(meses);
    persona.setCuotaMensual(cuota);
    persona.setMontoTotal(total);
}

private int calcularPlazo(double monto) {
    if (monto <= 1000) return 12;
    if (monto <= 3000) return 24;
    return 36;
}
 
private void generarCalendarioPagos() {
    DefaultTableModel model = (DefaultTableModel) tblCalendario.getModel();
    model.setRowCount(0); // Limpiar tabla existente
    
    double cuota = persona.getCuotaMensual();
    int meses = persona.getMeses();
    
    Calendar calendario = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    for (int i = 1; i <= meses; i++) {
        calendario.add(Calendar.MONTH, 1); // Sumar 1 mes
        model.addRow(new Object[]{
            i, // N° de cuota
            sdf.format(calendario.getTime()), // Fecha formateada
            String.format("S/%.2f", cuota) // Monto formateado
        });
    }
}
///-------------------------------------------------//
private void guardarInformacion() throws SQLException {
    String sql = "UPDATE tblSolicitudes SET " +
                 "meses = ?, " +
                 "cuota_mensual = ?, " +
                 "monto_total = ?, " +
                 "estado = ? " +  // Usar el estado actual en lugar de 'APROBADO'
                 "WHERE id = ?";
    
    try (Connection conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=Microcreditos;encrypt=true;trustServerCertificate=true",
            "UserSQL", "1234567890");
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, persona.getMeses());
        pstmt.setDouble(2, persona.getCuotaMensual());
        pstmt.setDouble(3, persona.getMontoTotal());
        pstmt.setString(4, persona.getEstado());  // Usar el estado actual
        pstmt.setInt(5, persona.getId());
        
        pstmt.executeUpdate();
        
    }
}

private void registrarDNI(Connection conn, int idSolicitud, String dni) throws SQLException {
    String sql = "UPDATE tblSolicitudes SET dni = ? WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, dni);
        pstmt.setInt(2, idSolicitud);
        pstmt.executeUpdate();
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnHistorialCompleto = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCalendario = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTransferencia = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnSaliir = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("A Web Page");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Solicitar Microcrédito"));

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(255, 255, 255));
        label1.setText("Solicitud de Microcrédito");

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setBackground(new java.awt.Color(255, 255, 255));
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setText("Aprobación y desembolso");

        label3.setAlignment(java.awt.Label.CENTER);
        label3.setBackground(new java.awt.Color(51, 102, 255));
        label3.setText("Seguimiento y pago");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Seguimiento"));

        jLabel2.setText("Transferencia realizada");

        jLabel5.setText("Calendario de pagos");

        btnHistorialCompleto.setText("Ver historial Completo");
        btnHistorialCompleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialCompletoActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        tblCalendario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblCalendario);

        tblTransferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblTransferencia);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(btnHistorialCompleto)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHistorialCompleto)
                .addGap(20, 20, 20))
        );

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnSaliir.setText("Salir");
        btnSaliir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaliirActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar ....");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnSaliir)
                        .addGap(112, 112, 112)
                        .addComponent(btnVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizar)
                        .addGap(124, 124, 124))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar)
                    .addComponent(btnVolver)
                    .addComponent(btnSaliir))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jMenu1.setText("Cliente");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Solicitar microcrédito");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pago");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Registro de utilidad");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHistorialCompletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialCompletoActionPerformed
        StringBuilder historial = new StringBuilder();
    historial.append("--- HISTORIAL COMPLETO ---\n\n");
    
    // Información básica
    String dni = persona.getDni();
    historial.append("DNI: ").append(persona.getDni() != null ? persona.getDni() : "No registrado").append("\n");
    historial.append("Estado: ").append(persona.getEstado()).append("\n\n");
    
    // Detalles del préstamo
    historial.append("Propósito del préstamo: ").append(persona.getProposito()).append("\n\n");
    
    // Detalles financieros
    historial.append(String.format("Monto solicitado: S/%.2f\n", persona.getMonto()));
    historial.append(String.format("Interés (10%%): S/%.2f\n", persona.getMonto() * 0.10));
    historial.append(String.format("Total a pagar: S/%.2f\n\n", persona.getMontoTotal()));
    
    // Plazo y cuota
    historial.append(String.format("Plazo: %d meses\n", persona.getMeses()));
    historial.append(String.format("Cuota mensual: S/%.2f\n\n", persona.getCuotaMensual()));
    
    // Próximos 3 pagos
    historial.append("Próximos pagos:\n");
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    for (int i = 1; i <= 6; i++) {
        cal.add(Calendar.MONTH, 1);
        historial.append(String.format("Cuota %d: %s - S/%.2f\n", 
            i, sdf.format(cal.getTime()), persona.getCuotaMensual()));
    }
    
    // Mostrar en un JOptionPane más grande
    JOptionPane.showMessageDialog(this, 
        historial.toString(), 
        "Historial Completo de la Solicitud", 
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnHistorialCompletoActionPerformed

    private void btnSaliirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaliirActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea salir de la aplicación?", 
            "Confirmar salida", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSaliirActionPerformed
        
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.dispose(); // Cierra la ventana actual
        PersonaVista02 vistaAnterior = new PersonaVista02(persona);
        vistaAnterior.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
    try {
        // Validar que el DNI esté registrado
        if (persona.getDni() == null || persona.getDni().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No se ha registrado el DNI para esta solicitud", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        guardarInformacion();
        
        JOptionPane.showMessageDialog(this, 
            "Proceso completado exitosamente\nDNI registrado: " + persona.getDni(), 
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        this.dispose();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Error al guardar: " + ex.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JToggleButton btnHistorialCompleto;
    private javax.swing.JButton btnSaliir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JTable tblCalendario;
    private javax.swing.JTable tblTransferencia;
    // End of variables declaration//GEN-END:variables
}
