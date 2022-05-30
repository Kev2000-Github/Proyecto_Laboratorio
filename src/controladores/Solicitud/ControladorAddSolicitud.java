package controladores.Solicitud;

import DAO.BeneficiarioDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Beneficiario;
import modelos.Fundacion;
import modelos.Servicio;
import modelos.Solicitud;
import utils.Constants;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaCrearSolicitud;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorAddSolicitud extends ControladorGeneral implements ListSelectionListener {

    VentanaCrearSolicitud window;
    DaoFactory daoFactory;

    public ControladorAddSolicitud(IRouter router) {
        super("addSolicitud", router);
        daoFactory = new DaoFactory();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaCrearSolicitud(this, this, this);
        window.setVisible(true);
        initCrear();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void initCrear() {
        fillFundacion();
        fillEmpleado();
        fillBeneficiario();
        fillPrioridad();
        String fundacionId = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
        fillServicios(fundacionId);
        String rol = user.getRol().getNombre();
        if(rol.equals("admin")){
            window.restrictSensitiveComp(true);
        }
    }

    public void fillFundacion() {
        DefaultComboBoxModel<ComboboxItem> modelFundacion = new DefaultComboBoxModel<ComboboxItem>();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundacionList = fundacionDao.getAll();
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                    new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundacion(modelFundacion);
    }

    public void fillServicios(String fundacionId) {
        DefaultTableModel modelServicios = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        ServicioDao servicioDao = new ServicioDao();
        List<Servicio> fsList = servicioDao.getAllFromFundacion(fundacionId);
        modelServicios.setColumnCount(5);
        modelServicios.setColumnIdentifiers(new Object[]{"Seleccionar", "ID", "Nombre", "Costo", "Tipo"});

        for (Servicio s : fsList) {
            modelServicios.addRow(new Object[]{Boolean.FALSE, s.getId(), s.getNombre(), s.getCosto(), s.getTipo().toString()});
        }
        window.setModelServicio(modelServicios);
    }

    public void fillEmpleado() {
        String nombreCompleto = user.getEmpleado().getNombre() + " " + user.getEmpleado().getApellido();
        String id = user.getEmpleado().getId();
        window.setEmpleado(id, nombreCompleto);
    }

    public void fillBeneficiario() {
        DefaultComboBoxModel<ComboboxItem> modelBeneficiario = new DefaultComboBoxModel<ComboboxItem>();
        BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
        List<Beneficiario> beneficiariosList = beneficiarioDao.getAll();
        for (Beneficiario ben : beneficiariosList) {
            modelBeneficiario.addElement(
                    new ComboboxItem(ben.getId(),
                            ben.getCedula() + "-" + ben.getApellido()));
        }
        window.setModelBeneficiario(modelBeneficiario);
    }

    public void fillPrioridad() {
        DefaultComboBoxModel<ComboboxItem> modelPrioridad = new DefaultComboBoxModel<ComboboxItem>();
        String[] prioridades = new String[]{ Constants.prioridadEnum.alta.toString(), Constants.prioridadEnum.media.toString(), Constants.prioridadEnum.baja.toString() };
        for (String prioridad : prioridades) {
            modelPrioridad.addElement(
                    new ComboboxItem(prioridad, prioridad));
        }
        window.setModelPrioridad(modelPrioridad);
    }

    public Solicitud getSolicitud() {
        Solicitud solicitud = new Solicitud();
        solicitud.setId(window.getSaltString());
        solicitud.setBeneficiarioId(((ComboboxItem) window.getBeneficiario().getSelectedItem()).getId());
        solicitud.setEmpleadoId(window.getEmpleadoId().getText());
        solicitud.setFundacionId(((ComboboxItem) window.getFundacion().getSelectedItem()).getId());
        solicitud.setPrioridad(Constants.prioridadEnum.valueOf(((ComboboxItem) window.getPrioridad().getSelectedItem()).getId()));
        ArrayList<Servicio> serviciosArr = new ArrayList<Servicio>();
        for (int i = 0; i < window.getServicios().getRowCount(); i++) {
            Boolean isChecked = Boolean.valueOf(window.getServicios().getValueAt(i, 0).toString());
            if (isChecked) {
                Servicio serv = new Servicio();
                serv.setId(window.getServicios().getValueAt(i, 1).toString());
                serv.setNombre(window.getServicios().getValueAt(i, 2).toString());
                serv.setCosto(Float.valueOf(window.getServicios().getValueAt(i, 3).toString()));
                serviciosArr.add(serv);
            }
        }

        solicitud.setServicios(serviciosArr);
        solicitud.setStatus(Constants.estadoEnum.pendiente);
        return solicitud;
    }

    public void save() {
        try {
            if (calcCosto() == 0) {
                window.mostrarMensaje("Debe seleccionar almenos un servicio");
                return;
            }
            Solicitud newSolicitud = getSolicitud();
            String entity = "solicitud";
            System.out.println("save" + '-' + entity);

            System.out.println(newSolicitud.toString());
            IDao<Solicitud> entityDao = daoFactory.getDao(entity);
            Solicitud existenteSolicitud = (Solicitud) entityDao.get(newSolicitud.getId());
            if (existenteSolicitud != null) {
                window.mostrarMensaje("Ya existe un registro de esta " + entity);
                return;
            }
            entityDao.save(newSolicitud);
            window.mostrarMensaje("Se agrego el registro con exito ");
            initCrear();

            //((VentanaCrearSolicitud) window).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorAddSolicitud.save()" + e);
        }

    }

    public float calcCosto() {
        float cost = 0;
        for (int i = 0; i < window.getServicios().getRowCount(); i++) {
            Boolean isChecked = Boolean.valueOf(window.getServicios().getValueAt(i, 0).toString());
            if (isChecked) {
                cost += Float.valueOf(window.getServicios().getValueAt(i, 3).toString());
                //get the values of the columns you need.
            }
        }
        return cost;
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getFundacion()) {
            String fundacion_id = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
            fillServicios(fundacion_id);
        }

        if (source == window.getCrearSolicitud()) {
            save();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
        window.setTextPrecio(String.valueOf(calcCosto()));
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

}
