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
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Map;

import modelos.Charla;
import vistas.swing.VentanaCharla;
import java.util.Date;

public class ControladorCharla extends ControladorGeneral implements ListSelectionListener{

    VentanaCharla window;
    DaoFactory daoFactory;
    CharlaDao charlaDao;
    Map<String, String> charlaInfo;
    
    public ControladorCharla(IRouter router) {
        super("charla", router);
        daoFactory = new DaoFactory();
        charlaDao = new CharlaDao();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }
    
    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaCharla(this, this, this);
        window.setVisible(true);
        fillCharlas();
    }

    public void closeGUI(){
        window.dispose();
    }
 
    public void fillCharlas() {
        DefaultTableModel modelCharlas = new DefaultTableModel();
        List<Charla> ch_list = charlaDao.getAll();
        modelCharlas.setColumnCount(5);
        modelCharlas.setColumnIdentifiers(new Object[]{"Id", "Tema", "Lugar", "Organismo", "Fecha"});

        for (Charla c : ch_list) {
            modelCharlas.addRow(new Object[]{ c.getId(), 
                c.getTema(), c.getDireccion(), c.getOrganismo(), c.getFecha()});
        }
        window.setModelServicio(modelCharlas);
    }
    
    public void fillBusqueda(String fecha_f, String fecha_t) {
        DefaultTableModel modelCharlas = new DefaultTableModel();
        List<Charla> ch_list = charlaDao.getCharlasByDate(fecha_f,fecha_t);
        modelCharlas.setColumnCount(5);
        modelCharlas.setColumnIdentifiers(new Object[]{"Id", "Tema", "Lugar", "Organismo", "Fecha"});

        for (Charla c : ch_list) {
            modelCharlas.addRow(new Object[]{ c.getId(), 
                c.getTema(), c.getDireccion(), c.getOrganismo(), c.getFecha()});
        }
        window.setModelServicio(modelCharlas);
    }

    public String getIdSelectedCharla(){
        int row = window.gettblCharlas().getSelectedRow();
        String charlaId = window.gettblCharlas().getValueAt(row, 0).toString();

        return charlaId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var source = e.getSource();
        if (source == window.getBtnRegistrar()) {
            if(window.gettblCharlas().getSelectedRow() != -1){
                String idCharlaSelected = getIdSelectedCharla();
                if(charlaDao.charlaIsRegistered(idCharlaSelected)== false){                    
                    router.notify(this, "update-registrarAsistentes-" + idCharlaSelected);
                }
                else{    
                    mostrarMensaje("Los asistentes a esta charla ya fueron registrados");
                }
            }
            else{
                mostrarMensaje("No se ha seleccionado ninguna solicitud");
            }
        }
        if(source == window.getBtnBuscar()){
            Date fecha_f = window.getDChooserFrom().getDate();
            Date fecha_t = window.getDChooserTo().getDate();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            if((fecha_f != null) && (fecha_t != null)){
                if(fecha_f.before(fecha_t)){
                    String f = formato.format(fecha_f);
                    String t = formato.format(fecha_t);                
                    fillBusqueda(f,t);
                }
                else{
                    window.mostrarMensaje("La primera fecha seleccionada debe ser anterior a la segunda");
                }
            }
            else{
                window.mostrarMensaje("Debe llenar ambas fechas para hacer la busqueda");
            }
        }
        if(source == window.getBtnCrearCharla()){
            router.notify(this, "go-addCharla");
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) { 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
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

    

    

    
