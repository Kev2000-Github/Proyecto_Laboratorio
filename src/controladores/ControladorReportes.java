/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import modelos.Usuario;
import vistas.swing.VentanaReportes;

/**
 *
 * @author prometheus
 */
public class ControladorReportes extends ControladorGeneral {

    VentanaReportes window;

    public ControladorReportes(Usuario user) {
        super(user);
        window = new VentanaReportes(this,this);
        window.setVisible(true);
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getSolicitantesList()) {
            window.dispose();
            new ControladorListaSolicitante(user);
        }
        if (source == window.getPresupuestosList()) {
            window.dispose();
            new ControladorListaPresupuesto(user);
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
                new ControladorBackOffice(user);
            }
        }
    }
}
   
