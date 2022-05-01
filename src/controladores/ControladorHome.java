package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import modelos.Usuario;
import vistas.swing.VentanaHome;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorHome extends ControladorGeneral {

    VentanaHome window;

    public ControladorHome(Usuario user) {
        super(user);
        window = new VentanaHome(this);
        window.setVisible(true);
    }

    private void goBackOffice() {
        window.dispose();

    }

    private void goGestionarSolicitud() {
           window.dispose();

    }

    private void goCrearSolicitud() {
           window.dispose();
           
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getBack_office()) {
            goBackOffice();
            new ControladorBeneficiario(user);
            
        }
        if (source == window.getGestionar_solicitud()) {
            goGestionarSolicitud();
            new ControladorGestionarSolicitudes(user);
        }
        if (source == window.getCrear_solicitud()) {
            goCrearSolicitud();
            new ControladorAddSolicitud(user);
        }

    }

}
