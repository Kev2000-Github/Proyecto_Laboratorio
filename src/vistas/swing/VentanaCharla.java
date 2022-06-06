/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;

import vistas.general.VentanaGeneral;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

/**
 *
 * @author ASRock
 */
public class VentanaCharla extends VentanaGeneral {

    /**
     * Creates new form VentanaGestionarSolicitud
     */
    public VentanaCharla(ActionListener accion, ListSelectionListener listSelection, MouseListener ml) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarActionListener(accion);
        this.agregarSelectionListener(listSelection);
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

        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();
        topMenu1 = new vistas.swing.componentes.topMenu.topMenu();
        crearCharlaBtn = new javax.swing.JButton();
        lblCharlas = new javax.swing.JLabel();
        lblFiltrar = new javax.swing.JLabel();
        dChFrom = new com.toedter.calendar.JDateChooser();
        lblAl = new javax.swing.JLabel();
        dChTo = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCharlas = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        backgroundImage3 = new vistas.swing.componentes.backgroundImage.backgroundImage();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(topMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        crearCharlaBtn.setBackground(new java.awt.Color(153, 255, 153));
        crearCharlaBtn.setText("Crear Charla");
        getContentPane().add(crearCharlaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, -1, -1));

        lblCharlas.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblCharlas.setForeground(new java.awt.Color(255, 255, 255));
        lblCharlas.setText("Charlas");
        getContentPane().add(lblCharlas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        lblFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        lblFiltrar.setText("Filtrar por Fecha:");
        getContentPane().add(lblFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        getContentPane().add(dChFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        lblAl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAl.setForeground(new java.awt.Color(255, 255, 255));
        lblAl.setText("al");
        getContentPane().add(lblAl, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));
        getContentPane().add(dChTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, -1));

        tblCharlas.setBackground(new java.awt.Color(204, 255, 204));
        tblCharlas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Tema", "Lugar", "Organismo", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCharlas);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 200));

        btnBuscar.setBackground(new java.awt.Color(153, 255, 153));
        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        btnRegistrar.setBackground(new java.awt.Color(153, 255, 153));
        btnRegistrar.setText("Registrar Asistentes");
        getContentPane().add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, -1, -1));
        getContentPane().add(backgroundImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 480));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 495, 330));

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
            java.util.logging.Logger.getLogger(VentanaCharla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCharla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCharla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCharla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage3;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton crearCharlaBtn;
    private com.toedter.calendar.JDateChooser dChFrom;
    private com.toedter.calendar.JDateChooser dChTo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAl;
    private javax.swing.JLabel lblCharlas;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JTable tblCharlas;
    private vistas.swing.componentes.topMenu.topMenu topMenu1;
    // End of variables declaration//GEN-END:variables

    
    private void agregarActionListener(ActionListener accion) {
        btnRegistrar.addActionListener(accion);
        btnBuscar.addActionListener(accion);
        crearCharlaBtn.addActionListener(accion);

    }

    private void agregarSelectionListener(ListSelectionListener lsl) {
        tblCharlas.getSelectionModel().addListSelectionListener(lsl);
    }

    private void agregarMouseListener(MouseListener ml) {
        tblCharlas.addMouseListener(ml);
    }
    
    public JTable gettblCharlas(){
        return tblCharlas;
    }
    
    public JButton getBtnBuscar(){
        return btnBuscar;
    }
    
    public JButton getBtnRegistrar(){
        return btnRegistrar;
    }

    public JButton getBtnCrearCharla(){
        return crearCharlaBtn;
    }
    
    public void setModelServicio(DefaultTableModel model) {
        this.tblCharlas.setModel(model);
    }
    
    public JDateChooser getDChooserFrom(){
        return dChFrom;
    }
    
    public JDateChooser getDChooserTo(){
        return dChTo;
    }
}