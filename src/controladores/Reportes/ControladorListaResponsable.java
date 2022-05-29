/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Reportes;
import javax.swing.table.DefaultTableModel;

import modelos.Empleado;
import modelos.Solicitud;
import vistas.swing.VentanaListaResponsable;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JLabel;
import DAO.EmpleadoDao;
import DAO.SolicitudDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
/**
 *
 * @author prometheus
 */
public class ControladorListaResponsable extends ControladorGeneral {
    VentanaListaResponsable window;
    SolicitudDao solicitudDao;
    
      public ControladorListaResponsable(IRouter router) {
        super("listaResponsable", router);
        solicitudDao = new SolicitudDao();
      }

      public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

        
    public void initGUI(){
        window = new VentanaListaResponsable(this, this);
        window.setVisible(true);
        fillResponsables();
    }

    public void closeGUI(){
        window.dispose();
    }
        
    public void fillResponsables() {
        DefaultTableModel modelResponsables = new DefaultTableModel();
        List<Solicitud> solicitudes = solicitudDao.getAllPending();  
        modelResponsables.setColumnCount(2);
        modelResponsables.setColumnIdentifiers(new String[]{"Solicitud", "Empleado"});

        for (Solicitud s : solicitudes) {
    
            EmpleadoDao empleadoDao = new EmpleadoDao();
            Empleado empleado = empleadoDao.get(s.getEmpleadoId());
            modelResponsables.addRow(new Object[]{s.getId(),  
                empleado.getNombre() +" "+ empleado.getApellido()});
        }
        window.setModelResponsables(modelResponsables);
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
                router.notify(this, "go-reportes");
            }
        }
    }
     
    
    
}
