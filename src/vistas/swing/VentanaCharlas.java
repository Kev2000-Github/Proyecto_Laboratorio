/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas.swing;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import vistas.general.VentanaGeneral;
import vistas.general.ComboboxItem;
import vistas.general.MyTableModel;

public class VentanaCharlas extends VentanaGeneral {

    //VER TIPOS Y FUNCION DE ACCION LISTENERS

    /**
     @param accion
     * @param listSelection
     * @param ml
     */
    public VentanaCharlas(ActionListener accion, ListSelectionListener listSelection, MouseListener ml) {
        initComponents();
        setLocationRelativeTo(null);
        this.agregarActionListener(accion);
        this.agregarSelectionListener(listSelection);
        this.agregarMouseListener(ml);
        topMenu.setMenuFunctions(this, "");


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        Background = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblTipodeCharla = new javax.swing.JLabel();
        CmBoxTipodeCharla = new javax.swing.JComboBox<>();
        BtnMostrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        charlas = new javax.swing.JTable();
        BtnRegistrarAsistentes = new javax.swing.JButton();
        topMenu = new vistas.swing.componentes.topMenuLogin.topMenuLogin();
        backgroundImage1 = new vistas.swing.componentes.backgroundImage.backgroundImage();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTitle.setText("Charlas");
        Background.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 52, -1, -1));

        lblTipodeCharla.setText("Tipo de Charla");
        Background.add(lblTipodeCharla, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, 20));

        CmBoxTipodeCharla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmBoxTipodeCharlaActionPerformed(evt);
            }
        });
        Background.add(CmBoxTipodeCharla, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 200, -1));

        BtnMostrar.setText("Mostrar");
        Background.add(BtnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        charlas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
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
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(charlas);

        Background.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 142, 340, 220));

        BtnRegistrarAsistentes.setText("Registrar Asistentes");
        Background.add(BtnRegistrarAsistentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, -1, -1));

        topMenu.setToolTipText("");
        Background.add(topMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, -1));
        Background.add(backgroundImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 480));

        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CmBoxTipodeCharlaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmBoxTipodeCharlaActionPerformed
        // TODO add your handling code here:
        //getting selected value:
        String seleccion = CmBoxTipodeCharla.getSelectedItem().toString();
        
        //TODO Hacer logica de actualizar tabla segun seleccion
        //caso 1: Charlas en status finished, sin participantes
        //caso 2: Charlas en status pending, sin participantes
        //caso 3: Charlas en status finished, con participantes
    }//GEN-LAST:event_CmBoxTipodeCharlaActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCharlas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCharlas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCharlas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCharlas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new VentanaCharlas().setVisible(true);
            }
        });
    }

    private void agregarActionListener(ActionListener accion){
        BtnMostrar.addActionListener(accion);
        BtnRegistrarAsistentes.addActionListener(accion);
        CmBoxTipodeCharla.addActionListener(accion); 
    }
    
    private void agregarMouseListener(MouseListener ml) {
        charlas.addMouseListener(ml);
    }
    
    private void agregarSelectionListener(ListSelectionListener lsl) {
        charlas.getSelectionModel().addListSelectionListener(lsl);
    }
    
    public JTable getCharlas() {
            return charlas;
        }
    public JButton getBtnMostrar(){
            return BtnMostrar;
    }
    public JButton getBtnRegistrarAsistentes(){
            return BtnRegistrarAsistentes;
    }
    public JComboBox<ComboboxItem> getCmBoxTipodeCharla(){
            return CmBoxTipodeCharla;
    }

    public void setCmBoxTipodeCharla(JComboBox<ComboboxItem> tipocharla) {
            this.CmBoxTipodeCharla = tipocharla;
        }

    public void setModelTipoCharla(DefaultComboBoxModel model) {
        this.CmBoxTipodeCharla.setModel(model);
    }
    
    public void setModelTablaCharla(DefaultTableModel model) {
    this.charlas.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JButton BtnMostrar;
    private javax.swing.JButton BtnRegistrarAsistentes;
    private javax.swing.JComboBox<ComboboxItem> CmBoxTipodeCharla;
    private vistas.swing.componentes.backgroundImage.backgroundImage backgroundImage1;
    private javax.swing.JTable charlas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTipodeCharla;
    private javax.swing.JLabel lblTitle;
    private vistas.swing.componentes.topMenuLogin.topMenuLogin topMenu;
    // End of variables declaration//GEN-END:variables
}
