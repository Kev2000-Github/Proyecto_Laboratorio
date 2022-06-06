/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import vistas.general.VentanaGeneral;
import java.awt.event.MouseListener;


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
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(topMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 30));

        usuarios.setText("Usuarios");
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });
        getContentPane().add(usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 117, 35));

        beneficiarios.setText("Beneficiarios");
        getContentPane().add(beneficiarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 117, 38));

        fundaciones.setText("Fundaciones");
        fundaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fundacionesActionPerformed(evt);
            }
        });
        getContentPane().add(fundaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 117, 35));

        empleados.setText("Empleados");
        empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosActionPerformed(evt);
            }
        });
        getContentPane().add(empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 117, 35));

        charlas.setText("Charlas");
        charlas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                charlasActionPerformed(evt);
            }
        });
        getContentPane().add(charlas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 117, 35));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIENVENIDO,");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        goBackLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/swing/componentes/images/return.png"))); // NOI18N
        goBackLbl.setName("goBack"); // NOI18N
        getContentPane().add(goBackLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 44, -1, -1));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 320));

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
    private vistas.swing.componentes.topMenu.topMenu topMenu;
    private javax.swing.JButton usuarios;
    // End of variables declaration//GEN-END:variables
}
