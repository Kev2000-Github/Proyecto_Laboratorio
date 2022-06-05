package controladores.Mediator;

import java.util.List;

import controladores.ControladorComponente.ControladorGeneral;
import modelos.Permiso;
import modelos.Rol;
import modelos.Usuario;

public class AuthDecorator implements IRouter {
    protected IRouter wrapee;

    public AuthDecorator(IRouter router){
        wrapee = router;
    }

    public void notify(ControladorGeneral component, String event){
        String[] eventContent = event.split("-");
        String locationId = eventContent[1];
        Usuario user = component.getUser();
        Rol userRole = user.getRol();
        List<Permiso> rolePermissions = userRole.getPermisos();
        boolean hasPermission = false;
        for(Permiso permission : rolePermissions){
            if(locationId.equals(permission.getId())){
                hasPermission = true;
                break;
            }
        }
        if(hasPermission){
            wrapee.notify(component, event);
        }
        else{
            System.out.println("User does not have required permissions");
            component.mostrarMensaje("El usuario no tiene los permisos requeridos");
        }
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
