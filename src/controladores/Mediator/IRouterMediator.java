package controladores.Mediator;

import java.util.List;

import controladores.ControladorComponente.ControladorGeneral;

public interface IRouterMediator<T> {
    public void notify(T component, String event);

    public void addControlador(ControladorGeneral controller);

    public void clear();

    public void setControladores(List<ControladorGeneral> controladores);

    public boolean removeControlador(String id);

    public ControladorGeneral getControlador(String id);
}