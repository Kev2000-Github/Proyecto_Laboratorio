package controladores;

import java.awt.event.ActionEvent;

import DAO.EmpleadoDao;
import DAO.UsuarioDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;
import modelos.Empleado;
import modelos.Usuario;
import vistas.swing.VentanaLogin;

public class ControladorLogin extends ControladorGeneral {
    VentanaLogin window;

    public ControladorLogin(Router router) {
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

    private Empleado getUserInfo(Usuario user) {
        EmpleadoDao empleadoDao = new EmpleadoDao();
        Empleado empleado = empleadoDao.get(user.getEmpleado().getId());
        return empleado;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String username = window.getUsername();
        String password = window.getPassword();
        Usuario user = autenticar(username, password);
        if (user == null) {
            window.mostrarMensaje("Credenciales incorrectas");
        } else {
            Empleado userEmpleado = getUserInfo(user);
            user.setEmpleado(userEmpleado);
            this.user = user;
            router.notify(this, "go-home");
        }
    }
}
