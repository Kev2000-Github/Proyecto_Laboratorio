package controladores.Persona.Beneficiario;

import DAO.BeneficiarioDao;
import DAO.FundacionDao;
import DAO.PersonaDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import controladores.ControladorComponente.ControladorUpdateGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import utils.Utils;
import vistas.general.ComboboxItem;
import javax.swing.JLabel;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Persona;
import vistas.swing.VentanaEditarPersona;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorUpdateBeneficiario extends ControladorUpdateGeneral implements ListSelectionListener {

    VentanaEditarPersona window;
    DaoFactory daoFactory;
    String beneficiarioId;

    public ControladorUpdateBeneficiario(IRouter router) {
        super("updateBeneficiario", router);
        daoFactory = new DaoFactory();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void updateId(String id){
        beneficiarioId = id;
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaEditarPersona("Editar Beneficiario", this, this);
        window.setVisible(true);
        init();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillModel(String cedula,
            String nombre, String apellido,
            String telefono,
            String correo,
            String direccion,
            String fundacion_id) {
        window.getCedula().setText(cedula);
        window.getNombre().setTextField(nombre);
        window.getApellido().setTextField(apellido);
        window.getTelefono().setTextField(telefono);
        window.getCorreo().setTextField(correo);
        window.getDireccion().setTextField(direccion);

        int selectedItem = Utils.findCombobox(window.getFundaciones(), fundacion_id);
        window.setSelectedFundacion(selectedItem != -1 ? selectedItem : 0);
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

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void clear() {
        fillFundacion();
        window.getCedula().setText("");
        window.getNombre().setTextField("");
        window.getApellido().setTextField("");
        window.getTelefono().setTextField("");
        window.getCorreo().setTextField("");
        window.getDireccion().setTextField("");
    }

    public Persona getPersona() {
        Persona persona = new Persona();
        persona.setCedula(window.getCedula().getText());
        persona.setNombre(window.getNombre().getTextField());
        persona.setApellido(window.getApellido().getTextField());
        persona.setTelefono(window.getTelefono().getTextField());
        persona.setCorreo(window.getCorreo().getTextField());
        persona.setDireccion(window.getDireccion().getTextField());
        return persona;
    }

    public Beneficiario getBeneficiario() {
        Persona persona = getPersona();
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setId(this.beneficiarioId != null ? this.beneficiarioId : window.getSaltString());
        beneficiario.setCedula(window.getCedula().getText());
        beneficiario.setFundacionId(((ComboboxItem) window.getFundaciones().getSelectedItem()).getId());
        beneficiario.setNombre(persona.getNombre());
        beneficiario.setApellido(persona.getApellido());
        beneficiario.setCorreo(persona.getCorreo());
        beneficiario.setDireccion(persona.getDireccion());
        beneficiario.setTelefono(persona.getTelefono());
        return beneficiario;
    }

    public Empleado getEmpleado() {
        Persona persona = getPersona();
        Empleado empleado = new Empleado();
        empleado.setId(this.beneficiarioId != null ? this.beneficiarioId : window.getSaltString());
        empleado.setCedula(window.getCedula().getText());
        empleado.setFundacionId(((ComboboxItem) window.getFundaciones().getSelectedItem()).getId());
        empleado.setNombre(persona.getNombre());
        empleado.setApellido(persona.getApellido());
        empleado.setCorreo(persona.getCorreo());
        empleado.setDireccion(persona.getDireccion());
        empleado.setTelefono(persona.getTelefono());
        return empleado;
    }

    public void save() {
        if (this.beneficiarioId != null) {
            update(id);
        } else {
            create();
        }
    }

    public Boolean validateForm() {
        return window.getCedula().getText().isEmpty()
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
                Beneficiario newBeneficiario = getBeneficiario();
                    String entity = "beneficiario";
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
            }

            //((VentanaCrearSolicitud) window).clear();
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.save()" + e);
        }
    }

    public void goBack() {
        router.notify(this, "go-beneficiario");
    }

    public void init() {
        fillFundacion();
        if (this.beneficiarioId != null) {
            BeneficiarioDao benDao = new BeneficiarioDao();
                Beneficiario model = benDao.get(this.beneficiarioId);
                fillModel(model.getCedula(),
                        model.getNombre(),
                        model.getApellido(),
                        model.getTelefono(),
                        model.getCorreo(),
                        model.getDireccion(),
                        model.getFundacionId());
        }
    }

    public void update(String cedula) {
        try {
            if (validateForm()) {
                window.mostrarMensaje("Faltan campos por llenar");
            } else {
                Beneficiario newBeneficiario = getBeneficiario();
                    String entity = "beneficiario";
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
            }
        } catch (Exception e) {
            System.out.println("controladores.ControladorPersona.update()" + e);
        }
    }

    public void searchPersona(String cedula) {
        PersonaDao personaDao = new PersonaDao();
        Persona persona = personaDao.get(cedula);
        if(persona != null){
            window.mostrarMensaje("La persona que intenta agregar ya existe");
        }
        else{
            window.setEnabled(true);
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
