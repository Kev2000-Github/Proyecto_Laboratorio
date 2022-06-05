/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Servicio;

import DAO.FundacionDao;
import DAO.general.DaoFactory;
import DAO.ServicioDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Fundacion;
import modelos.Servicio;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaGestionarBackOffice;

/**
 *
 * @author prometheus
 */
public class ControladorServicio extends ControladorGeneral implements ListSelectionListener {
    
    VentanaGestionarBackOffice window;
    DaoFactory daoFactory;
    ServicioDao  servicioDao;
    
    public ControladorServicio(IRouter router){
        super("servicio", router);
        daoFactory = new DaoFactory();
        servicioDao = new ServicioDao();   
    }
    
      public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaGestionarBackOffice("Gestionar Servicios", this, this);
        window.setVisible(true);
        fillServicios();
    }
    
     public void closeGUI(){
        window.dispose();
    }
     
    public void fillServicios() {
       DefaultTableModel modelServicios = new DefaultTableModel();
        ServicioDao servicioDao = new ServicioDao();
        List<Servicio> fList = servicioDao.getAll();
        modelServicios.setColumnCount(4);
        modelServicios.setColumnIdentifiers(new Object[]{"Id", "Nombre", "Tipo", "Costo"});
        for (Servicio serv : fList) {
            modelServicios.addRow(new Object[]{
                serv.getId(),
                serv.getNombre(),
                serv.getTipo(),
                serv.getCosto()});
        }
        window.setModeloTabla(modelServicios);
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
                fillServicios();
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }

        }
        if (source == window.getEditar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String servicioId = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("ServicioId: " + servicioId);
                router.notify(this, "update-updateServicio-" + servicioId);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getCrear()) {
            router.notify(this, "go-addServicio");
        }

    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
    }

  @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
    
}
