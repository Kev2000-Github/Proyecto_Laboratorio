/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import vistas.general.VentanaGeneral;

/**
 *
 * @author sarah
 */
public class VentanaRegistrarAsistentes extends VentanaGeneral {

    /**
     * Creates new form VentanaRegistrarAsistentes
     * @param accion
     * @param ml
     */
     
    public VentanaRegistrarAsistentes(ActionListener accion, MouseListener ml) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarActionListener(accion);
        this.agregarMouseListener(ml);
        topMenu1.setMenuFunctions(this, ml, "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topMenu1 = new vistas.swing.componentes.topMenu.topMenu();
        goBackLbl = new javax.swing.JLabel();
        lblIdCharla = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblCiDelAsistente = new javax.swing.JLabel();
        jtfCedula = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(topMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, -1));

        goBackLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/swing/componentes/images/return.png"))); // NOI18N
        goBackLbl.setName("goBack"); // NOI18N
        getContentPane().add(goBackLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        lblIdCharla.setFont(new java.awt.Font("Liberation Serif", 1, 14)); // NOI18N
        lblIdCharla.setForeground(new java.awt.Color(255, 255, 255));
        lblIdCharla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblIdCharla.setText("id");
        lblIdCharla.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(lblIdCharla, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        lblTitle.setFont(new java.awt.Font("Liberation Serif", 1, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Registrar Asistentes a Charla");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        lblCiDelAsistente.setForeground(new java.awt.Color(255, 255, 255));
        lblCiDelAsistente.setText("C.I. del Asistente:");
        getContentPane().add(lblCiDelAsistente, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jtfCedula.setToolTipText("");
        getContentPane().add(jtfCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 120, -1));

        btnRegistrar.setBackground(new java.awt.Color(110, 217, 161));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(0, 37, 35));
        btnRegistrar.setText("Registrar");
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, -1));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 180));

        pack();
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
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistrarAsistentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VentanaRegistrarAsistentes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel goBackLbl;
    private javax.swing.JTextField jtfCedula;
    private javax.swing.JLabel lblCiDelAsistente;
    private javax.swing.JLabel lblIdCharla;
    private javax.swing.JLabel lblTitle;
    private vistas.swing.componentes.topMenu.topMenu topMenu1;
    // End of variables declaration//GEN-END:variables

    private void agregarActionListener(ActionListener accion) {
        btnRegistrar.addActionListener(accion);
    }

    private void agregarMouseListener(MouseListener ml) {
        goBackLbl.addMouseListener(ml);
    }
    
    public JButton getBtnRegistrar(){
        return btnRegistrar;
    }
    
    public JTextField getEntryCedula(){
        return jtfCedula;
    }
    
    public void setEntryCedula(String v){
        jtfCedula.setText(v);
    }
    
    public JLabel getLblIdCharla(){
        return lblIdCharla;
    }
    
    public void setIdCharla(String id){
        lblIdCharla.setText(id);
    }
}
