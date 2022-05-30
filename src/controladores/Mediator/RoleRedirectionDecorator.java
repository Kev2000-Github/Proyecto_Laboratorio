package controladores.Mediator;

import controladores.ControladorComponente.ControladorGeneral;
import java.util.List;

public class RoleRedirectionDecorator implements IRouter {
    protected IRouter wrapee;

    public RoleRedirectionDecorator(IRouter router){
        wrapee = router;
    }

    public void notify(ControladorGeneral component, String event){
        String prevLocationId = component.getId();
        String roleName = component.getUser().getRol().getNombre();
        if(prevLocationId.equals("login")){
            switch(roleName){
                case "supervisor":
                    wrapee.notify(component, "go-solicitudes");
                    break;
                case "operador":
                    wrapee.notify(component, "go-addSolicitud");
                    break;
                default:
                    wrapee.notify(component, event);
                    break;
            }
            return;
        }
        wrapee.notify(component, event);
    }

    public void addControlador(ControladorGeneral controller){
        wrapee.addControlador(controller);
    }

    public void clear(){
        wrapee.clear();
    }

    public void setControladores(List<ControladorGeneral> controladores){
        wrapee.setControladores(controladores);
    }

    public boolean removeControlador(String id){
        return wrapee.removeControlador(id);
    }

    public ControladorGeneral getControlador(String id){
        return wrapee.getControlador(id);
    }

    public void addRoute(String location){
        wrapee.addRoute(location);
    }

    public void popRoute(){
        wrapee.popRoute();
    }

    public String getURI(){
        return wrapee.getURI();
    }

    public void clearURI(){
        wrapee.clearURI();
    }
}
