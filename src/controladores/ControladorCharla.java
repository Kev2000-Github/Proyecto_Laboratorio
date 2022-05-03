/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import DAO.CharlaDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import modelos.Charla;
import modelos.Usuario;
import vistas.swing.VentanaCharlas;


public class ControladorCharla extends ControladorGeneral implements ListSelectionListener{
    //Crear Variable Ventana y DaoFactory
    VentanaCharlas ventana;
    DaoFactory daoFactory;
    

        public ControladorCharla(Usuario user) {
        super(user);
        daoFactory = new DaoFactory();
        ventana = new VentanaCharlas(this, this, this);

        ventana.setVisible(true);

        initCrear();
    }
    
   public void initCrear() {
        //EJECUTAR AQUI TODOS LOS METODOS LOCALES!!!!!!!!!!!!
        fillDefaultComboBoxValues();
        
        //CONECCION DE DAO - VENTANA
        //String selection = ((ComboboxItem) ventana.getCmBoxTipodeCharla().getSelectedItem()).toString();
        fillCharlas("Finalizadas, sin Registrar");

    }     
   //=*
    public void fillCharlas(String selection) {
        DefaultTableModel modelCharlas = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        
        CharlaDao charlaDao = new CharlaDao();
        List<Charla> ch_list = charlaDao.getCharlasByType(selection);
        modelCharlas.setColumnCount(5);
        modelCharlas.setColumnIdentifiers(new Object[]{"Id", "Tema", "Lugar", "Organismo", "Fecha"});

        for (Charla c : ch_list) {
            modelCharlas.addRow(new Object[]{Boolean.FALSE, c.getId(), c.getTema(), c.getDireccion(), c.getOrganismo(), (c.getFecha()).toString()});
        }
        ventana.setModelTablaCharla(modelCharlas);
    }
     
    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void fillDefaultComboBoxValues() {

        DefaultComboBoxModel cmbTipoCharlas = new DefaultComboBoxModel();
        List<String> tipos = new ArrayList();
        tipos.add("Finalizadas, sin Registrar");
        tipos.add("Por empezar, sin Registrar");
        tipos.add("Finalizadas, Registradas");
        
        ventana.setModelTipoCharla(cmbTipoCharlas);
    }

    public void mouseClicked(MouseEvent e) {
            String source = e.getSource().getClass().getName();
            if(source.equals("javax.swing.JLabel")){
                JLabel lbl = (JLabel)e.getSource();
                if(lbl.getName() == "goHome"){
                    ventana.dispose();
                    new ControladorHome(user);
                }
            }



     }
    
    @Override
    public void mousePressed(MouseEvent e) {

        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

    

    

    
