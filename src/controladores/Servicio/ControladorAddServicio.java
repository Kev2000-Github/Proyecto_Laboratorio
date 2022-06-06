/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Servicio;

import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelos.Fundacion;
import vistas.swing.VentanaCrearFundacion;
import vistas.swing.VentanaCrearServicio; 
import modelos.Servicio;
import vistas.general.ComboboxItem;
/**
 *
 * @author prometheus
 */

public class ControladorAddServicio extends ControladorGeneral implements ListSelectionListener {
    
    VentanaCrearServicio window;
    DaoFactory daoFactory;
    ServicioDao  servicioDao;
    
    public ControladorAddServicio(IRouter router){
        super("addServicio", router);
        daoFactory = new DaoFactory();
        servicioDao = new ServicioDao();   
    }
    
    
    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }
    
    
    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaCrearServicio("Crear Servicio", this, this);
        window.setVisible(true);
        fillFundacion();
    }
    
    public void closeGUI(){
        window.dispose();
    }
    
    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void goBack() {
        router.notify(this, "go-servicio");
    }
    
    public void clear() {
        window.getNombre().setTextField("");
        window.getTipo().setTextField("");
        window.getCosto().setTextField("");
    }
    
    public void fillFundacion() {
        DefaultComboBoxModel<ComboboxItem> modelFundacion = new DefaultComboBoxModel<ComboboxItem>();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundacionList = fundacionDao.getAll();
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                    new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundaciones(modelFundacion);
    }
    
    public void save() {
        create();
    }

    public Boolean validateForm() {
        return window.getNombre().getTextField().isEmpty()
                || window.getTipo().getTextField().isEmpty()
                || window.getCosto().getTextField().isEmpty();
    }
    
     public Servicio getServicio(){
        Servicio servicio = new Servicio();
        servicio.setId(window.getSaltString());
        servicio.setNombre(window.getNombre().getTextField());
        servicio.setTipo(window.getTipo().getTextField());
        servicio.setCosto(Float.parseFloat(window.getCosto().getTextField()));
        return servicio;
    }
     
    public Boolean isCreationValid(Servicio servicio){
        boolean hasEmptyFields = validateForm();
        if (hasEmptyFields) {
            window.mostrarMensaje("Faltan campos por llenar");
            return false;
        }
     
        return true;
    }
    
        public void create() {
        try {
            Servicio newServicio = getServicio();
            if(isCreationValid(newServicio)) {
                String entity = "servicio";
                System.out.println("save" + '-' + entity);
                System.out.println(newServicio.toString());
                Servicio servicioExiste = servicioDao.get(newServicio.getId());
                if (servicioExiste != null) {
                    window.mostrarMensaje("Ya existe un registro de este " + entity);
                    return;
                }
                servicioDao.save(newServicio);
                window.mostrarMensaje("Se agrego el registro con exito ");
                clear();
            }

            //((VentanaCrearSolicitud) window).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.save()" + e);
        }
    }

        @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();
        if (source == window.getSave()) {
            save();
            goBack();
        }
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
