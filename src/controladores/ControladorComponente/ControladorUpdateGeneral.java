package controladores.ControladorComponente;

import controladores.Mediator.IRouter;

public abstract class ControladorUpdateGeneral extends ControladorGeneral {

    public ControladorUpdateGeneral(String id, IRouter router){
        super(id, router);
    }

    public abstract void updateId(String id);
}
