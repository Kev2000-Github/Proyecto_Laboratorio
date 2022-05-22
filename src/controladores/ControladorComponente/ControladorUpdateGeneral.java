package controladores.ControladorComponente;

import controladores.Mediator.Router;

public abstract class ControladorUpdateGeneral extends ControladorGeneral {

    public ControladorUpdateGeneral(String id, Router router){
        super(id, router);
    }

    public abstract void updateId(String id);
}
