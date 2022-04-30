package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.UsuarioDao;
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

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String username = window.getUsername();
        String password = window.getPassword();
        Usuario user = autenticar(username, password);
        if (user == null) {
            window.mostrarMensaje("Credenciales incorrectas");
        } else {
            window.dispose();
            new ControladorHome(user);
        }
    }
}
