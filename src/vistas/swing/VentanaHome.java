/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import vistas.general.VentanaGeneral;

/**
 *
 * @author juanperez
 */
public class VentanaHome extends VentanaGeneral {

    /**
     * Creates new form VentanaHome
     * @param accion
     */
    public VentanaHome(ActionListener accion) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarListener(accion);
        topMenuLogin.setMenuFunctions(this, "");
    }
    
    private void agregarListener(ActionListener accion){
        crear_solicitud.addActionListener(accion);
        gestionar_solicitud.addActionListener(accion);
        updateFondos.addActionListener(accion);
        back_office1.addActionListener(accion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        updateFondos = new javax.swing.JButton();
        crear_solicitud = new javax.swing.JButton();
        gestionar_solicitud = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        topMenuLogin = new vistas.swing.componentes.topMenuLogin.topMenuLogin();
        back_office1 = new javax.swing.JButton();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updateFondos.setText("asignar partida");
        updateFondos.setName("asignarPartida"); // NOI18N
        updateFondos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateFondosActionPerformed(evt);
            }
        });
        getContentPane().add(updateFondos, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 142, 144, 37));

        crear_solicitud.setText("Crear Solicitud");
        crear_solicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_solicitudActionPerformed(evt);
            }
        });
        getContentPane().add(crear_solicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 87, 155, 37));

        gestionar_solicitud.setText("Gestionar Solicitud");
        gestionar_solicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionar_solicitudActionPerformed(evt);
            }
        });
        getContentPane().add(gestionar_solicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 87, 144, 37));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Inicio");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 38, -1, -1));
        getContentPane().add(topMenuLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, -1));

        back_office1.setText("BackOffice");
        getContentPane().add(back_office1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 142, 155, 37));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crear_solicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_solicitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crear_solicitudActionPerformed

    private void gestionar_solicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionar_solicitudActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gestionar_solicitudActionPerformed


    private void updateFondosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateFondosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateFondosActionPerformed


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
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new VentanaHome().setVisible(true);
            }
        });
    }

    public JButton getBack_office() {
        return back_office1;
    }

    public JButton getCrear_solicitud() {
        return crear_solicitud;
    }

    public JButton getGestionar_solicitud() {
        return gestionar_solicitud;
    }
    
    public JButton getAsignar_fondos() {
        return updateFondos;
    }


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_office1;
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JButton crear_solicitud;
    private javax.swing.JButton gestionar_solicitud;
    private javax.swing.JLabel jLabel1;
    private vistas.swing.componentes.topMenuLogin.topMenuLogin topMenuLogin;
    private javax.swing.JButton updateFondos;
    // End of variables declaration//GEN-END:variables
}
