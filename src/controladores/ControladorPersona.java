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

import modelos.Servicio;
import modelos.Solicitud;
import modelos.Usuario;
import utils.Constants;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaCrearSolicitud;
import javax.swing.JLabel;
import controladores.ControladorHome;
import javax.swing.JButton;
import javax.swing.JComboBox;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Persona;
import vistas.swing.VentanaGuardarPersona;
import vistas.swing.VentanaGestionarBackOffice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorPersona extends ControladorGeneral implements ListSelectionListener {

    VentanaGuardarPersona window;
    DaoFactory daoFactory;
    private String type;
    private String id;

    public ControladorPersona(Usuario user, String type, String id) {
        super(user);
        daoFactory = new DaoFactory();
        this.type = type;
        this.id = id;
        if (id != null) {
            window = new VentanaGuardarPersona("Editar " + type, this, this);
        } else {
            window = new VentanaGuardarPersona("Crear " + type, this, this);
        }

        window.setVisible(true);
        init();
    }

    public void fillModel(String cedula,
            String nombre, String apellido,
            String telefono,
            String correo,
            String direccion,
            String fundacion_id) {
        window.getCedula().setTextField(cedula);
        window.getNombre().setTextField(nombre);
        window.getApellido().setTextField(apellido);
        window.getTelefono().setTextField(telefono);
        window.getCorreo().setTextField(correo);
        window.getDireccion().setTextField(direccion);

        int selectedItem = findCombobox(window.getFundaciones(), fundacion_id);
        window.setSelectedFundacion(selectedItem != -1 ? selectedItem : 0);
    }

    public void init() {
        fillFundacion();
        if (this.id != null) {
            if (type == "beneficiario") {
                BeneficiarioDao benDao = new BeneficiarioDao();
                Beneficiario model = benDao.get(this.id);
                fillModel(model.getPersona().getCedula(),
                        model.getPersona().getNombre(),
                        model.getPersona().getApellido(),
                        model.getPersona().getTelefono(),
                        model.getPersona().getCorreo(),
                        model.getPersona().getDireccion(),
                        model.getFundacionId());
            } else {
                EmpleadoDao empDao = new EmpleadoDao();
                Empleado model = empDao.get(this.id);
                fillModel(model.getPersona().getCedula(),
                        model.getPersona().getNombre(),
                        model.getPersona().getApellido(),
                        model.getPersona().getTelefono(),
                        model.getPersona().getCorreo(),
                        model.getPersona().getDireccion(),
                        model.getFundacionId());
            }

        }
    }

    public void fillFundacion() {
        DefaultComboBoxModel modelFundacion = new DefaultComboBoxModel();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundacionList = fundacionDao.getAll();
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                    new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundaciones(modelFundacion);
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void clear() {
        fillFundacion();
        window.getCedula().setTextField("");
        window.getNombre().setTextField("");
        window.getApellido().setTextField("");
        window.getTelefono().setTextField("");
        window.getCorreo().setTextField("");
        window.getDireccion().setTextField("");
    }

    public Persona getPersona() {
        Persona persona = new Persona();
        persona.setCedula(window.getCedula().getTextField());
        persona.setNombre(window.getNombre().getTextField());
        persona.setApellido(window.getApellido().getTextField());
        persona.setTelefono(window.getTelefono().getTextField());
        persona.setCorreo(window.getCorreo().getTextField());
        persona.setDireccion(window.getDireccion().getTextField());
        return persona;
    }

    public Beneficiario getBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(this.id != null ? this.id : window.getSaltString());
        beneficiario.setCedula(window.getCedula().getTextField());
        beneficiario.setFundacionId(((ComboboxItem) window.getFundaciones().getSelectedItem()).getId());
        beneficiario.setPersona(getPersona());
        return beneficiario;
    }

    public Empleado getEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setId(this.id != null ? this.id : window.getSaltString());
        empleado.setCedula(window.getCedula().getTextField());
        empleado.setFundacionId(((ComboboxItem) window.getFundaciones().getSelectedItem()).getId());
        empleado.setPersona(getPersona());
        return empleado;
    }

    public void save() {
        if (this.id != null) {
            update(id);
        } else {
            create();
        }
    }

    public Boolean validateForm() {
        return window.getCedula().getTextField().isEmpty()
                || window.getNombre().getTextField().isEmpty()
                || window.getApellido().getTextField().isEmpty()
                || window.getTelefono().getTextField().isEmpty()
                || window.getCorreo().getTextField().isEmpty()
                || window.getDireccion().getTextField().isEmpty();
    }

    public void create() {
        try {
            if (validateForm()) {
                window.mostrarMensaje("Faltan campos por llenar");
            } else {
                if (this.type == "beneficiario") {
                    Beneficiario newBeneficiario = getBeneficiario();
                    String entity = this.type;
                    System.out.println("save" + '-' + entity);
                    System.out.println(newBeneficiario.toString());
                    IDao entityDao = daoFactory.getDao(entity);
                    Beneficiario existenteSolicitud = (Beneficiario) entityDao.get(newBeneficiario.getId());
                    if (existenteSolicitud != null) {
                        window.mostrarMensaje("Ya existe un registro de este " + entity);
                        return;
                    }
                    entityDao.save(newBeneficiario);
                    window.mostrarMensaje("Se agrego el registro con exito ");
                    clear();
                } else {
                    Empleado newEmpleado = getEmpleado();
                    String entity = this.type;
                    System.out.println("save" + '-' + entity);
                    System.out.println(newEmpleado.toString());
                    IDao entityDao = daoFactory.getDao(entity);
                    Beneficiario existenteSolicitud = (Beneficiario) entityDao.get(newEmpleado.getId());
                    if (existenteSolicitud != null) {
                        window.mostrarMensaje("Ya existe un registro de este " + entity);
                        return;
                    }
                    entityDao.save(newEmpleado);
                    window.mostrarMensaje("Se actualizo el registro con exito ");
                    clear();
                }

            }

            //((VentanaCrearSolicitud) window).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.save()" + e);
        }
    }

    public void update(String cedula) {
        try {
            if (validateForm()) {
                window.mostrarMensaje("Faltan campos por llenar");
            } else {
                if (this.type == "beneficiario") {
                    Beneficiario newBeneficiario = getBeneficiario();
                    String entity = this.type;
                    System.out.println("save" + '-' + entity);
                    System.out.println(newBeneficiario.toString());
                    IDao entityDao = daoFactory.getDao(entity);
                    Beneficiario existenteSolicitud = (Beneficiario) entityDao.get(newBeneficiario.getId());
                    if (existenteSolicitud == null) {
                        window.mostrarMensaje("No existe este " + entity);
                        return;
                    }
                    entityDao.update(newBeneficiario);
                    window.mostrarMensaje("Se actualizo el registro con exito ");
                } else {
                    Empleado newEmpleado = getEmpleado();
                    String entity = this.type;
                    System.out.println("save" + '-' + entity);
                    System.out.println(newEmpleado.toString());
                    IDao entityDao = daoFactory.getDao(entity);
                    Beneficiario existenteSolicitud = (Beneficiario) entityDao.get(newEmpleado.getId());
                    if (existenteSolicitud == null) {
                        window.mostrarMensaje("No existe este " + entity);
                        return;
                    }
                    entityDao.update(newEmpleado);
                    window.mostrarMensaje("Se actualizo el registro con exito ");
                    //empleado
                }

            }
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.update()" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();
        if (source == window.getSave()) {
            save();
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //  System.out.println("t" + String.valueOf(calcCosto()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if (source.equals("javax.swing.JLabel")) {
            JLabel lbl = (JLabel) e.getSource();
            if (lbl.getName() == "goHome") {
                window.dispose();
                //    new ControladorHome(user);
            }
            if (lbl.getName() == "goBack") {
                if (this.type == "beneficiario") {
                    window.dispose();
                    new ControladorBeneficiario(user);
                } else {
                     window.dispose();
                    new ControladorEmpleado(user);
                }

            }
        }
    }

}