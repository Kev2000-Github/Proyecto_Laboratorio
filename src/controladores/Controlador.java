package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.PersonaDao;
import DAO.general.DaoFactory;
import DAO.general.IDao;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Persona;
import modelos.Usuario;
import vistas.VentanaBackOffice;
import vistas.VentanaHome;
import vistas.VentanaRegistros;
import vistas.backOffice.VentanaAddBeneficiario;
import vistas.backOffice.VentanaEditBeneficiario;
import vistas.backOffice.VentanaEditEmpleado;
import vistas.backOffice.VentanaEditPersona;
import vistas.general.VentanaFactory;
import vistas.general.VentanaGeneral;
import vistas.general.VentanaGeneralLista;
import vistas.solicitudes.VentanaAddSolicitud;
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
		else if(action.equals("goSection")){
			String ventanaCode = actionName[1];
			window.dispose();
			if(ventanaCode.equals("home")){
				window = new VentanaHome(this);
			}
			//?secciones home
			if(ventanaCode.equals("crearSolicitud")){
			  window = new VentanaAddSolicitud(this);
				
			}
			if(ventanaCode.equals("gestSolicitud")){
				
			}
			if(ventanaCode.equals("backOffice")){
				window = new VentanaBackOffice(this);
			}
			//?secciones backOffice
			if(ventanaCode.equals("registros")){
				window = new VentanaRegistros(this);
			}
			if(ventanaCode.equals("reportes")){
			//	window = new Ventana(this);
			}
			
		}
		else if(action.equals("goList")){
			String ventanaCode = actionName[1];
			window.dispose();
			if(ventanaCode.equals("ben001")){
				BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
				List<Beneficiario> beneficiarios = beneficiarioDao.getAll();
				window = ventanaFactory.getVentanaList(ventanaCode, this, beneficiarios);
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
				Beneficiario newBeneficiario = ((VentanaAddBeneficiario) window).getBeneficiario();
				IDao entityDao = daoFactory.getDao(entity);
				Persona existentPersona = (Persona) entityDao.get(newPersona.getCedula());
				if(existentPersona != null){
					window.mostrarMensaje("Ya existe un registro de esta " + entity);
					return;
				}
				entityDao.save(newBeneficiario);
				window.mostrarMensaje("Se agrego el registro con exito ");
				((VentanaAddBeneficiario) window).clear();
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
				List<String> modifiableFields = List.of("nombre","apellido","direccion","telefono", "correo");
				ArrayList<String> mutableFields = new ArrayList<String>(modifiableFields);
				window = ventanaFactory.getVentanaEdit(ventanaCode, this, item, entity, inmutableFields, mutableFields);
			}
			if(entity.equals("empleado")){
				window.dispose();
				IDao entityDao = daoFactory.getDao(entity);
				Object item = entityDao.get(id);
				ArrayList<String> inmutableFields = new ArrayList<>(List.of("id","cedula"));
				List<String> modifiableFields = List.of("nombre","apellido","direccion","telefono", "correo");
				ArrayList<String> mutableFields = new ArrayList<String>(modifiableFields);
				window = ventanaFactory.getVentanaEdit(ventanaCode, this, item, entity, inmutableFields, mutableFields);
			}
		}
		else if(action.equals("edit")){
			String entity = actionName[1];
			if(entity.equals("beneficiario")){
				Beneficiario beneficiario = ((VentanaEditBeneficiario) window).getItem();
				IDao<Beneficiario> entityDao = daoFactory.getDao(entity);
				entityDao.update(beneficiario);
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
