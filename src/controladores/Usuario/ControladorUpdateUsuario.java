package controladores.Usuario;

import DAO.RolDao;
import DAO.UsuarioDao;
import DAO.general.DaoFactory;
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
import modelos.Rol;
import modelos.Usuario;
import vistas.swing.VentanaUpdateUsuario;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorUpdateUsuario extends ControladorUpdateGeneral implements ListSelectionListener {

    VentanaUpdateUsuario window;
    DaoFactory daoFactory;
    UsuarioDao usuarioDao;
    String usuarioId;

    public ControladorUpdateUsuario(IRouter router) {
        super("updateUsuario", router);
        daoFactory = new DaoFactory();
        usuarioDao = new UsuarioDao();
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void updateId(String id){
        usuarioId = id;
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaUpdateUsuario("Editar Usuario", this, this);
        window.setVisible(true);
        init();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillModel(
            String username,
            String password, 
            String rolId
            ) {
        window.getUsername().setTextField(username);
        window.getPassword().setTextField(password);

        int selectedItem = Utils.findCombobox(window.getRol(), rolId);
        window.setSelectedRol(selectedItem != -1 ? selectedItem : 0);
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

    public void clear() {
        window.getUsername().setTextField("");
        window.getPassword().setTextField("");    
    }

    public void save() {
        update(usuarioId);
    }

    public Boolean validateForm() {
        return window.getUsername().getTextField().isEmpty()
                || window.getPassword().getTextField().isEmpty();
    }

    public void goBack() {
        router.notify(this, "go-usuario");
    }

    public void init() {
        fillRoles();
        if (this.usuarioId != null) {
            UsuarioDao usuarioDao = new UsuarioDao();
                Usuario model = usuarioDao.get(this.usuarioId);
                fillModel(model.getUsername(), model.getPassword(), model.getRol().getId());
        }
    }

    public Usuario getUsuario(){
        Rol rol = new Rol();
        Usuario usuario2 = usuarioDao.get(usuarioId);
        rol.setId(((ComboboxItem) window.getRol().getSelectedItem()).getId());
        usuario2.setUsername(window.getUsername().getTextField());
        usuario2.setPassword(window.getPassword().getTextField());
        usuario2.setRol(rol);

        return usuario2;
    }
    public void update(String usuarioId) {
        try {
            if (validateForm()) {
                window.mostrarMensaje("Faltan campos por llenar");
            } else {
                Usuario newUsuario = getUsuario();
                String entity = "usuario";
                System.out.println("save" + '-' + entity);
                System.out.println(newUsuario.toString());
                Usuario existenteSolicitud = (Usuario) usuarioDao.get(newUsuario.getId());
                if (existenteSolicitud == null) {
                    window.mostrarMensaje("No existe este " + entity);
                    return;
                }
                usuarioDao.update(newUsuario);
                window.mostrarMensaje("Se actualizo el registro con exito");
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
