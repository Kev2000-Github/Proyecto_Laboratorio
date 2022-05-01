package controladores;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Servicio;
import modelos.Solicitud;
import modelos.Usuario;
import utils.Constants;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaCrearSolicitud;
import javax.swing.JLabel;
import controladores.ControladorHome;

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

    public ControladorAddSolicitud(Usuario user) {
        super(user);
        daoFactory = new DaoFactory();
        window = new VentanaCrearSolicitud(this, this, this);
        window.setVisible(true);
        initCrear();
    }

    public void initCrear() {
        fillFundacion();
        fillEmpleado();
        fillBeneficiario();
        String fundacion_id = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
        fillServicios(fundacion_id);

    }

    public void fillFundacion() {
        DefaultComboBoxModel modelFundacion = new DefaultComboBoxModel();
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
        modelServicios.setColumnCount(4);
        modelServicios.setColumnIdentifiers(new Object[]{"Seleccionar", "ID", "Nombre", "Costo"});

        for (Servicio s : fsList) {
            modelServicios.addRow(new Object[]{Boolean.FALSE, s.getId(), s.getNombre(), s.getCosto()});
        }
        window.setModelServicio(modelServicios);
    }

    public void fillEmpleado() {
        DefaultComboBoxModel modelEmpleado = new DefaultComboBoxModel();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        List<Empleado> empleadoList = empleadoDao.getAll();
        for (Empleado emp : empleadoList) {
            modelEmpleado.addElement(
                    new ComboboxItem(emp.getId(), emp.getCedula() + "-" + emp.getApellido()));
        }
        window.setModelEmpleado(modelEmpleado);
    }

    public void fillBeneficiario() {
        DefaultComboBoxModel modelBeneficiario = new DefaultComboBoxModel();
        BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
        List<Beneficiario> beneficiariosList = beneficiarioDao.getAll();
        for (Beneficiario ben : beneficiariosList) {
            // 1 - can call methods of element
            modelBeneficiario.addElement(
                    new ComboboxItem(ben.getId(),
                            ben.getCedula() + "-" + ben.getApellido()));
        }
        window.setModelBeneficiario(modelBeneficiario);

    }

    public Solicitud getSolicitud() {
        Solicitud solicitud = new Solicitud();
        //System.out.println("i: " + ((ArrayList<Servicio>) itemList.getSelectedValuesList()).toString());
        solicitud.setId(window.getSaltString());
        solicitud.setBeneficiarioId(((ComboboxItem) window.getBeneficiario().getSelectedItem()).getId());
        solicitud.setEmpleadoId(((ComboboxItem) window.getEmpleado().getSelectedItem()).getId());
        solicitud.setFundacionId(((ComboboxItem) window.getFundacion().getSelectedItem()).getId());

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
        solicitud.setPrioridad(Constants.prioridadEnum.alta);
        solicitud.setStatus(Constants.estadoEnum.pendiente);
        return solicitud;
    }

    public void save() {
        try {
            if (calcCosto() == 0){
                 window.mostrarMensaje("Debe seleccionar almenos un servicio");
            }else{
                Solicitud newSolicitud = getSolicitud();
                String entity = "solicitud";
                System.out.println("save" + '-' + entity);
            
                System.out.println(newSolicitud.toString());
                IDao entityDao = daoFactory.getDao(entity);
                Solicitud existenteSolicitud = (Solicitud) entityDao.get(newSolicitud.getId());
            if (existenteSolicitud != null) {
                window.mostrarMensaje("Ya existe un registro de esta " + entity);
                return;
            }
            entityDao.save(newSolicitud);
            window.mostrarMensaje("Se agrego el registro con exito "); 
            initCrear();
            }
         
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
        //action
        // System.out.println("t" + String.valueOf(calcCosto()));
        //  window.setTextPrecio(String.valueOf(calcCosto()));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getFundacion()) {
            String fundacion_id = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
            // System.out.println(fundacion_id);
            fillServicios(fundacion_id);
        }
        //mejorar
          if (source == window.getCrearSolicitud()) {
              save();
          }
        //  if (source == window.getGestionar_solicitud()) {
        //      goGestionarSolicitud();
        //  }
        //  if (source == window.getCrear_solicitud()) {
        //      goCrearSolicitud();
        //  }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
        window.setTextPrecio(String.valueOf(calcCosto()));
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                window.dispose();
                new ControladorHome(user);
            }
        }
    }

}
