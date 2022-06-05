package controladores.ControladorComponente;

import controladores.Mediator.IRouter;

public interface IControladorFactory {
    public ControladorGeneral getControlador(String nombre, IRouter router);
}
