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
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.FundacionServicio;
import modelos.Servicio;
import modelos.Solicitud;
import modelos.Usuario;
import utils.Constants;
import vistas.general.ComboboxItem;
import vistas.solicitudes.VentanaAddSolicitud;
import vistas.swing.VentanaCrearSolicitud;
import vistas.swing.VentanaHome;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorAddSolicitud implements ActionListener, ListSelectionListener, MouseListener {

    VentanaCrearSolicitud windowCrear;
    DaoFactory daoFactory;

    public ControladorAddSolicitud() {
        super();
        daoFactory = new DaoFactory();
        windowCrear = new VentanaCrearSolicitud(this, this, this);
        windowCrear.setVisible(true);
        initCrear();
    }

    public void initCrear() {
        fillFundacion();
        fillEmpleado();
        fillBeneficiario();
        String fundacion_id = ((ComboboxItem) windowCrear.getFundacion().getSelectedItem()).getId();
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
        windowCrear.setModelFundacion(modelFundacion);
    }

    public void fillServicios(String id) {
        DefaultTableModel modelServicios = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        ServicioDao servicioDao = new ServicioDao();
        List<FundacionServicio> fsList = servicioDao.getFundacionServiciosById(id);
        modelServicios.setColumnCount(4);
        modelServicios.setColumnIdentifiers(new Object[]{"Seleccionar", "ID", "Nombre", "Costo"});

        for (FundacionServicio fs : fsList) {
            modelServicios.addRow(new Object[]{Boolean.FALSE, fs.getServicio_id(), fs.getNombre(), fs.getCosto()});
        }
        windowCrear.setModelServicio(modelServicios);
    }

    public void fillEmpleado() {
        DefaultComboBoxModel modelEmpleado = new DefaultComboBoxModel();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        List<Empleado> empleadoList = empleadoDao.getAll();
        for (Empleado emp : empleadoList) {
            modelEmpleado.addElement(
                    new ComboboxItem(emp.getId(), emp.getCedula() + "-" + emp.getApellido()));
        }
        windowCrear.setModelEmpleado(modelEmpleado);
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
        windowCrear.setModelBeneficiario(modelBeneficiario);

    }

    public Solicitud getSolicitud() {
        Solicitud solicitud = new Solicitud();
        //System.out.println("i: " + ((ArrayList<Servicio>) itemList.getSelectedValuesList()).toString());
        solicitud.setId(windowCrear.getSaltString());
        solicitud.setBeneficiarioId(((ComboboxItem) windowCrear.getBeneficiario().getSelectedItem()).getId());
        solicitud.setEmpleadoId(((ComboboxItem) windowCrear.getEmpleado().getSelectedItem()).getId());
        solicitud.setFundacionId(((ComboboxItem) windowCrear.getFundacion().getSelectedItem()).getId());

        ArrayList<Servicio> serviciosArr = new ArrayList<Servicio>();
        for (int i = 0; i < windowCrear.getServicios().getRowCount(); i++) {
             Boolean isChecked = Boolean.valueOf(windowCrear.getServicios().getValueAt(i, 0).toString());
            if (isChecked) {
                 serviciosArr.add(
                    new Servicio(String.valueOf(windowCrear.getServicios().getValueAt(i, 1).toString()),
                    String.valueOf(windowCrear.getServicios().getValueAt(i, 2).toString())));
            }
           
        }

        solicitud.setServicios(serviciosArr);
        solicitud.setPrioridad(Constants.prioridadEnum.alta);
        solicitud.setStatus(Constants.estadoEnum.pendiente);
        solicitud.setCostoTotal(calcCosto());
        return solicitud;
    }

    public void save() {
        try {
            if (calcCosto() == 0){
                 windowCrear.mostrarMensaje("Debe seleccionar almenos un servicio");
            }else{
               Solicitud newSolicitud = getSolicitud();
            String entity = "solicitud";
            System.out.println("save" + '-' + entity);
          
            System.out.println(newSolicitud.toString());
            IDao entityDao = daoFactory.getDao(entity);
            Solicitud existenteSolicitud = (Solicitud) entityDao.get(newSolicitud.getId());
            if (existenteSolicitud != null) {
                windowCrear.mostrarMensaje("Ya existe un registro de esta " + entity);
                return;
            }
            entityDao.save(newSolicitud);
            windowCrear.mostrarMensaje("Se agrego el registro con exito "); 
            initCrear();
            }
         
            //((VentanaCrearSolicitud) windowCrear).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorAddSolicitud.save()" + e);
        }

    }

    public float calcCosto() {
        float cost = 0;
        for (int i = 0; i < windowCrear.getServicios().getRowCount(); i++) {
            Boolean isChecked = Boolean.valueOf(windowCrear.getServicios().getValueAt(i, 0).toString());
            if (isChecked) {
                cost += Float.valueOf(windowCrear.getServicios().getValueAt(i, 3).toString());
                //get the values of the columns you need.
            }
        }
        return cost;
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {
        //action
        // System.out.println("t" + String.valueOf(calcCosto()));
        //  windowCrear.setTextPrecio(String.valueOf(calcCosto()));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == windowCrear.getFundacion()) {
            String fundacion_id = ((ComboboxItem) windowCrear.getFundacion().getSelectedItem()).getId();
            // System.out.println(fundacion_id);
            fillServicios(fundacion_id);
        }
        //mejorar
          if (source == windowCrear.getCrearSolicitud()) {
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
    public void mouseClicked(MouseEvent e) {

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
        //  System.out.println("t" + String.valueOf(calcCosto()));
        windowCrear.setTextPrecio(String.valueOf(calcCosto()));
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
