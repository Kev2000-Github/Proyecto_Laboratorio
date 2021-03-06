/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import vistas.general.VentanaGeneral;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import vistas.general.ComboboxItem;


/**
 *
 * @author juanperez
 */
public class VentanaRegistros extends VentanaGeneral {

    /**
     * Creates new form VentanaHome
     * @param accion
     */
    public VentanaRegistros(ActionListener accion, MouseListener ml) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarListener(accion);
        this.agregarMouseListener(ml);
        topMenu.setMenuFunctions(this, ml, "Registros");
    }
    
    private void agregarListener(ActionListener accion){
        empleados.addActionListener(accion);
        beneficiarios.addActionListener(accion);
        charlas.addActionListener(accion);
        fundaciones.addActionListener(accion);
        servicios.addActionListener(accion);
        usuarios.addActionListener(accion);
    }

    private void agregarMouseListener(MouseListener ml) {
        goBackLbl.addMouseListener(ml);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topMenu = new vistas.swing.componentes.topMenu.topMenu();
        usuarios = new javax.swing.JButton();
        beneficiarios = new javax.swing.JButton();
        fundaciones = new javax.swing.JButton();
        empleados = new javax.swing.JButton();
        charlas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        goBackLbl = new javax.swing.JLabel();
        servicios = new javax.swing.JButton();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(topMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 30));

        usuarios.setBackground(new java.awt.Color(110, 217, 161));
        usuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        usuarios.setForeground(new java.awt.Color(0, 37, 35));
        usuarios.setText("Usuarios");
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });
        getContentPane().add(usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 117, 35));

        beneficiarios.setBackground(new java.awt.Color(110, 217, 161));
        beneficiarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        beneficiarios.setForeground(new java.awt.Color(0, 37, 35));
        beneficiarios.setText("Beneficiarios");
        getContentPane().add(beneficiarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 117, 38));

        fundaciones.setBackground(new java.awt.Color(110, 217, 161));
        fundaciones.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fundaciones.setForeground(new java.awt.Color(0, 37, 35));
        fundaciones.setText("Fundaciones");
        fundaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fundacionesActionPerformed(evt);
            }
        });
        getContentPane().add(fundaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 117, 35));

        empleados.setBackground(new java.awt.Color(110, 217, 161));
        empleados.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        empleados.setForeground(new java.awt.Color(0, 37, 35));
        empleados.setText("Empleados");
        empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosActionPerformed(evt);
            }
        });
        getContentPane().add(empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 117, 35));

        charlas.setBackground(new java.awt.Color(110, 217, 161));
        charlas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        charlas.setForeground(new java.awt.Color(0, 37, 35));
        charlas.setText("Charlas");
        charlas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                charlasActionPerformed(evt);
            }
        });
        getContentPane().add(charlas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 117, 35));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIENVENIDO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        goBackLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/swing/componentes/images/return.png"))); // NOI18N
        goBackLbl.setName("goBack"); // NOI18N
        getContentPane().add(goBackLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 44, -1, -1));

        servicios.setBackground(new java.awt.Color(110, 217, 161));
        servicios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        servicios.setForeground(new java.awt.Color(0, 37, 35));
        servicios.setText("Servicios");
        servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviciosActionPerformed(evt);
            }
        });
        getContentPane().add(servicios, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 117, 35));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 380));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleadosActionPerformed

    private void charlasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_charlasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_charlasActionPerformed
    private void fundacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fundacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fundacionesActionPerformed

    private void serviciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serviciosActionPerformed
    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariosActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(VentanaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new VentanaHome().setVisible(true);
            }
        });
    }

    public JButton getBeneficiarios() {
        return beneficiarios;
    }

    public JButton getEmpleados() {
        return empleados;
    }
    
    public JButton getFundaciones() {
        return fundaciones;
    }
    
    public JButton getServicios() {
        return servicios;
    }
 
    public JButton getUsuarios() {
        return usuarios;
    }

    public JButton getCharlas() {
        return charlas;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JButton beneficiarios;
    private javax.swing.JButton charlas;
    private javax.swing.JButton empleados;
    private javax.swing.JButton fundaciones;
    private javax.swing.JLabel goBackLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton servicios;
    private vistas.swing.componentes.topMenu.topMenu topMenu;
    private javax.swing.JButton usuarios;
    // End of variables declaration//GEN-END:variables
}
