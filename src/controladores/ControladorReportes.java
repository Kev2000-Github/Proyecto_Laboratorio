/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import modelos.Usuario;
import vistas.swing.VentanaBackOffice;
import vistas.swing.VentanaHome;
import vistas.swing.VentanaReportes;

/**
 *
 * @author prometheus
 */
public class ControladorReportes extends ControladorGeneral {

    VentanaReportes window;

    public ControladorReportes(Usuario user) {
        super(user);
        window = new VentanaReportes(this);
        window.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getSolicitantes_list()) {
            window.dispose();
            new ControladorListaSolicitante(user);
        }
    
    }
}