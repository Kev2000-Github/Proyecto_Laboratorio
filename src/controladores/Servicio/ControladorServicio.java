/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Servicio;

import DAO.general.DaoFactory;
import DAO.FundacionDao;
import DAO.ServicioDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelos.Fundacion;
import modelos.Servicio;
import utils.Constants;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaGestionarBackOffice;
import vistas.swing.VentanaGestionarServicios;

/**
 *
 * @author prometheus
 */
public class ControladorServicio extends ControladorGeneral implements ListSelectionListener {
    
    VentanaGestionarServicios window;
    DaoFactory daoFactory;
    ServicioDao  servicioDao;
    String fundacionId;
    
    public ControladorServicio(IRouter router){
        super("servicio", router);
        daoFactory = new DaoFactory();
        servicioDao = new ServicioDao(); 
    }
    
      public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaGestionarServicios("Gestionar Servicios", this, this);
        window.setVisible(true);
        fundacionId = "1";
        fillFundaciones();
        fillServicios(fundacionId);
    }
    
     public void closeGUI(){
        window.dispose();
    }
     
    public void fillServicios(String fundacionId) {
        DefaultTableModel modelServicios = new DefaultTableModel();
        ServicioDao servicioDao = new ServicioDao();
        List<Map<String, String>> sList = servicioDao.getAllWithCosto(fundacionId);
        modelServicios.setColumnCount(4);
        modelServicios.setColumnIdentifiers(new Object[]{"Id", "FundacionId", "Nombre", "Tipo","Costo"});
        for (Map<String, String> serv : sList) {
            modelServicios.addRow(new Object[]{
                serv.get("id"),
                serv.get("fundacionId"),
                serv.get("nombre"),
                serv.get("tipo"),
                serv.get("costo")
            });
        }
        window.setModeloTabla(modelServicios);
    }

    public void fillFundaciones() {
        DefaultComboBoxModel<ComboboxItem> modelFundaciones = new DefaultComboBoxModel<ComboboxItem>();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundaciones = fundacionDao.getAll();
        fundacionId = fundaciones.get(0).getId();
        for (Fundacion fund : fundaciones) {
            modelFundaciones.addElement(
                    new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundaciones(modelFundaciones);
    }
    
    @Override
    public void valueChanged(ListSelectionEvent l) {
     
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();
        if (source == window.getEliminar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String servicioId = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("ServicioId: " + servicioId);
                Servicio serv = new Servicio();
                serv.setId(servicioId);
                servicioDao.delete(serv);
                fillServicios(fundacionId);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }

        }
        if (source == window.getEditar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String servicioId = window.getTable().getModel().getValueAt(row, 0).toString();
                String fundacionId = window.getTable().getModel().getValueAt(row, 1).toString();
                System.out.println("ServicioId: " + servicioId);
                router.notify(this, "update-updateServicio-" + servicioId + '/' + fundacionId);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getCrear()) {
            router.notify(this, "go-addServicio");
        }
        if (source == window.getFundacion()){
            String fundacionId = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
            fillServicios(fundacionId);
        }
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

  @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
    
}
