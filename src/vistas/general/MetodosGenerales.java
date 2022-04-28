package vistas.general;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.WindowConstants;


public class MetodosGenerales extends javax.swing.JFrame {
	protected JPanel mainContainer;
	private Color background;
	
	public MetodosGenerales() {
		super();
		
	}
	
	public void mostrarMensaje(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje);
	}
}
