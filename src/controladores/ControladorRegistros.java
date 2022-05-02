package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import modelos.Usuario;
import vistas.swing.VentanaBackOffice;
import vistas.swing.VentanaHome;
import vistas.swing.VentanaRegistros;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorRegistros extends ControladorGeneral {

    VentanaRegistros window;

    public ControladorRegistros(Usuario user) {
        super(user);
        window = new VentanaRegistros(this);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getBeneficiarios()) {
            window.dispose();
            new ControladorBeneficiario(user);
        }

        if (source == window.getEmpleados()) {
            window.dispose();
            new ControladorEmpleado(user);
        }

    }



}
