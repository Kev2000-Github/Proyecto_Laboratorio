package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modelos.Usuario;
import vistas.swing.VentanaGestionarSolicitud;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorGestionarSolicitudes extends ControladorGeneral {

    VentanaGestionarSolicitud window;

    public ControladorGestionarSolicitudes(Usuario user) {
        super(user);
        window = new VentanaGestionarSolicitud(this, this);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                window.dispose();
                new ControladorHome(user);
            }
        }
    }
}
