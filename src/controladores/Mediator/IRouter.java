package controladores.Mediator;

import java.util.List;

import controladores.ControladorComponente.ControladorGeneral;

public interface IRouter {
    public void notify(ControladorGeneral component, String event);

    public void clear();

    public void setControladores(List<ControladorGeneral> controladores);

    public void addControlador(ControladorGeneral controller);

    public boolean removeControlador(String id);

    public void addRoute(String location);

    public void popRoute();

    public String getURI();
    
    public void clearURI();

    public ControladorGeneral getControlador(String id);
}