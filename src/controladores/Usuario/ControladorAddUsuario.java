package controladores.Usuario;

import DAO.EmpleadoDao;
import DAO.RolDao;
import DAO.UsuarioDao;
import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelos.Empleado;
import modelos.Rol;
import modelos.Usuario;
import vistas.general.ComboboxItem;
import vistas.swing.VentanaCrearUsuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorAddUsuario extends ControladorGeneral implements ListSelectionListener {

    VentanaCrearUsuario window;
    DaoFactory daoFactory;
    UsuarioDao usuarioDao;
    String empleadoId;

    public ControladorAddUsuario(IRouter router) {
        super("addUsuario", router);
        daoFactory = new DaoFactory();
        usuarioDao = new UsuarioDao();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaCrearUsuario("Crear Usuario", this, this);
        window.setVisible(true);
        fillRoles();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillRoles(){
        DefaultComboBoxModel<ComboboxItem> modelRol = new DefaultComboBoxModel<ComboboxItem>();
        RolDao rolDao = new RolDao();
        List<Rol> rolList = rolDao.getAll();
        for (Rol rol : rolList) {
            modelRol.addElement(
                    new ComboboxItem(rol.getId(), rol.getNombre()));
        }
        window.setModelRol(modelRol);
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {

    }

    public void goBack() {
        router.notify(this, "go-usuario");
    }

    public void clear() {
        window.getCedula().setTextField("");
        window.getUsername().setTextField("");
        window.getPassword().setTextField("");
    }

    public void save() {
        create();
    }

    public Boolean validateForm() {
        return window.getCedula().getTextField().isEmpty()
                || window.getUsername().getTextField().isEmpty()
                || window.getPassword().getTextField().isEmpty();
    }

    public Usuario getUsuario(){
        Usuario usuario = new Usuario();
        Empleado emp = new Empleado();
        Rol rol = new Rol();
        emp.setId(empleadoId);
        rol.setId(((ComboboxItem) window.getRol().getSelectedItem()).getId());
        usuario.setId(window.getSaltString());
        usuario.setUsername(window.getUsername().getTextField());
        usuario.setPassword(window.getPassword().getTextField());
        usuario.setRol(rol);
        usuario.setEmpleado(emp);
        return usuario;
    }

    public Empleado searchEmpleado(String cedula){
        Empleado empleado = new Empleado();
        EmpleadoDao empDao = new EmpleadoDao();
        empleado = empDao.getByCedula(cedula);
        if(empleado == null){
            window.mostrarMensaje("El empleado que intenta agregar como usuario no existe");
        }
        else{
            empleadoId = empleado.getId();
            Usuario usuario = usuarioDao.getByEmpleadoId(empleadoId, false);
            if(usuario == null){
                Usuario deletedUser = usuarioDao.getByEmpleadoId(empleadoId, false);
                if(deletedUser == null){
                    window.setEnabled(true);
                }
                else{
                    //TODO
                }
            }
            else{
                window.mostrarMensaje("El empleado ya tiene una cuenta activa");
            }
        }
        return empleado;
    }

    public Boolean isCreationValid(Usuario usuario){
        boolean hasEmptyFields = validateForm();
        if (hasEmptyFields) {
            window.mostrarMensaje("Faltan campos por llenar");
            return false;
        }
        return true;
    }

    public void create() {
        try {
            Usuario newUsuario = getUsuario();
            if(isCreationValid(newUsuario)) {
                String entity = "fundacion";
                System.out.println("save" + '-' + entity);
                System.out.println(newUsuario.toString());
                Usuario usuarioExiste = usuarioDao.get(newUsuario.getId());
                if (usuarioExiste != null) {
                    window.mostrarMensaje("Ya existe un registro de este " + entity);
                    return;
                }
                usuarioDao.save(newUsuario);
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
        if (source == window.getCedula().getSourceButton()) {
            String cedula = window.getCedula().getTextField();
            searchEmpleado(cedula);

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
