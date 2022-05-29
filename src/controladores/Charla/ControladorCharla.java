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
import modelos.Charla;
import vistas.swing.VentanaCharlas;


/**
 *
 * @author sarah
 */
public class ControladorCharla extends ControladorGeneral implements ListSelectionListener{

    VentanaCharlas window;
    DaoFactory daoFactory;
    
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
        CharlaDao charlaDao = new CharlaDao();
        List<Charla> ch_list = charlaDao.getCharlasByType();
        modelCharlas.setColumnCount(5);
        modelCharlas.setColumnIdentifiers(new Object[]{"Seleccionar", "ID", "Nombre", "Costo"});

        for (Charla c : ch_list) {
            modelCharlas.addRow(new Object[]{Boolean.FALSE, c.getId(), c.getTema(), c.getDireccion(), c.getOrganismo(), c.getFecha()});
        }
        window.setModelTablaCharla(modelCharlas);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
