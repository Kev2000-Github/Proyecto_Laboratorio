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
 * @author ASRock
 */
public class VentanaGestionarSolicitud extends VentanaGeneral {
    String selectedRow;
    /**
     * Creates new form VentanaGestionarSolicitud
     */
    public VentanaGestionarSolicitud(ActionListener accion, MouseListener ml) {
        initComponents();
        topMenu1.setMenuFunctions(this, ml, "");
        this.agregarActionListener(accion);
    }

    public void setModelSolicitudes(DefaultTableModel model){
        this.tableSolicitudes.setModel(model);
    }

    private void agregarActionListener(ActionListener accion) {
        solicitudBtn.addActionListener(accion);
    }

    public JTable getSolicitudes(){
        return tableSolicitudes;
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSolicitudes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        solicitudBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(topMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, -1));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Solicitud", "Fundacion", "Beneficiario", "Encargado", "Prioridad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableSolicitudes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 520, 314));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 386, -1, -1));

        solicitudBtn.setText("ver solicitud");
        solicitudBtn.setName("verSolicitud"); // NOI18N
        solicitudBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitudBtnActionPerformed(evt);
            }
        });
        jPanel1.add(solicitudBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 88, 610, 360));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Solicitudes");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));
        jPanel3.add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, -1, 560));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 640, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void solicitudBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitudBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_solicitudBtnActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaGestionarSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGestionarSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGestionarSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGestionarSolicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VentanaGestionarSolicitud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton solicitudBtn;
    private javax.swing.JTable tableSolicitudes;
    private vistas.swing.componentes.topMenu.topMenu topMenu1;
    // End of variables declaration//GEN-END:variables
}
