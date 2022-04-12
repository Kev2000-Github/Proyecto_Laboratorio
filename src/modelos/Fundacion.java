package modelos;

public class Fundacion {

    private String id;
    private String nombre;
    private String presupuesto;
    private String porcentajePartidoAnual;

    public Fundacion() {
    };

    public Fundacion(String id, String nombre, String presupuesto, String porcentajePartidoAnual) {
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

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getPorcentajePartidoAnual() {
        return porcentajePartidoAnual;
    }

    public void setPorcentajePartidoAnual(String porcentajePartidoAnual) {
        this.porcentajePartidoAnual = porcentajePartidoAnual;
    }

}
