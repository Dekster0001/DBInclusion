package vista;


import javax.swing.JOptionPane;
import modelo.Persona;
        
public class PersonaVista01 extends javax.swing.JFrame {
    
private Persona persona;

 public PersonaVista01() {
    initComponents(); // Esto debe ir después de asignar la variable
    setLocationRelativeTo(null);
}
        
    public PersonaVista01(Persona persona) {
        this.persona = persona;
        initComponents();
        setLocationRelativeTo(null);
        
        
    }
public double getMonto() {
    try {
        return Double.parseDouble(txtMonto.getText());
    } catch (NumberFormatException e) {
        return -1; // Valor inválido
    }
}

public double getIngresos() {
    try {
        return Double.parseDouble(txtIngresos.getText());
    } catch (NumberFormatException e) {
        return -1; // Valor inválido
    }
}

public String getProposito() {
    return txtProposito.getText();
}

public boolean tieneGarantia() {
    return btnSiGarantia.isSelected();
}

public void mostrarError(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
}

public void limpiarFormulario() {
    txtMonto.setText("");
    txtIngresos.setText("");
    txtProposito.setText("");
    btnNoGarantia.setSelected(true);
}

public void resetearBotonesGarantia() {
    btnNoGarantia.setSelected(true);
    btnSiGarantia.setSelected(false);
    btnNoGarantia.setEnabled(true);
    btnSiGarantia.setEnabled(true);
    btnNoGarantia.setBackground(null);
    btnSiGarantia.setBackground(null);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        txtIngresos = new javax.swing.JTextField();
        txtProposito = new javax.swing.JTextField();
        btnSiGarantia = new javax.swing.JRadioButton();
        btnNoGarantia = new javax.swing.JRadioButton();
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
        label1.setBackground(new java.awt.Color(51, 102, 255));
        label1.setText("Solicitud de Microcrédito");

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setBackground(new java.awt.Color(255, 255, 255));
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setText("Aprobación y desembolso");

        label3.setAlignment(java.awt.Label.CENTER);
        label3.setBackground(new java.awt.Color(255, 255, 255));
        label3.setText("Seguimiento y pago");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Solicitud "));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 18)); // NOI18N
        jLabel1.setText("Ingreso de Datos");

        jLabel2.setText("Monto solicitado");

        jLabel3.setText("Ingresos mensuales promedio");

        jLabel4.setText("Propósito de préstamo");

        jLabel5.setText("¿Cuenta con garantía?");

        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        txtIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIngresosActionPerformed(evt);
            }
        });

        txtProposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPropositoActionPerformed(evt);
            }
        });

        btnSiGarantia.setText("Si");
        btnSiGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiGarantiaActionPerformed(evt);
            }
        });

        btnNoGarantia.setText("No");
        btnNoGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoGarantiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMonto)
                    .addComponent(txtIngresos)
                    .addComponent(txtProposito)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnSiGarantia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(btnNoGarantia)
                        .addGap(44, 44, 44)))
                .addGap(54, 54, 54))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtIngresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtProposito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnSiGarantia)
                    .addComponent(btnNoGarantia))
                .addGap(0, 54, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(btnSalir)
                        .addGap(72, 72, 72)
                        .addComponent(btnVolver)
                        .addGap(56, 56, 56)
                        .addComponent(btnContinuar)))
                .addContainerGap(22, Short.MAX_VALUE))
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

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIngresosActionPerformed
        
    }//GEN-LAST:event_txtIngresosActionPerformed

    private void btnNoGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoGarantiaActionPerformed
        // Cuando se selecciona "No", desmarcar "Si"
    btnSiGarantia.setSelected(false);
    }//GEN-LAST:event_btnNoGarantiaActionPerformed

    private void btnSiGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiGarantiaActionPerformed
        // Cuando se selecciona "Si", desmarcar "No"
    btnNoGarantia.setSelected(false);
    }//GEN-LAST:event_btnSiGarantiaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro que desea salir de la aplicación?", 
            "Confirmar salida", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        try {
        double monto = Double.parseDouble(txtMonto.getText().trim());
        double ingresos = Double.parseDouble(txtIngresos.getText().trim());
        String proposito = txtProposito.getText().trim();

        // Validar que se haya seleccionado una opción de garantía
        if (!btnNoGarantia.isSelected() && !btnSiGarantia.isSelected()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione una opción de garantía", 
                "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean garantia = btnSiGarantia.isSelected();
        Persona persona = new Persona(monto, ingresos, proposito, garantia);
        boolean exito = persona.insertarPersona();
        
        if (exito) {
            // Avanzar a la siguiente vista
            PersonaVista02 vista02 = new PersonaVista02(persona);
            vista02.setVisible(true);
            this.dispose(); // Cierra la vista actual
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la solicitud.");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.");
    }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtPropositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPropositoActionPerformed
        // Validar datos antes de continuar
    if (getMonto() <= 0) {
        mostrarError("Ingrese un monto válido");
        return;
    }
    
    if (getIngresos() <= 0) {
        mostrarError("Ingrese ingresos mensuales válidos");
        return;
    }
    
    if (getProposito().trim().isEmpty()) {
        mostrarError("Ingrese el propósito del préstamo");
        return;
    }
    
    // Si todo está correcto, mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Solicitud procesada correctamente", 
        "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
    }//GEN-LAST:event_txtPropositoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
    btnVolver.addActionListener(e -> {
    txtMonto.setText("");
    txtIngresos.setText("");
    txtProposito.setText("");
    btnSiGarantia.setSelected(false);
    btnNoGarantia.setSelected(false);
});

    }//GEN-LAST:event_btnVolverActionPerformed


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
            java.util.logging.Logger.getLogger(PersonaVista01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonaVista01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonaVista01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonaVista01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PersonaVista01().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnContinuar;
    public javax.swing.JRadioButton btnNoGarantia;
    public javax.swing.JButton btnSalir;
    public javax.swing.JRadioButton btnSiGarantia;
    public javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    public javax.swing.JTextField txtIngresos;
    public javax.swing.JTextField txtMonto;
    public javax.swing.JTextField txtProposito;
    // End of variables declaration//GEN-END:variables
}
