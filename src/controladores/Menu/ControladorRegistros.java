package controladores.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;
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

    public ControladorRegistros(Router router) {
        super("registros", router);
    }

    public void initGUI(){
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

    }

    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
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
                router.notify(this, "go-backOffice");
            }
        }
    }

}
