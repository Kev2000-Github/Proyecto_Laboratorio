package vistas.solicitudes;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.FundacionDao;
import DAO.ServicioDao;

import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Servicio;
import modelos.Solicitud;
import vistas.general.VentanaGeneral;

import vistas.general.ComboboxItem;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import utils.Constants;
public class VentanaAddSolicitud extends VentanaGeneral {
	private JLabel lblTitulo;
	static JComboBox fundacionElement;
	static JComboBox empleadoElement;
	static JComboBox beneficiarioElement;

	static JList itemList;

	private JButton goSave;
	private JButton goBack;

	public VentanaAddSolicitud(ActionListener accion) {
		super();
		initGUI();
		this.agregarListener(accion);
	}

	public Float calcCostoTotal() {
		return (float) 1000;
	}
	public DefaultListModel<Servicio> fillListServicios() {
		DefaultListModel<Servicio> listModel = new DefaultListModel<Servicio>();
		;
		ServicioDao servicioDao = new ServicioDao();
		List<Servicio> servicioList = servicioDao.getAll();
		for (Servicio serv : servicioList) {
			listModel.addElement(new Servicio(serv.getId(), serv.getNombre(), serv.getTipo()));
		}
		return listModel;
	}

	private void initGUI() {
		try {
		System.out.println("initGui");
			mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			{

				lblTitulo = new JLabel();
				mainContainer.add(lblTitulo);

				lblTitulo.setText("Crear Solicitud");
				lblTitulo.setFont(new java.awt.Font("Dialog", 1, 15));
				lblTitulo.setBorder(new EtchedBorder());

				Vector modelBeneficiario = new Vector();
				BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
				List<Beneficiario> beneficiariosList = beneficiarioDao.getAll();

				for (Beneficiario ben : beneficiariosList) {
					// 1 - can call methods of element
					modelBeneficiario.addElement(
							new ComboboxItem(ben.getCedula(),
									ben.getCedula() + "-" + ben.getApellido()));
					// ...
				}
				// create checkbox
				beneficiarioElement = new JComboBox(modelBeneficiario);
				beneficiarioElement.setSize(new Dimension(100, 20));
				mainContainer.add(beneficiarioElement);

				// ? Servicios
				itemList = new JList<Servicio>(fillListServicios());
				itemList.setVisibleRowCount(20);
				MouseAdapter mouseListener = new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						System.out.println("Clicked on " + itemList.getSelectedValuesList());

						// selectedItem = (String) itemList.getSelectedValue();
						// String id = selectedItem.split(" ")[0];
						// delete.putClientProperty("itemId", id);
						// edit.putClientProperty("itemId", id);
					}
				};
				itemList.addMouseListener(mouseListener);
				mainContainer.add(new JScrollPane(itemList));

				Vector modelEmpleado = new Vector();
				EmpleadoDao empleadoDao = new EmpleadoDao();
				List<Empleado> empleadoList = empleadoDao.getAll();

				for (Empleado emp : empleadoList) {
					modelEmpleado.addElement(
							new ComboboxItem(emp.getCedula(), emp.getCedula() + "-" + emp.getApellido()));
					// ...
				}
				// create checkbox
				empleadoElement = new JComboBox(modelEmpleado);
				empleadoElement.setSize(new Dimension(100, 20));
				mainContainer.add(empleadoElement);

				// ?fundacion
				Vector modelFundacion = new Vector();
				FundacionDao fundacionDao = new FundacionDao();
				List<Fundacion> fundacionList = fundacionDao.getAll();
				for (Fundacion fund : fundacionList) {

					modelFundacion.addElement(
							new ComboboxItem(fund.getId(), fund.getNombre()));
				}
				// create checkbox
				fundacionElement = new JComboBox(modelFundacion);
				fundacionElement.setSize(new Dimension(100, 20));
				mainContainer.add(fundacionElement);

				// ?back
				goBack = new JButton();
				mainContainer.add(goBack);
				goBack.setText("Atras");
				goBack.setName("go-hom001");

				goSave = new JButton();
				goSave.setText("Guardar");
				goSave.setName("goSolicitud-save");
				mainContainer.add(goSave);
			

			}

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public Solicitud getSolicitud() {
		Solicitud solicitud = new Solicitud();
		System.out.println("i: " + ((ArrayList<Servicio>) itemList.getSelectedValuesList()).toString());
		solicitud.setId(getSaltString());
     	solicitud.setBeneficiarioId(((ComboboxItem) beneficiarioElement.getSelectedItem()).getId());
		solicitud.setEmpleadoId(((ComboboxItem) empleadoElement.getSelectedItem()).getId());
		solicitud.setFundacionId(((ComboboxItem) fundacionElement.getSelectedItem()).getId());
		solicitud.setServicios((ArrayList<Servicio>) itemList.getSelectedValuesList());
		solicitud.setPrioridad(Constants.prioridadEnum.alta);
		solicitud.setStatus(Constants.estadoEnum.pendiente);
		solicitud.setCostoTotal(calcCostoTotal());
		return solicitud;
	}

	//move
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	public void clear() {
		// cedulaInput.clear();
		//
	}

	public void agregarListener(ActionListener accion) {
		goSave.addActionListener(accion);
		goBack.addActionListener(accion);
	}
}

