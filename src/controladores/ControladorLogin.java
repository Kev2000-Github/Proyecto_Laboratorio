package controladores;

import java.awt.event.ActionEvent;
import java.util.List;

import DAO.EmpleadoDao;
import DAO.PermisoDao;
import DAO.RolDao;
import DAO.UsuarioDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import modelos.Empleado;
import modelos.Permiso;
import modelos.Rol;
import modelos.Usuario;
import vistas.swing.VentanaLogin;

public class ControladorLogin extends ControladorGeneral {
    VentanaLogin window;

    public ControladorLogin(IRouter router) {
        super("login", router);
    }

    public void initGUI(){
        window = new VentanaLogin(this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    private Usuario autenticar(String username, String password) {
        UsuarioDao userDao = new UsuarioDao();
        return userDao.login(username, password);
    }

    private Usuario getUserInfo(Usuario user) {
        Usuario newUser = user;
        EmpleadoDao empleadoDao = new EmpleadoDao();
        RolDao rolDao = new RolDao();
        PermisoDao permisoDao = new PermisoDao();

        Empleado empleado = empleadoDao.get(user.getEmpleado().getId());
        Rol rol =  rolDao.getByUser(user.getId());
        List<Permiso> permisos = permisoDao.getAllByRole(rol.getId());
        rol.setPermisos(permisos);

        newUser.setEmpleado(empleado);
        newUser.setRol(rol);
        return newUser;
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String username = window.getUsername();
        String password = window.getPassword();
        Usuario user = autenticar(username, password);
        if (user == null) {
            window.mostrarMensaje("Credenciales incorrectas");
        } else {
            Usuario completeUser = getUserInfo(user);
            setUser(completeUser);
            router.notify(this, "go-home");
        }
    }
}
