package modelos;

import java.util.ArrayList;

public class Fundacion {

    private String id;
    private String nombre;
    private float presupuesto;
    private float porcentajePartidoAnual;
    private ArrayList<Servicio> servicios;
    private ArrayList<Empleado> empleados;

    public Fundacion() {
    };

    public Fundacion(String id, String nombre, float presupuesto, float porcentajePartidoAnual, ArrayList<Servicio> servicios, ArrayList<Empleado> empleados) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.porcentajePartidoAnual = porcentajePartidoAnual;
        this.servicios = servicios;
        this.empleados = empleados;
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

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}
