package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelos.Usuario;
import vistas.VentanaHome;
import vistas.backOffice.VentanaBeneficiarios;
import vistas.backOffice.VentanaEmpleados;
public class Controlador implements ActionListener {
        
	VentanaHome window;
	VentanaBeneficiarios beneficiariosBackOffice;
	VentanaEmpleados empleadosBackOffice;

    Usuario user = null;	
	
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
		window = new VentanaHome();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.agregarListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String name = btn.getName();
		if(name == "gotoBeneficiarios"){
			//window.dispose();
			//beneficiariosBackOffice = new VentanaBeneficiarios();
			//beneficiariosBackOffice.agregarListener(this);
		}
		else if(name == "gotoEmpleados"){
			window.dispose();
			empleadosBackOffice = new VentanaEmpleados();
			empleadosBackOffice.agregarListener(this);
		}
		else if(name == "deleteBeneficiario"){
			beneficiariosBackOffice.mostrarMensaje("Are you sure about that?");
		}
		else if(name == "editBeneficiario"){
			beneficiariosBackOffice.mostrarMensaje("Editar");
		}
		else if(name == "gotoRegisterPerson"){
			beneficiariosBackOffice.mostrarMensaje("gotoRegisterPerson");
		}
		else if(name == "deleteEmpleado"){
			empleadosBackOffice.mostrarMensaje("Are you sure about that?");
		}
		else if(name == "editEmpleado"){
			empleadosBackOffice.mostrarMensaje("Editar");
		}
		else if(name == "gotoRegisterEmpleado"){
			empleadosBackOffice.mostrarMensaje("gotoRegisterPerson");
		}
	}
}
