package vistas.general;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

import utils.Utils;

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

    public String mostrarMensajeInput(String mensaje){
        String valor = JOptionPane.showInputDialog(mensaje);
        return valor;
    }

    //getSaltString
    public String getSaltString() {
        return Utils.genRandomSalt();
    }
}
