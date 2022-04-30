package vistas.general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

public class MetodosGenerales extends javax.swing.JFrame {

    protected JPanel mainContainer;
    private Color background;

    public MetodosGenerales() {
        super();

    }
    //mostrarMensaje
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    //getSaltString
    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
