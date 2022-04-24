package vistas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import vistas.general.VentanaGeneral;
import vistas.general.InputField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Color;

public class VentanaHome extends VentanaGeneral{
    private JLabel lblTitulo;
    private JButton goEmpleados;
    private JButton goBeneficiarios;
    private JButton goSolicitudes;

    public VentanaHome(ActionListener accion){
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

					lblTitulo.setText("Home");
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
				{
					goEmpleados = new JButton();
					mainContainer.add(goEmpleados);
					goEmpleados.setText("Empleados");
					goEmpleados.setName("goList-emp001");
				}
				{
					goBeneficiarios = new JButton();
					mainContainer.add(goBeneficiarios);
					goBeneficiarios.setText("Beneficiarios");
					goBeneficiarios.setName("goList-ben001");
				}
				{
					goBeneficiarios = new JButton();
					mainContainer.add(goBeneficiarios);
					goBeneficiarios.setText("Solicitudes");
					goBeneficiarios.setName("go-solicitudes");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public void agregarListener(ActionListener accion){
        goEmpleados.addActionListener(accion);
		goBeneficiarios.addActionListener(accion);
    }
}
