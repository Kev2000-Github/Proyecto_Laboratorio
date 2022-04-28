package vistas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.border.EtchedBorder;

import vistas.general.MetodosGenerales;

import java.awt.event.ActionListener;
import java.awt.Color;

public class VentanaBackOffice extends MetodosGenerales {
	private JLabel lblTitulo;
	private JButton goRegistros;
	private JButton goReportes;

	public VentanaBackOffice(ActionListener accion) {
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

					lblTitulo.setText("Backoffice");
					lblTitulo.setFont(new java.awt.Font("Dialog", 1, 16));
					lblTitulo.setBorder(new EtchedBorder());
				}
				{
					goRegistros = new JButton();
					mainContainer.add(goRegistros);
					goRegistros.setText("Registros");
					goRegistros.setName("goSection-registros");
				}
				{
					goReportes = new JButton();
					mainContainer.add(goReportes);
					goReportes.setText("Reportes");
					goReportes.setName("goSection-reportes");
				}
			}
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public void agregarListener(ActionListener accion) {
		goRegistros.addActionListener(accion);
		goReportes.addActionListener(accion);
	}
}
