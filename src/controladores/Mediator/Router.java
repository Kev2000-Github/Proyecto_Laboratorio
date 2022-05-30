package controladores.Mediator;
import java.util.ArrayList;
import java.util.List;

import controladores.ControladorComponente.ControladorDetailsGeneral;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.ControladorComponente.ControladorUpdateGeneral;
import controladores.Solicitud.ControladorGestionarSolicitudes;

public class Router implements IRouter {
    private List<ControladorGeneral> controladores;
    private List<String> route;

    public Router(){
        route = new ArrayList<String>();
        controladores = new ArrayList<ControladorGeneral>();
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

    private String[] getRoutingInfo(String event){
        String[] eventContent = event.split("-");
        if(eventContent.length < 2){
            String[] result = {eventContent[0], null};
            return result;
        }
        return eventContent;
    }

    public void addControlador(ControladorGeneral controller){
        controladores.add(controller);
    }

    public void addRoute(String location){
        route.add(location);
    }

    public void popRoute(){
        int lastIndex = route.size() - 1;
        route.remove(lastIndex);
    }

    public String getURI(){
        String URI = String.join("/", route);
        return URI;
    }

    public void clear() {
        controladores.clear();
        route.clear();
    }

    public void clearURI(){
        route.clear();
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

    public ControladorGeneral getControlador(String id){
        for(ControladorGeneral controlador : controladores){
            String contId = controlador.getId();
            if(id.equals(contId)){
                return controlador;
            }
        }
        return null;
    }

    public void notify(ControladorGeneral component, String event){
        System.out.println(getURI());
        String[] eventContent = getRoutingInfo(event);
        String action = eventContent[0];
        String to = eventContent[1];
        ControladorGeneral newControlador = getControlador(to);
        if(newControlador == null){
            System.out.println("Controller doesn't exist");
            return;
        }
        switch(action){
            case "go":
                if(to.equals("detalleSolicitud")){
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
                break;
        }
    }

}
