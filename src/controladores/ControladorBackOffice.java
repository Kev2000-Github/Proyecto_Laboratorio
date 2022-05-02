package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import modelos.Usuario;
import vistas.swing.VentanaBackOffice;
import vistas.swing.VentanaHome;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorBackOffice extends ControladorGeneral {

    VentanaBackOffice window;

    public ControladorBackOffice(Usuario user) {
        super(user);
        window = new VentanaBackOffice(this);
        window.setVisible(true);
    }

   

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getRegistros()) {
            window.dispose();
            new ControladorRegistros(user);
        }
        
        if (source == window.getReportes()) {
         window.dispose();
            new ControladorReportes(user);

        }
  

    }

}
