package controladores.Mediator;
import java.util.ArrayList;
import java.util.List;

import controladores.ControladorComponente.ControladorDetailsGeneral;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.ControladorComponente.ControladorUpdateGeneral;
import controladores.Solicitud.ControladorGestionarSolicitudes;

public class Router {
    private List<ControladorGeneral> controladores;

    public Router(){
        controladores = new ArrayList<ControladorGeneral>();
    }

    private ControladorGeneral getControlador(String id){
        for(ControladorGeneral controlador : controladores){
            String contId = controlador.getId();
            if(id.equals(contId)){
                return controlador;
            }
        }
        return null;
    }

    private void changeLocation(ControladorGeneral prevLocation, ControladorGeneral newLocation){
        prevLocation.closeGUI();
        newLocation.setUser(prevLocation.getUser());
        newLocation.initGUI();
    }

    private void toDetails(ControladorGestionarSolicitudes prevLocation, ControladorDetailsGeneral newLocation){
        prevLocation.closeGUI();
        newLocation.setUser(prevLocation.getUser());
        newLocation.initGUI();
        newLocation.updateMap(prevLocation.getSelectedSolicitud());
    }

    private void toUpdate(ControladorGeneral prevLocation, ControladorUpdateGeneral newLocation, String id){
        prevLocation.closeGUI();
        newLocation.setUser(prevLocation.getUser());
        newLocation.updateId(id);
        newLocation.initGUI();
    }

    public void addControlador(ControladorGeneral controller){
        controladores.add(controller);
    }

    public void clear() {
        controladores.clear();
    }

    public void setControladores(List<ControladorGeneral> controladores){
        this.controladores = controladores;
    }

    public boolean removeControlador(String id){
        ControladorGeneral controller = getControlador(id);
        if(controller == null){
            return false;
        }
        return controladores.remove(controller);
    }

    public void notify(ControladorGeneral component, String event){
        String[] eventContent = event.split("-");
        String action = eventContent[0];
        String to = eventContent[1];
        ControladorGeneral newControlador = getControlador(to);
        if(newControlador == null){
            return;
        }
        switch(action){
            case "go":
                if(to.equals("detallesolicitud")){
                    toDetails(
                            (ControladorGestionarSolicitudes) component, 
                            (ControladorDetailsGeneral) newControlador
                        );
                }
                else{
                    changeLocation(component, newControlador);
                }
                break;
            case "update":
                String id = eventContent[2];
                toUpdate(component, (ControladorUpdateGeneral)newControlador, id);
        }
    }


}
