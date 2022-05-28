package controladores.ControladorComponente;

import java.util.Map;

import controladores.Mediator.Router;

public abstract class ControladorDetailsGeneral extends ControladorGeneral {

    public ControladorDetailsGeneral(String id, Router router){
        super(id, router);
    }

    public abstract void updateMap(Map<String, String> map);
}
