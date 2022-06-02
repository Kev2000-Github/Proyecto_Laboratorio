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
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import modelos.Charla;
import vistas.swing.VentanaCharlas;


public class ControladorCharla extends ControladorGeneral implements ListSelectionListener{

    VentanaCharlas window;
    DaoFactory daoFactory;
    CharlaDao charlaDao;
    Map<String, String> charlaInfo;
    
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
        charlaDao = new CharlaDao();
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
        modelCharlas.setColumnCount(5);
        modelCharlas.setColumnIdentifiers(new Object[]{"Id", "Tema", "Lugar", "Organismo", "Fecha"});

        for (Charla c : ch_list) {
            
            CharlaDao charlaDao = new CharlaDao();
            Charla charla = charlaDao.get(c.getId());
            modelCharlas.addRow(new Object[]{Boolean.FALSE, c.getId(), 
                c.getTema(), c.getDireccion(), c.getOrganismo(), c.getTema()});
            
        }
        window.setModelServicio(modelCharlas);
    }
    
    public Map<String, String> getSelectedCharla(){
        int row = window.gettblCharlas().getSelectedRow();
        Map<String, String> rowInfo = new HashMap<String, String>();
        String charlaId = window.gettblCharlas().getValueAt(row, 0).toString();
        
        rowInfo.put("solicitudId",charlaId);

        return rowInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JButton")){
            JButton btn = (JButton) e.getSource();
            String name = btn.getName();
            if(name.equals("btnRegistrar")){
                if(window.gettblCharlas().getSelectedRow() != -1){
                    
                    //TODO: agregar logica de validacion por consulta (rs empty-> abre ventana de RegistrarAsistentes, else -> mensaje "ya los asistentes de esta charla fueron registrados")
                    //logica empty
                    window.dispose();
                    this.charlaInfo = getSelectedCharla();
                    router.notify(this, "go-detalleSolicitud");
                    
                    //logica full(ya registrados)
                    window.mostrarMensaje("Los asistentes a esta charla ya fueron registrados");
                }
                else{
                    window.mostrarMensaje("No se ha seleccionado ninguna solicitud");
                }
            }
        }
    }
    
    
     
    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                router.notify(this, "go-home");
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

    

    

    
