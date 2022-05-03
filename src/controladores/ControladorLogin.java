package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.EmpleadoDao;
import DAO.UsuarioDao;
import modelos.Empleado;
import modelos.Usuario;
import vistas.swing.VentanaLogin;

public class ControladorLogin implements ActionListener {

    VentanaLogin window;

    public ControladorLogin() {
        super();
    }

    public void init(){
        window = new VentanaLogin(this);
        window.setVisible(true);
        System.out.println("pase por aqui");
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
            window.dispose();
            new ControladorHome(user);
        }
    }
}
