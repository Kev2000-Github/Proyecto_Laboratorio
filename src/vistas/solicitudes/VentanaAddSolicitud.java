package vistas.solicitudes;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import modelos.Beneficiario;
import vistas.general.VentanaGeneral;
import vistas.general.InputField;

import java.awt.Dimension;
import java.awt.event.ActionListener;

public class VentanaAddSolicitud extends VentanaGeneral{
    private JLabel lblTitulo;
	static JComboBox fundacion;
	static JComboBox beneficiario;
	static JComboBox encargado;

    private JLabel presupuesto;

    private JButton sendBtn;
    private JButton goBack;

    public VentanaAddSolicitud(ActionListener accion){
        super();
        initGUI();
        this.agregarListener(accion);
    }
	
	private void initGUI() {
		try {
            mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
			{
				{
					lblTitulo = new JLabel();
					mainContainer.add(lblTitulo);

					lblTitulo.setText("Registrar Beneficiario");
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
				{
					String s1[] = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };
					// create checkbox
					beneficiario = new JComboBox(s1);
				}
                {
					goBack = new JButton();
					mainContainer.add(goBack);
					goBack.setText("goBack");
					goBack.setName("goSection-home");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public Beneficiario getBeneficiario() {
        Beneficiario beneficiario = new Beneficiario();
/*      beneficiario.setCedula(cedulaInput.getValue());
        beneficiario.setNombre(nombreInput.getValue());
        beneficiario.setApellido(apellidoInput.getValue());
        beneficiario.setDireccion(direccionInput.getValue());
        beneficiario.setTelefono(telefonoInput.getValue()); */
        return beneficiario;
    }

    public void clear(){
     //   cedulaInput.clear();
    // 
    }

    public void agregarListener(ActionListener accion){
        sendBtn.addActionListener(accion);
		beneficiario.addActionListener(accion);
        goBack.addActionListener(accion);
    }
}
