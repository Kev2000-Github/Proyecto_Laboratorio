package controladores.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import vistas.swing.VentanaBackOffice;

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

    public ControladorBackOffice(IRouter router) {
        super("backOffice", router);
    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    public void initGUI(){
        router.addRoute(this.id);
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
        super.mouseClicked(e);
    }

}
