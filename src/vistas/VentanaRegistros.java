package vistas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import vistas.general.MetodosGenerales;

import java.awt.event.ActionListener;
public class VentanaRegistros extends MetodosGenerales{
    private JLabel lblTitulo;
    private JButton goEmpleados;
    private JButton goBeneficiarios;

    public VentanaRegistros(ActionListener accion){
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

					lblTitulo.setText("Registros");
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