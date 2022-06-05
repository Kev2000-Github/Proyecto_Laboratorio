package controladores.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
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

    public ControladorRegistros(IRouter router) {
        super("registros", router);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaRegistros(this, this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getBeneficiarios()) {
            router.notify(this, "go-beneficiario");
        }

        if (source == window.getEmpleados()) {
            router.notify(this, "go-empleado");
        }
        
        if (source == window.getCharlas()){
            router.notify(this, "go-charla");
        }

    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

}
