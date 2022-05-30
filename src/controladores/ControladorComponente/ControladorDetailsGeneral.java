package controladores.ControladorComponente;

import java.util.Map;

import controladores.Mediator.IRouter;

public abstract class ControladorDetailsGeneral extends ControladorGeneral {

    public ControladorDetailsGeneral(String id, IRouter router){
        super(id, router);
    }

    public abstract void updateMap(Map<String, String> map);
}
