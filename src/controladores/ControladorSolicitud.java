package controladores;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Usuario;
import vistas.general.ComboboxItem;
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
public class ControladorSolicitud implements ActionListener {

    VentanaCrearSolicitud windowCrear;

    public ControladorSolicitud(String type) {
        super();
        if (type == "crear") {
            windowCrear = new VentanaCrearSolicitud(this);
            windowCrear.setVisible(true);
            initCrear();
        }

    }

    public void initCrear() {
        fillFundacion();
        fillEmpleado();
        fillBeneficiario();

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

    
      public void fillServicios() {
        DefaultTableModel modelServicios = new DefaultTableModel();
        ServicioDao servicioDao = new ServicioDao();
        /*List<Fundacion> fundacionList = servicioDao.getAll();
        for (Fundacion fund : fundacionList) {
           modelServicios.addRow(new Object[] {
                message.getSender(), message.getSubject(),
                message.getBody() });
         
            //modelFundacion.addElement(
              //      new ComboboxItem(fund.getId(), fund.getNombre()));
        }*/
          // windowCrear.setModelFundacion(modelFundacion);
    }
      
    public void fillEmpleado() {
        DefaultComboBoxModel modelEmpleado = new DefaultComboBoxModel();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        List<Empleado> empleadoList = empleadoDao.getAll();

        for (Empleado emp : empleadoList) {
            modelEmpleado.addElement(
                    new ComboboxItem(emp.getCedula(), emp.getCedula() + "-" + emp.getApellido()));
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
                    new ComboboxItem(ben.getCedula(),
                            ben.getCedula() + "-" + ben.getApellido()));
        }
        windowCrear.setModelBeneficiario(modelBeneficiario);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        //  if (source == window.getBack_office()) {
        //      goBackOffice();
        //  }
        //  if (source == window.getGestionar_solicitud()) {
        //      goGestionarSolicitud();
        //  }
        //  if (source == window.getCrear_solicitud()) {
        //      goCrearSolicitud();
        //  }
    }

}
