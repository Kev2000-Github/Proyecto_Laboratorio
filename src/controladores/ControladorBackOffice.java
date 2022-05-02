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
        window = new VentanaBackOffice(this, this);
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

        }
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
            if(lbl.getName() == "goBack"){
                window.dispose();
                new ControladorHome(user);
            }
        }
    }

}
