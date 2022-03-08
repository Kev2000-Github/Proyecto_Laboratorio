package bean;
/*
 * Archivo CajaDeTexto.java
 * La clase CajaDeTexto.java hereda de JTextField e implementa la clase Serializable para ejemplarizar un Bean.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.JTextField;


public class JTextFieldValidator extends JTextField implements Serializable {

	 
	// Constantes para indicar los tipos de validación     
	public static final char SOLO_NUMEROS = 'A';
	public static final char SOLO_LETRAS  = 'B';
	public static final char LETRAS_Y_ESPACIOS  = 'C';
	public static final char LETRAS_Y_NUMEROS  = 'D';
	public static final char LETRAS_ESPACIOS_Y_NUMEROS  = 'E';
	public static final char CUALQUIER_CARACTER  = 'Z';
	
    // Atributos de la clase JTextFieldValidator
	private int maximaLongitud = 500;	
	private int tipoCaracteresPermitidos = CUALQUIER_CARACTER;

	
    // Métodos Get y Set	
	public int getMaximaLongitud() {
		return maximaLongitud;
	}

	public void setMaximaLongitud(int maximaLongitud) {
		this.maximaLongitud = maximaLongitud;
	}

	public int getTipoCaracteresPermitidos() {
		return tipoCaracteresPermitidos;
	}

	public void setTipoCaracteresPermitidos(int tipoCaracteresPermitidos) {
		this.tipoCaracteresPermitidos = tipoCaracteresPermitidos;
	}

	//Contructores de la Clase
	public JTextFieldValidator() {
		super();
		this.addKeyListener(this.keyListener);
	}
	
	public JTextFieldValidator(int maximaLongitud) {
		this();
		this.maximaLongitud = maximaLongitud;
	}
	
	public JTextFieldValidator(char tipoCaracteresPermitidos) {
		this();
		this.tipoCaracteresPermitidos = tipoCaracteresPermitidos;
	}
	
	public JTextFieldValidator(int maximaLongitud, char tipoCaracteresPermitidos) {
		this();
		this.maximaLongitud = maximaLongitud;
		this.tipoCaracteresPermitidos = tipoCaracteresPermitidos;
	}
	
	
	private KeyListener keyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent keyEvent) {
			validar(keyEvent);
		}
		
		@Override
		public void keyReleased(KeyEvent arg0) {

		}
		
		@Override
		public void keyPressed(KeyEvent arg0) {
			
		}
	};
	
	private void validar(KeyEvent keyEvent) {
		switch (tipoCaracteresPermitidos) {
		case SOLO_NUMEROS:
			if ((!Character.isDigit(keyEvent.getKeyChar())) ||
		    	(((JTextField)keyEvent.getSource())).getText().length() >= maximaLongitud){					
		        	keyEvent.consume();
		        	getToolkit().beep();
			} 	
			break;
		case SOLO_LETRAS:
			if ((!Character.isLetter(keyEvent.getKeyChar())) ||
		    	(((JTextField)keyEvent.getSource())).getText().length() >= maximaLongitud){					
	        	keyEvent.consume();
	        	getToolkit().beep();
		    } 	
			break;
		case LETRAS_Y_ESPACIOS: {
			boolean a = !Character.isLetter(keyEvent.getKeyChar()) &&
						keyEvent.getKeyChar() != keyEvent.VK_SPACE;
			boolean b = (((JTextField)keyEvent.getSource())).getText().length() >= maximaLongitud; 
			if (a || b){					
	        	 keyEvent.consume();
	        	 getToolkit().beep();
		       } 	
			}
			break;
		case LETRAS_ESPACIOS_Y_NUMEROS: {
			boolean a = !Character.isDigit(keyEvent.getKeyChar()) &&
						!Character.isLetter(keyEvent.getKeyChar()) &&
						keyEvent.getKeyChar() != keyEvent.VK_SPACE;
			boolean b = (((JTextField)keyEvent.getSource())).getText().length() >= maximaLongitud; 
			if (a || b){					
	        	keyEvent.consume();
	        	getToolkit().beep();
		      } 	
			}
			break;
		case CUALQUIER_CARACTER:
			if (((JTextField)keyEvent.getSource()).getText().length() >= maximaLongitud){					
	        	keyEvent.consume();
	        	getToolkit().beep();
		  } 	
			break;
		}
	}

}
