package modelos;

public class Fundacion {

    private String id;
    private String nombre;
    private double presupuesto;
    private double porcentaje_partido_anual;

    public Fundacion() {
    };

    public Fundacion(String id, String nombre, double presupuesto, double porcentaje_partido_anual) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.porcentaje_partido_anual = porcentaje_partido_anual;
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

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getPorcentaje_partido_anual() {
        return Porcentaje_partido_anual;
    }

    public void setPorcentaje_partido_anual(double porcentaje_partido_anual) {
        this.Porcentaje_partido_anual = Porcentaje_partido_anual;
    }

}
