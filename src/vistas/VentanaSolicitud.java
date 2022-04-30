package vistas;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;

import vistas.general.MetodosGenerales;
import vistas.general.InputField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class VentanaSolicitud extends MetodosGenerales {
    private JLabel lblTitulo;
    private InputField codigoInput;
    private InputField beneficiarioInput;
    private InputField empleadoInput;
    private InputField fundacionInput;
    private InputField prioridadInput;

    private JComboBox<String> comboServicio;
    private JLabel lblComSevi;
    private JButton sendBtn;
    private JButton goHome;
    private JButton agregarServicio;

    public VentanaSolicitud(ActionListener accion) {
        super();
        initGUI();
        this.agregarListener(accion);
    }

    private void initGUI() {
        try {
            mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));

            {
                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Solicitudes");

                {
                    lblTitulo = new JLabel();
                    mainContainer.add(lblTitulo);
                    lblTitulo.setText("Registro de Solicitud");
                    lblTitulo.setFont(new java.awt.Font("Dialog", 1, 16));
                    lblTitulo.setBorder(new EtchedBorder());
                }


                {
                    beneficiarioInput = new InputField("Beneficiario:");
                    beneficiarioInput.setPreferredSize(new Dimension(100, 30));
                    beneficiarioInput.setEnabled(true);
                    mainContainer.add(beneficiarioInput);
                }

                {
                    empleadoInput = new InputField("Empleado:");
                    empleadoInput.setPreferredSize(new Dimension(100, 30));
                    empleadoInput.setEnabled(true);
                    mainContainer.add(empleadoInput);
                }

                {
                    fundacionInput = new InputField("Fundacion:");
                    fundacionInput.setPreferredSize(new Dimension(100, 30));
                    fundacionInput.setEnabled(true);
                    mainContainer.add(fundacionInput);
                }

                {
                    prioridadInput = new InputField("Prioridad:");
                    prioridadInput.setPreferredSize(new Dimension(100, 30));
                    prioridadInput.setEnabled(true);
                    mainContainer.add(prioridadInput);
                }

                {
                    setLayout(new BorderLayout());
                    lblComSevi = new JLabel();
                    lblComSevi.setText("Servicios: ");
                    mainContainer.add(lblComSevi);
                    add(mainContainer, BorderLayout.CENTER);
                    comboServicio = new JComboBox<String>();
                    comboServicio.addItem("SELECCION");
                    mainContainer.add(comboServicio);
                    add(mainContainer, BorderLayout.NORTH);

                }

                {
                    sendBtn = new JButton();
                    mainContainer.add(sendBtn);
                    sendBtn.setText("Registrar Solicitud");
                }

            

            }
        } catch (Exception e) {
            // add your error handling code here
            e.printStackTrace();
        }
    }

    public void agregarListener(ActionListener accion) {
        sendBtn.addActionListener(accion);
    }
}
