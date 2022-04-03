package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.UsuarioDao;
import modelos.Usuario;
import vistas.VentanaLogin;

public class ControladorLogin implements ActionListener {
    
	VentanaLogin window;
    Usuario user = null;	
	
	public ControladorLogin() {
		super();
		// TODO Auto-generated constructor stub
		window = new VentanaLogin();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.agregarListener(this);
	}
	
	private Usuario autenticar(String username, String password){
        UsuarioDao userDao = new UsuarioDao();
        return userDao.get(0);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
        String username = window.getUsername();
        String password = window.getPassword();
        user = autenticar(username, password);
        if(user == null){
            window.mostrarMensaje("Credenciales incorrectas");
        }
        else{
            
        }
	}
}
