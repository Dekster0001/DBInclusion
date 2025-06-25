package vista;

import java.sql.*;
import javax.swing.*;
import java.util.Random;
import modelo.Persona;
import java.sql.SQLException;
import controlador.PersonaControlador;
import javax.swing.JOptionPane;
import vista.PersonaVista01;
import vista.PersonaVista03;
import java.sql.Connection;
import modelo.Cconexion;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import java.sql.ResultSet;

public class PersonaVista02 extends javax.swing.JFrame {
    
private Persona persona;
private PersonaControlador controlador;

 public PersonaVista02(Persona persona) {
    this.persona = persona;
    this.controlador = new PersonaControlador();
    initComponents(); // Esto debe ir después de asignar la variable
    setLocationRelativeTo(null);
    
    // Determinar estado basado en si tiene garantía (usando isGarantia() en lugar de tieneGarantia())
    String estado = determinarEstado(persona.isGarantia());
    persona.setEstado(estado);
    txtEstado.setText(estado);
    txtEstado.setEditable(false);
    
    // Mostrar resumen
    String resumen = "Monto solicitado: $" + persona.getMonto() + "\n" +
                     "Ingresos mensuales: $" + persona.getIngresos() + "\n" +
                     "Propósito: " + persona.getProposito()+ "\n" +
                     "Garantía: " + (persona.isGarantia() ? "Sí" : "No");
    txtResumen.setText(resumen);
    txtResumen.setEditable(false);
}

private String determinarEstado(boolean tieneGarantia) {
    Random rand = new Random();
    double probabilidad = rand.nextDouble();
    
    if (tieneGarantia) {
        // Con garantía: 60% Aprobado, 30% En revisión, 10% Rechazado
        if (probabilidad < 0.6) {
            return "Aprobado";
        } else if (probabilidad < 0.9) {
            return "En revisión";
        } else {
            return "Rechazado";
        }
    } else {
        // Sin garantía: 10% Aprobado, 50% En revisión, 40% Rechazado
        if (probabilidad < 0.1) {
            return "Aprobado";
        } else if (probabilidad < 0.4) {
            return "En revisión";
        } else {
            return "Rechazado";
        }
    }
}

  private void configurarBotones() {
        // Configurar botones según estado
        boolean aprobado = persona.getEstado().equals("Aprobado");
        boolean enRevision = persona.getEstado().equals("En revisión");
        
        btnFirmarContrat.setEnabled(aprobado);
        btnContinuar.setEnabled(false); // Solo se habilita después de firmar
        
        if (!aprobado) {
            String mensaje = enRevision ? 
            "Su solicitud está en revisión. Espere notificaciones." :
            "Lo sentimos, su solicitud ha sido rechazada.";
            
            btnFirmarContrat.setToolTipText(mensaje);
            btnContinuar.setToolTipText(mensaje);
            
            // Mostrar mensaje según estado
        JOptionPane.showMessageDialog(this, mensaje, "Estado de solicitud", 
            enRevision ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEstado = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResumen = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        btnFirmarContrat = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnContinuar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("A Web Page");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Solicitar Microcrédito"));

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setBackground(new java.awt.Color(255, 255, 255));
        label1.setText("Solicitud de Microcrédito");

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setBackground(new java.awt.Color(51, 102, 255));
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setText("Aprobación y desembolso");

        label3.setAlignment(java.awt.Label.CENTER);
        label3.setBackground(new java.awt.Color(255, 255, 255));
        label3.setText("Seguimiento y pago");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));

        jLabel2.setText("Estado de solicitud");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane1.setViewportView(txtEstado);

        jScrollPane2.setViewportView(txtResumen);

        jLabel1.setText("Resumen");

        btnFirmarContrat.setText("Firmar contrato digital");
        btnFirmarContrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirmarContratActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnFirmarContrat)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFirmarContrat)
                        .addGap(0, 7, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)))
                .addContainerGap())
        );

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnContinuar.setText("Continuar...");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(72, 72, 72)
                .addComponent(btnVolver)
                .addGap(56, 56, 56)
                .addComponent(btnContinuar)
                .addGap(116, 116, 116))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnSalir)
                    .addComponent(btnContinuar))
                .addContainerGap(18, Short.MAX_VALUE))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirmarContratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirmarContratActionPerformed
    if (!persona.getEstado().equals("Aprobado")) {
        JOptionPane.showMessageDialog(this, 
            "Solo puede firmar contratos de solicitudes aprobadas", 
            "Acción no permitida", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String dni = JOptionPane.showInputDialog(this, 
        "Ingrese su DNI para firmar el contrato:",
        "Firma Digital", 
        JOptionPane.PLAIN_MESSAGE);
    
    if (dni == null || dni.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Debe ingresar un DNI válido", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Validar formato de DNI (8 dígitos numéricos)
    if (!dni.matches("\\d{8}")) {
        JOptionPane.showMessageDialog(this, 
            "El DNI debe contener exactamente 8 dígitos numéricos", 
            "Error de formato", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Registrar firma usando el controlador
        boolean exito = controlador.registrarFirma(persona.getId(), dni);
        
        if (exito) {
            persona.setEstado("Firmado");
            persona.setDni(dni); // <-- ESTA ES LA LÍNEA CLAVE QUE FALTABA
            txtEstado.setText("Estado de solicitud: Firmado");
            btnContinuar.setEnabled(true);
            JOptionPane.showMessageDialog(this, 
                "Contrato firmado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se pudo registrar la firma. Verifique la conexión a la base de datos.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Error de base de datos: " + ex.getMessage(), 
            "Error crítico", 
            JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error inesperado: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    
    }//GEN-LAST:event_btnFirmarContratActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea salir de la aplicación?", 
            "Confirmar salida", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        //Volver al jFrame anterior
        this.dispose(); // Cierra la ventana actual
        PersonaVista01 vistaAnterior = new PersonaVista01(persona);
        vistaAnterior.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // Continuar al siguiente JFrame
        if (!persona.getEstado().equals("Firmado")) {
                JOptionPane.showMessageDialog(this, 
                    "Debe firmar el contrato antes de continuar", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        try {
        // Actualizar estado en la base de datos
        boolean exito = Persona.actualizarEstado(persona.getId(), "Firmado");
        if (!exito) {
            JOptionPane.showMessageDialog(this, 
                "Error al actualizar el estado en la base de datos", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Continuar a la siguiente vista
        this.dispose();
        PersonaVista03 siguienteVista = new PersonaVista03(persona);
        siguienteVista.setVisible(true);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, 
            "Error de base de datos: " + ex.getMessage(), 
            "Error crítico", 
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnContinuarActionPerformed

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PersonaVista02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonaVista02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonaVista02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonaVista02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnFirmarContrat;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    public javax.swing.JTextPane txtEstado;
    public javax.swing.JTextPane txtResumen;
    // End of variables declaration//GEN-END:variables

}
