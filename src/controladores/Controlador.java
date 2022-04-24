package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import vistas.backOffice.VentanaEditEmpleado;
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
			String id = (String)btn.getClientProperty("itemId");
			if(id == null){
				window.mostrarMensaje("ningun " + entity + " seleccionado");
				return;
			}
			if(entity.equals("persona")){
				window.dispose();
				IDao entityDao = daoFactory.getDao(entity);
				Object item = entityDao.get(id);
				ArrayList<String> inmutableFields = new ArrayList<String>(List.of("cedula"));
				List<String> modifiableFields = List.of("nombre","apellido","direccion","telefono");
				ArrayList<String> mutableFields = new ArrayList<String>(modifiableFields);
				window = ventanaFactory.getVentanaEdit(ventanaCode, this, item, entity, inmutableFields, mutableFields);
			}
			if(entity.equals("empleado")){
				window.dispose();
				IDao entityDao = daoFactory.getDao(entity);
				Object item = entityDao.get(id);
				ArrayList<String> inmutableFields = new ArrayList<>(List.of("id","cedula"));
				List<String> modifiableFields = List.of("nombre","apellido","direccion","telefono");
				ArrayList<String> mutableFields = new ArrayList<String>(modifiableFields);
				window = ventanaFactory.getVentanaEdit(ventanaCode, this, item, entity, inmutableFields, mutableFields);
			}
		}
		else if(action.equals("edit")){
			String entity = actionName[1];
			if(entity.equals("persona")){
				Persona persona = ((VentanaEditPersona) window).getItem();
				IDao<Persona> entityDao = daoFactory.getDao(entity);
				entityDao.update(persona);
				window.dispose();
				window = ventanaFactory.getVentana("hom001", this);
			}
			else if(entity.equals("empleado")){
				Empleado empleado = ((VentanaEditEmpleado) window).getItem();
				IDao<Persona> entityDao = daoFactory.getDao("persona");
				entityDao.update(empleado);
				window.dispose();
				window = ventanaFactory.getVentana("hom001", this);
			}
		}
	}
}
