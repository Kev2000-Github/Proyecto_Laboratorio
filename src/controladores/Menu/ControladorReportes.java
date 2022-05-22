/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;
import vistas.swing.VentanaReportes;

/**
 *
 * @author prometheus
 */
public class ControladorReportes extends ControladorGeneral {
    VentanaReportes window;

    public ControladorReportes(Router router) {
        super("reportes", router);
        this.router = router;
    }
    
    public void initGUI(){
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
            router.notify(this, "go-listasolicitante");
        }
        if (source == window.getPresupuestosList()) {
            router.notify(this, "go-listapresupuesto");
        }
        if (source == window.getResponsableList()) {
            router.notify(this, "go-listaresponsable");
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
                router.notify(this, "go-backoffice");
            }
        }
    }
}
   
