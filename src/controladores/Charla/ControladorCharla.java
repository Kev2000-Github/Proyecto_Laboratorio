/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Charla;

import DAO.CharlaDao;
import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

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
import vistas.swing.VentanaCharlas;


public class ControladorCharla extends ControladorGeneral implements ListSelectionListener{

    VentanaCharlas window;
    DaoFactory daoFactory;
    CharlaDao charlaDao;
    
    public ControladorCharla(IRouter router) {
        super("charla", router);
        daoFactory = new DaoFactory();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }
    
    public void initGUI(){
        window = new VentanaCharlas(this, this, this);
        window.setVisible(true);
        fillCharlas();
    }

    public void closeGUI(){
        window.dispose();
    }
 
    //TRAER EL/LOS PARAM/S AL METODO TAMBIEN:
    public void fillCharlas() {
        DefaultTableModel modelCharlas = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        List<Charla> ch_list = charlaDao.getCharlasPorEmpezar();
        modelCharlas.setColumnCount(6);
        modelCharlas.setColumnIdentifiers(new Object[]{"Id", "Tema", "Lugar", "Organismo", "Fecha"});

        for (Charla c : ch_list) {
            
            CharlaDao charlaDao = new CharlaDao();
            Charla charla = charlaDao.get(c.getId());
            modelCharlas.addRow(new Object[]{Boolean.FALSE, c.getId(), 
                c.getTema(), c.getDireccion(), c.getOrganismo(), c.getTema()});
        }
        window.setModelTablaCharla(modelCharlas);
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

    

    

    
