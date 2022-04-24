package modelos;

import java.util.ArrayList;

import utils.Constants.*;

public class Solicitud {
    private String fundacionDestino;
    private String id;
    private float costo_total;

    private ArrayList<Servicio> servicios;
    private Beneficiario beneficiario;
    private Empleado empleado;
    private prioridadEnum prioridad;
    private tipoAyudaEnum tipoAyuda;
    private estadoEnum estado;

    //Constructores
    public Solicitud(){};

    public Solicitud(
            String fundaciondestino, 
            String id, 
            float costo_total,
            ArrayList<Servicio> servicios,
            Beneficiario beneficiario,
            Empleado empleado
            ){
        this.fundacionDestino = fundaciondestino;
        this.id = id;
        this.costo_total = costo_total;
        this.servicios = servicios;
        this.beneficiario = beneficiario;
        this.empleado = empleado;
    }

    //Metodos sets y gets
        public float getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(float costo_total) {
        this.costo_total = costo_total;
    }

    public void setFundacionDestino(String fundacionDestino){
        this.fundacionDestino = fundacionDestino;
    }
    
    public String getFundacionDestino(){
        return fundacionDestino;
    }

    public void setServicios(ArrayList<Servicio> servicios){
        this.servicios = servicios;
    }

    public ArrayList<Servicio> getServicios(){
        return servicios;
    }

    public void setBeneficiario(Beneficiario beneficiario){

        this.beneficiario = beneficiario;
    }

    public Beneficiario getBeneficiario(){

        return beneficiario;
    }

    public void setEmpleado(Empleado empleado){

        this.empleado = empleado;
    }

    public Empleado getEmpleado(){

        return empleado;
    }

    public prioridadEnum getPrioridad(){
        return prioridad;
    }

    public void setPrioridad(prioridadEnum prioridad){
        this.prioridad = prioridad;
    }

    public tipoAyudaEnum getTipoAyuda(){
        return tipoAyuda;
    }

    public void setTipoAyuda(tipoAyudaEnum tipoAyuda){
        this.tipoAyuda = tipoAyuda;
    }

    public estadoEnum getEstado(){
        return estado;
    }

    public void setEstado(estadoEnum estado){
        this.estado = estado;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){ return id; }

}