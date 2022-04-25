package vistas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import vistas.general.VentanaGeneral;

import java.awt.event.ActionListener;

public class VentanaHome extends VentanaGeneral{
    private JLabel lblTitulo;
    private JButton goCrearSolicitud;
    private JButton goGestionarSolicitud;
    private JButton goBackOffice;
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
					goCrearSolicitud = new JButton();
					mainContainer.add(goCrearSolicitud);
					goCrearSolicitud.setText("Crear solicitud");
					goCrearSolicitud.setName("goSection-crearSolicitud");
				}
				{
					goGestionarSolicitud = new JButton();
					mainContainer.add(goGestionarSolicitud);
					goGestionarSolicitud.setText("Gestionar Solicitud");
					goGestionarSolicitud.setName("goSection-gestSolicitud");
				}
				{
					goBackOffice = new JButton();
					mainContainer.add(goBackOffice);
					goBackOffice.setText("BackOffice");
					goBackOffice.setName("go-backOffice");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public void agregarListener(ActionListener accion){
        goCrearSolicitud.addActionListener(accion);
		goGestionarSolicitud.addActionListener(accion);
		goBackOffice.addActionListener(accion);
    }
}
