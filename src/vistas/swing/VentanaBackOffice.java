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
public class VentanaBackOffice extends VentanaGeneral {

    /**
     * Creates new form VentanaHome
     * @param accion
     */
    public VentanaBackOffice(ActionListener accion, MouseListener ml) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarListener(accion);
        this.agregarMouseListener(ml);
        topMenu.setMenuFunctions(this, ml, "Backoffice");
    }
    
    private void agregarListener(ActionListener accion){
        registros.addActionListener(accion);
        reportes.addActionListener(accion);
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

        registros = new javax.swing.JButton();
        reportes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        goBackLbl = new javax.swing.JLabel();
        topMenu = new vistas.swing.componentes.topMenu.topMenu();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        registros.setText("Registros");
        getContentPane().add(registros, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 117, 38));

        reportes.setText("Reportes");
        getContentPane().add(reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 117, 35));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIENVENIDO,");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        goBackLbl.setForeground(new java.awt.Color(255, 255, 255));
        goBackLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/swing/componentes/images/return.png"))); // NOI18N
        goBackLbl.setName("goBack"); // NOI18N
        getContentPane().add(goBackLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));
        getContentPane().add(topMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 30));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 300));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaBackOffice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBackOffice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBackOffice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBackOffice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new VentanaHome().setVisible(true);
            }
        });
    }

    public JButton getRegistros() {
        return registros;
    }

    public JButton getReportes() {
        return reportes;
    }

    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JLabel goBackLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton registros;
    private javax.swing.JButton reportes;
    private vistas.swing.componentes.topMenu.topMenu topMenu;
    // End of variables declaration//GEN-END:variables
}
