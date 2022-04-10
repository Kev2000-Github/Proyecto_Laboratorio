package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import DAO.PersonaDao;
import modelos.Persona;
import modelos.Usuario;
import vistas.VentanaHome;
import vistas.backOffice.VentanaBeneficiarios;
import vistas.backOffice.VentanaEmpleados;
import vistas.general.VentanaGeneral;
public class Controlador implements ActionListener {
	VentanaGeneral window;
	VentanaEmpleados empleadosBackOffice;

    Usuario user;	
	
	public Controlador(Usuario user) {
		super();
		this.user = user;
		window = new VentanaHome(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String name = btn.getName();
		if(name == "gotoBeneficiarios"){
			window.dispose();
			PersonaDao personaDao = new PersonaDao();
			List<Persona> personas = personaDao.getAll();
			window = new VentanaBeneficiarios(this, personas);
		}
		else if(name == "gotoEmpleados"){
			window.dispose();
			window = new VentanaEmpleados(this);
		}
		else if(name == "deleteBeneficiario"){
			window.mostrarMensaje("Are you sure about that?");
		}
		else if(name == "editBeneficiario"){
			window.mostrarMensaje("Editar");
		}
		else if(name == "gotoRegisterPerson"){
			window.mostrarMensaje("gotoRegisterPerson");
		}
		else if(name == "deleteEmpleado"){
			window.mostrarMensaje("Are you sure about that?");
		}
		else if(name == "editEmpleado"){
			window.mostrarMensaje("Editar");
		}
		else if(name == "gotoRegisterEmpleado"){
			window.mostrarMensaje("gotoRegisterPerson");
		}
		else if(name == "goHome"){
			window.dispose();
			window = new VentanaHome(this);
		}
	}
}
