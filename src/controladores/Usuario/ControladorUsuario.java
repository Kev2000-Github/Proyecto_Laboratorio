package controladores.Usuario;

import DAO.EmpleadoDao;
import DAO.RolDao;
import DAO.UsuarioDao;
import DAO.general.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;

import modelos.Empleado;
import modelos.Rol;
import modelos.Usuario;
import vistas.swing.VentanaGestionarBackOffice;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorUsuario extends ControladorGeneral implements ListSelectionListener {

    VentanaGestionarBackOffice window;
    DaoFactory daoFactory;
    UsuarioDao usuarioDao;

    public ControladorUsuario(IRouter router) {
        super("usuario", router);
        daoFactory = new DaoFactory();
        usuarioDao = new UsuarioDao();
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaGestionarBackOffice("Gestionar Usuarios", this, this);
        window.setVisible(true);
        fillUsuarios();
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillUsuarios() {
        DefaultTableModel modelUsuarios = new DefaultTableModel();
        List<Usuario> uList = usuarioDao.getAll();
        modelUsuarios.setColumnCount(4);
        modelUsuarios.setColumnIdentifiers(new Object[]{"Id", "Empleado", "Username", "Rol"});
        for (Usuario user : uList) {
            EmpleadoDao empleadoDao = new EmpleadoDao();
            Empleado emp = empleadoDao.get(user.getEmpleado().getId());
            RolDao rolDao = new RolDao();
            Rol rol = rolDao.get(user.getRol().getId());
            modelUsuarios.addRow(new Object[]{
                user.getId(),
                emp.getNombre() + " " + emp.getApellido(),
                user.getUsername(),
                rol.getNombre()});
        }
        window.setModeloTabla(modelUsuarios);
    }

    @Override
    public void valueChanged(ListSelectionEvent l) {
     
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();
        if (source == window.getEliminar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String usuarioId = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("UsuarioId: " + usuarioId);
                Usuario user = new Usuario();
                user.setId(usuarioId);
                usuarioDao.delete(user);
                fillUsuarios();
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getEditar()) {
            int row = window.getTable().getSelectedRow();
            System.out.println("row: " + row);
            if (row != -1) {
                String usuarioId = window.getTable().getModel().getValueAt(row, 0).toString();
                System.out.println("FundacionId: " + usuarioId);
                router.notify(this, "update-updateUsuario-" + usuarioId);
            } else {
                window.mostrarMensaje("Debes seleccionar un item primero");
            }
        }
        if (source == window.getCrear()) {
            router.notify(this, "go-addUsuario");
        }

    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
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
