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

        topMenu = new vistas.swing.componentes.topMenu();
        beneficiarios = new javax.swing.JButton();
        empleados = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        goBackLbl = new javax.swing.JLabel();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(topMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, -1));

        beneficiarios.setText("Beneficiarios");
        getContentPane().add(beneficiarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 117, 38));

        empleados.setText("Empleados");
        empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleadosActionPerformed(evt);
            }
        });
        getContentPane().add(empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 117, 35));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("BIENVENIDO,");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        goBackLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/swing/componentes/images/return.png"))); // NOI18N
        goBackLbl.setName("goBack"); // NOI18N
        getContentPane().add(goBackLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 44, -1, -1));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleadosActionPerformed

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
    

 

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JButton beneficiarios;
    private javax.swing.JButton empleados;
    private javax.swing.JLabel goBackLbl;
    private javax.swing.JLabel jLabel1;
    private vistas.swing.componentes.topMenu topMenu;
    // End of variables declaration//GEN-END:variables
}
