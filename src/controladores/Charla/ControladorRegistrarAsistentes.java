/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.Charla;

import DAO.CharlaDao;
import DAO.general.DaoFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.ControladorComponente.ControladorUpdateGeneral;
import controladores.Mediator.IRouter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import vistas.swing.VentanaRegistrarAsistentes;

/**
 *
 * @author sarah
 */
public class ControladorRegistrarAsistentes extends ControladorUpdateGeneral{
    
    VentanaRegistrarAsistentes window;
    DaoFactory daoFactory;
    CharlaDao charlaDao;
    String charlaId;
    
    public ControladorRegistrarAsistentes(IRouter router) {
        super("registrarAsistentes", router);
        daoFactory = new DaoFactory();
        charlaDao = new CharlaDao();
    }
    
    //REVISAR: 
    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }
    
    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaRegistrarAsistentes(this, this);
        window.setVisible(true);
        setIdLabel(charlaId);

    }

    public void closeGUI(){
        window.dispose();
    }
    
    public void updateId(String id){
        charlaId = id;
    }
    
    public void setIdLabel(String id){
        window.getLblIdCharla().setText(id);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        var source = e.getSource();

        if (source == window.getBtnRegistrar()) {
        
            String ci = window.getEntryCedula().getText();
            
            if(ci != null){
                if(ci.matches("[+-]?\\d*(\\.\\d+)?")==true && ci.length()==8){
                    
                    if(charlaDao.existingAsistente(ci,charlaId)==false){
                        charlaDao.saveAsistente(ci, charlaId);
                        window.setEntryCedula("");
                        mostrarMensaje("Registro Exitoso");
                    }else{
                        mostrarMensaje("El asistente que desea registrar ya existe");                        
                    }

                }else{
                    mostrarMensaje("Porfavor ingrese un numero de cedula valido");
                    window.setEntryCedula("");
                }
                
            }else{
                mostrarMensaje("Porfavor ingrese cedula");  
            }
        }
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
    
}
