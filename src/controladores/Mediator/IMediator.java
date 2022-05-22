package controladores.Mediator;

public interface IMediator<T> {
    public void notify(T component, String event);
}