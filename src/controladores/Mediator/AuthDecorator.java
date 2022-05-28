package controladores.Mediator;

import java.util.List;

import controladores.ControladorComponente.ControladorGeneral;
import modelos.Permiso;
import modelos.Rol;
import modelos.Usuario;

public class AuthDecorator implements IRouterMediator<ControladorGeneral> {
    protected Router wrapee;

    public AuthDecorator(Router router){
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
            if(permission.getId() == locationId){
                hasPermission = true;
            }
        }
        if(hasPermission){
            wrapee.notify(component, event);
        }
        else{
            System.out.println("Tu usuario no tiene los permisos para entrar");
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
}
