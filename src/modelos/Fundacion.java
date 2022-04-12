package modelos;

public class Fundacion {

    private String id;
    private String nombre;
    private float presupuesto;
    private float porcentajePartidoAnual;

    public Fundacion() {
    };

    public Fundacion(String id, String nombre, float presupuesto, float porcentajePartidoAnual) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.porcentajePartidoAnual = porcentajePartidoAnual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public float getPorcentajePartidoAnual() {
        return porcentajePartidoAnual;
    }

    public void setPorcentajePartidoAnual(float porcentajePartidoAnual) {
        this.porcentajePartidoAnual = porcentajePartidoAnual;
    }

}
