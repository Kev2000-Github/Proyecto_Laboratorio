package vistas.backOffice;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import modelos.Persona;
import vistas.general.VentanaGeneral;
import vistas.general.InputField;

import java.awt.Dimension;
import java.awt.event.ActionListener;

public class VentanaAddPersona extends VentanaGeneral{
    private JLabel lblTitulo;
    private InputField cedulaInput;
    private InputField nombreInput;
    private InputField apellidoInput;
    private InputField direccionInput;
    private InputField telefonoInput;
    private JButton sendBtn;
    private JButton goHome;

    public VentanaAddPersona(ActionListener accion){
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
                    cedulaInput = new InputField("cedula:");
                    cedulaInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(cedulaInput);
				}
                {
                    nombreInput = new InputField("nombre:");
                    nombreInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(nombreInput);
				}
                {
                    apellidoInput = new InputField("apellido:");
                    apellidoInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(apellidoInput);
				}
                {
                    direccionInput = new InputField("direccion:");
                    direccionInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(direccionInput);
				}
                {
                    telefonoInput = new InputField("telefono:");
                    telefonoInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(telefonoInput);
				}
				{
					sendBtn = new JButton();
					mainContainer.add(sendBtn);
					sendBtn.setText("Agregar");
                    String btnName = String.join("-", "add", "persona");
                    sendBtn.setName(btnName);
				}
                {
					goHome = new JButton();
					mainContainer.add(goHome);
					goHome.setText("home");
					goHome.setName("go-hom001");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public Persona getPersona() {
        Persona persona = new Persona();
        persona.setCedula(cedulaInput.getValue());
        persona.setNombre(nombreInput.getValue());
        persona.setApellido(apellidoInput.getValue());
        persona.setDireccion(direccionInput.getValue());
        persona.setTelefono(telefonoInput.getValue());
        return persona;
    }

    public void clear(){
        cedulaInput.clear();
        nombreInput.clear();
        apellidoInput.clear();
        direccionInput.clear();
        telefonoInput.clear();
    }

    public void agregarListener(ActionListener accion){
        sendBtn.addActionListener(accion);
        goHome.addActionListener(accion);
    }
}
