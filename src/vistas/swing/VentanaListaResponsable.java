/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;
import vistas.general.VentanaGeneral;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author prometheus
 */
public class VentanaListaResponsable extends VentanaGeneral {

    /**
     * Creates new form VentanaListaResponsable
     */
    public VentanaListaResponsable(ActionListener accion, MouseListener ml) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarMouseListener(ml);
        topMenu1.setMenuFunctions(this, ml, "Listado de Responsables");
    }

    private void agregarMouseListener(MouseListener ml) {
        //fundacioncb.addMouseListener(ml);\
        goBackLbl.addMouseListener(ml);
    }
    
  
    
    public void setModelResponsables(DefaultTableModel model){
        this.tablaResponsables.setModel(model);
    }
    
    private void agregarActionListener(ActionListener accion) {
           
    }
    
     public JTable getResponsables(){
        return tablaResponsables;
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaResponsables = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        topMenu1 = new vistas.swing.componentes.topMenu.topMenu();
        goBackLbl = new javax.swing.JLabel();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaResponsables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Solicitud", "Empleado"
            }
        ));
        jScrollPane1.setViewportView(tablaResponsables);

        jScrollPane2.setViewportView(jScrollPane1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 194));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 73, -1, -1));

        jLabel2.setFont(new java.awt.Font("Liberation Serif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lista de Responsables por solicitud");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));
        getContentPane().add(topMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, -1));

        goBackLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/swing/componentes/images/return.png"))); // NOI18N
        goBackLbl.setName("goBack"); // NOI18N
        getContentPane().add(goBackLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 44, -1, -1));
        getContentPane().add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 320));

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
            java.util.logging.Logger.getLogger(VentanaListaResponsable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaListaResponsable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaListaResponsable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaListaResponsable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JLabel goBackLbl;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaResponsables;
    private vistas.swing.componentes.topMenu.topMenu topMenu1;
    // End of variables declaration//GEN-END:variables
}
