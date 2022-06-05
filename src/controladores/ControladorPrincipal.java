package controladores;

import java.util.ArrayList;
import java.util.List;

import controladores.ControladorComponente.ControladorFactory;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.AuthDecorator;
import controladores.Mediator.IRouter;
import controladores.Mediator.RoleRedirectionDecorator;
import controladores.Mediator.Router;

public class ControladorPrincipal {
    IRouter router;
    private List<ControladorGeneral> controladores;

    public ControladorPrincipal(){
        router = new Router();
        //router = new AuthDecorator(router);
        //router = new RoleRedirectionDecorator(router);
        initControladores();
        ControladorGeneral login = getControlador("login");
        login.initGUI();
    }

    private void initControladores(){
        controladores = new ArrayList<ControladorGeneral>();
        ControladorFactory controladorFactory = new ControladorFactory();

        for(String controladorName : ControladorFactory.CONTROLADORES){
            ControladorGeneral controlador = controladorFactory.getControlador(controladorName, router);
            controladores.add(controlador);
        }

        router.setControladores(controladores);
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

}
