/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Servicio;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.general.DaoFactory;
import controladores.Mediator.IRouter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import modelos.Servicio;
import modelos.Fundacion;
import controladores.ControladorComponente.ControladorUpdateGeneral;
import vistas.swing.VentanaEditarServicio;
import javax.swing.DefaultComboBoxModel;
import vistas.general.ComboboxItem;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author prometheus
 */
public class ControladorUpdateServicio extends ControladorUpdateGeneral implements ListSelectionListener {

    VentanaEditarServicio window;
    DaoFactory daoFactory;
    ServicioDao servicioDao;
    String servicioId;
    String fundacionId;
    
    public ControladorUpdateServicio(IRouter router) {
        super("updateServicio", router);
        daoFactory = new DaoFactory();
        servicioDao = new ServicioDao();
    }

        public void mostrarMensaje(String mensaje){
            window.mostrarMensaje(mensaje);
        }

        public void updateId(String id){
            String[] ids = id.split("/");
            servicioId = ids[0];
            fundacionId = ids[1];
        }
        
    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaEditarServicio("Editar Servicio", this, this);
        window.setVisible(true);
        fillFundacion();
        if (this.servicioId != null) {
                Servicio model = servicioDao.getWithCosto(this.servicioId, this.fundacionId);
                fillModel(
                    model.getId(),
                    model.getNombre(),
                    model.getTipo(),
                    model.getCosto()
                );
        }
    }
    
    
    public void closeGUI(){
        window.dispose();
    }
    
           
    @Override
    public void valueChanged(ListSelectionEvent l) {
    }
    
    public void clear() {
        window.getServicioId().setText("");
        window.getNombre().setTextField("");
        window.getTipo().setTextField("");
        window.getCosto().setTextField("");
    }

    public void fillModel(
            String servicioId,
            String nombre, 
            String tipo,
            float costo) {
            window.getServicioId().setText(servicioId);
            window.getNombre().setTextField(nombre);
            window.getTipo().setTextField(tipo);
            window.getCosto().setTextField(String.valueOf(costo));
            window.setEnabled(true);
    }

    
        public void save() {
        update(servicioId);
    }
        
     public Boolean validateForm() {
        return window.getServicioId().getText().isEmpty()
                || window.getNombre().getTextField().isEmpty()
                || window.getTipo().getTextField().isEmpty()
                || window.getCosto().getTextField().isEmpty();
    }

    public void goBack() {
        router.notify(this, "go-servicio");
    }    
    
     public Servicio getServicio(){
        Servicio servicio = new Servicio();
        servicio.setId(window.getSaltString());
        servicio.setNombre(window.getNombre().getTextField());
        servicio.setTipo(window.getTipo().getTextField());
        servicio.setCosto(Float.parseFloat(window.getCosto().getTextField()));
        return servicio;
    }
    
       public void fillFundacion() {
            DefaultComboBoxModel<ComboboxItem> modelFundacion = new DefaultComboBoxModel<ComboboxItem>();
            FundacionDao fundacionDao = new FundacionDao();
            List<Fundacion> fundacionList = fundacionDao.getAll();
            int idx = 0;
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                new ComboboxItem(fund.getId(), fund.getNombre()));
            if(fund.getId() == fundacionId){
                idx = modelFundacion.getSize() - 1;
            }
        }
        window.setModelFundaciones(modelFundacion);
        window.getFundaciones().setSelectedIndex(idx);
    } 
        
  public void update(String servicioId) {
        try {
            if (validateForm()) {
                window.mostrarMensaje("Faltan campos por llenar");
            } else {
                Servicio newServicio = getServicio();
                String entity = "servicio";
                System.out.println("save" + '-' + entity);
                System.out.println(newServicio.toString());
                Servicio servicio = servicioDao.get(newServicio.getId());
                if (servicio == null) {
                    window.mostrarMensaje("No existe este " + entity);
                    return;
                }
                servicioDao.update(newServicio);
                window.mostrarMensaje("Se actualizo el registro con exito ");
            }
        } catch (Exception e) {
            System.out.println("controladores.ControladorUpdateServicio.update()" + e);
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
    
    
    
    
    