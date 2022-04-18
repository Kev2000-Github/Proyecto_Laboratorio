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
import vistas.backOffice.VentanaAddPersona;
import vistas.backOffice.VentanaEditPersona;
import vistas.general.VentanaFactory;
import vistas.general.VentanaGeneral;
import vistas.general.VentanaGeneralLista;
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

	public void aprobarSolicitud(){

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
		else if(action.equals("delete")){
			String entity = actionName[1];
			String id = (String)btn.getClientProperty("itemId");
			if(id == null){
				window.mostrarMensaje("ningun " + entity + " seleccionado");
				return;
			}
			IDao entityDao = daoFactory.getDao(entity);
			entityDao.delete(entityDao.get(id));
			((VentanaGeneralLista<?>) window).updateList(entityDao.getAll());
		}
		else if(action.equals("add")){
			String entity = actionName[1];
			if(entity.equals("persona")){
				Persona newPersona = ((VentanaAddPersona) window).getPersona();
				IDao entityDao = daoFactory.getDao(entity);
				Persona existentPersona = (Persona) entityDao.get(newPersona.cedula);
				if(existentPersona != null){
					window.mostrarMensaje("Ya existe un registro de esta " + entity);
					return;
				}
				entityDao.save(newPersona);
				window.mostrarMensaje("Se agrego el registro con exito ");
				((VentanaAddPersona) window).clear();
			}
		}
		else if(action.equals("goEdit")){
			String entity = actionName[1];
			String ventanaCode = actionName[2];
			if(entity == "persona"){
				String id = (String)btn.getClientProperty("itemId");
				if(id == null){
					window.mostrarMensaje("ningun " + entity + " seleccionado");
					return;
				}
				window.dispose();
				IDao entityDao = daoFactory.getDao(entity);
				Object item = entityDao.get(id);
				window = ventanaFactory.getVentanaEdit(ventanaCode, this, item);
			}
		}
		else if(action.equals("edit")){
			String entity = actionName[1];
			Persona persona = ((VentanaEditPersona) window).getPersona();
			IDao entityDao = daoFactory.getDao(entity);
			entityDao.update(persona);
			window.dispose();
			window = ventanaFactory.getVentana("hom001", this);
		}
	}
}
