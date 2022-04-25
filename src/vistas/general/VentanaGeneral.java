package vistas.general;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.WindowConstants;


public class VentanaGeneral extends javax.swing.JFrame {
	protected JPanel mainContainer;
	private Color background;
	
	public VentanaGeneral() {
		super();
		this.setVisible(true);
		initGUI();
		this.setLocationRelativeTo(null);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				mainContainer = new JPanel();
				mainContainer.setBackground(background);
				this.add(mainContainer, BorderLayout.CENTER);
				mainContainer.setLayout(new BorderLayout());
			}
			pack();
			setSize(500, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}	
	
	public void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
}
