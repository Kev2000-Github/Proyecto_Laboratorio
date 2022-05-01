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
import vistas.general.MetodosGenerales;
import vistas.general.ComboboxItem;
import vistas.general.MyTableModel;

public class VentanaCharlas extends MetodosGenerales {

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
        topMenu = new vistas.swing.componentes.topMenuLogin();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTitle.setText("Charlas");

        lblTipodeCharla.setText("Tipo de Charla");

        CmBoxTipodeCharla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmBoxTipodeCharlaActionPerformed(evt);
            }
        });

        BtnMostrar.setText("Mostrar");

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

        BtnRegistrarAsistentes.setText("Registrar Asistentes");

        topMenu.setToolTipText("");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addComponent(lblTipodeCharla)
                        .addGap(14, 14, 14)
                        .addComponent(CmBoxTipodeCharla, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(BtnMostrar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(lblTitle))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(BtnRegistrarAsistentes)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(topMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipodeCharla)
                    .addComponent(CmBoxTipodeCharla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnMostrar))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnRegistrarAsistentes)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 375, Short.MAX_VALUE)
        );

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
    private javax.swing.JTable charlas;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTipodeCharla;
    private javax.swing.JLabel lblTitle;
    private vistas.swing.componentes.topMenuLogin topMenu;
    // End of variables declaration//GEN-END:variables
}
