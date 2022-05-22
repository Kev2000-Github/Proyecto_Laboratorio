package controladores.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;
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

    public ControladorBackOffice(Router router) {
        super("backoffice", router);
    }

    public void initGUI(){
        window = new VentanaBackOffice(this, this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getRegistros()) {
            router.notify(this, "go-registros");
        }
        
        if (source == window.getReportes()) {
            router.notify(this, "go-reportes");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                router.notify(this, "go-home");
            }
            if(lbl.getName() == "goBack"){
                router.notify(this, "go-home");
            }
        }
    }

}
