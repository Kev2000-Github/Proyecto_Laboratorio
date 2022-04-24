package vistas.solicitudes;


import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.FundacionDao;
import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import vistas.general.VentanaGeneral;
import vistas.general.InputField;
import vistas.general.ComboboxItem;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class VentanaAddSolicitud extends VentanaGeneral {
	private JLabel lblTitulo;
	static JComboBox fundacion;
	static JComboBox empleado;
	static JComboBox beneficiario;

	private JLabel presupuesto;

	private JButton sendBtn;
	private JButton goBack;

	public VentanaAddSolicitud(ActionListener accion) {
		super();
		initGUI();
		this.agregarListener(accion);
	}

	private void initGUI() {
		try {
			mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			mainContainer.setSize(1000,1000);
			{
				{
					lblTitulo = new JLabel();
					mainContainer.add(lblTitulo);

					lblTitulo.setText("Crear Solicitud");
					lblTitulo.setFont(new java.awt.Font("Dialog", 1, 16));
					lblTitulo.setBorder(new EtchedBorder());
				}
				{
					
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
					beneficiario = new JComboBox(modelBeneficiario);
					mainContainer.add(beneficiario);

					Vector modelEmpleado = new Vector();
					EmpleadoDao empleadoDao = new EmpleadoDao();
					List<Empleado> empleadoList = empleadoDao.getAll();

					for (Empleado emp : empleadoList) {
						modelEmpleado.addElement(
								new ComboboxItem(emp.getCedula(), emp.getCedula() + "-" + emp.getApellido()));
						// ...
					}
					// create checkbox
					empleado = new JComboBox(modelEmpleado);
					mainContainer.add(empleado);

					// ?fundacion

					Vector modelFundacion = new Vector();
					FundacionDao fundacionDao = new FundacionDao();
					List<Fundacion> fundacionList = fundacionDao.getAll();

					for (Fundacion fund : fundacionList) {
						modelFundacion.addElement(
								new ComboboxItem(fund.getId(), fund.getNombre()));
						// ...
					}
					// create checkbox
					fundacion = new JComboBox(modelFundacion);
					mainContainer.add(fundacion);
				}
				{
					goBack = new JButton();
					mainContainer.add(goBack);
					goBack.setText("Atras");
					goBack.setName("goSection-home");
				}
			}
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public Beneficiario getBeneficiario() {
		Beneficiario beneficiario = new Beneficiario();
		/*
		 * beneficiario.setCedula(cedulaInput.getValue());
		 * beneficiario.setNombre(nombreInput.getValue());
		 * beneficiario.setApellido(apellidoInput.getValue());
		 * beneficiario.setDireccion(direccionInput.getValue());
		 * beneficiario.setTelefono(telefonoInput.getValue());
		 */
		return beneficiario;
	}

	public void clear() {
		// cedulaInput.clear();
		//
	}

	public void agregarListener(ActionListener accion) {
		// sendBtn.addActionListener(accion);
		beneficiario.addActionListener(accion);
		goBack.addActionListener(accion);
	}
}
