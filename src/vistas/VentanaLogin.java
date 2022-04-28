package vistas;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import vistas.general.MetodosGenerales;
import vistas.general.InputField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Color;

public class VentanaLogin extends MetodosGenerales{
    private JLabel lblTitulo;
    private InputField usernameInput;
    private InputField passwordInput;
    private JButton sendBtn;

    public VentanaLogin(ActionListener accion){
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

					lblTitulo.setText("Login");
					lblTitulo.setFont(new java.awt.Font("Dialog",1,16));
                    lblTitulo.setBorder(new EtchedBorder());
				}
                {
                    usernameInput = new InputField("username:");
                    usernameInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(usernameInput);
				}
                {
                    passwordInput = new InputField("password:");
                    passwordInput.setPreferredSize(new Dimension(100,10));
                    mainContainer.add(passwordInput);
				}
				{
					sendBtn = new JButton();
					mainContainer.add(sendBtn);
					sendBtn.setText("Login");
				}
			}
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

    public String getUsername(){
        return usernameInput.getValue();
    }

    public String getPassword(){
        return passwordInput.getValue();
    }

    public void agregarListener(ActionListener accion){
        sendBtn.addActionListener(accion);
    }
}
