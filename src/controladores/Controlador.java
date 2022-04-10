package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import DAO.EmpleadoDao;
import DAO.PersonaDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import modelos.Empleado;
import modelos.Persona;
import modelos.Usuario;
import vistas.VentanaHome;
import vistas.backOffice.VentanaBeneficiarios;
import vistas.backOffice.VentanaEmpleados;
import vistas.general.VentanaFactory;
import vistas.general.VentanaGeneral;
public class Controlador implements ActionListener {
	VentanaGeneral window;
	VentanaFactory ventanaFactory;
	DaoFactory daoFactory;

    Usuario user;	
	
	public Controlador(Usuario user) {
		super();
		ventanaFactory = new VentanaFactory();
		daoFactory = new DaoFactory();
		this.user = user;
		window = new VentanaHome(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String name = btn.getName();
		String[] actionName = name.split("-");
		String action = actionName[0];
		if(action.equals("go")){
			String ventanaCode = actionName[1];
			window.dispose();
			window = ventanaFactory.getVentana(ventanaCode, this);
		}
		else if(action.equals("goList")){
			String ventanaCode = actionName[1];
			window.dispose();
			if(ventanaCode.equals("ben001")){
				PersonaDao personaDao = new PersonaDao();
				List<Persona> personas = personaDao.getAll();
				window = ventanaFactory.getVentanaList(ventanaCode, this, personas);
			}
			if(ventanaCode.equals("emp001")){
				EmpleadoDao empleadoDao = new EmpleadoDao();
				List<Empleado> empleados = empleadoDao.getAll();
				window = ventanaFactory.getVentanaList(ventanaCode, this, empleados);
			}
		}
		else if(action.equals("edit")){
			String entity = actionName[1];
			if(actionName.length < 3){
				window.mostrarMensaje("ningun " + entity + " seleccionado");
				return;
			}
			String id = actionName[2];
			IDao entityDao = daoFactory.getDao(entity);
			entityDao.update(entityDao.get(id));
		}
		else if(action.equals("delete")){
			String entity = actionName[1];
			if(actionName.length < 3){
				window.mostrarMensaje("ningun " + entity + " seleccionado");
				return;
			}
			String id = actionName[2];
			IDao entityDao = daoFactory.getDao(entity);
			entityDao.delete(entityDao.get(id));
		}
	}
}
