/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import vistas.swing.VentanaReportes;

/**
 *
 * @author prometheus
 */
public class ControladorReportes extends ControladorGeneral {
    VentanaReportes window;

    public ControladorReportes(IRouter router) {
        super("reportes", router);
        this.router = router;
    }
    
    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaReportes(this, this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getSolicitantesList()) {
            router.notify(this, "go-listaSolicitante");
        }
        if (source == window.getPresupuestosList()) {
            router.notify(this, "go-listaPresupuesto");
        }
        if (source == window.getResponsableList()) {
            router.notify(this, "go-listaResponsable");
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
   
