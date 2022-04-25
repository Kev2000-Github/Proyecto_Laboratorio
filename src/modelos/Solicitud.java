package modelos;

import java.util.ArrayList;

import utils.Constants.*;

public class Solicitud {
    private String id;

    private String empleadoId;
    private String fundacionId;
    private prioridadEnum prioridad;
    private estadoEnum status;
    private Float costoTotal;
    private ArrayList<Servicio> servicios;
    private String beneficiarioId;

    public Solicitud(){}

    public Solicitud(
            String fundaciondestino, 
            String id, 
            float costoTotal,
            ArrayList<Servicio> servicios,
            String beneficiarioId,
            String empleadoId
            ){
        this.fundacionId = fundaciondestino;
        this.id = id;
        this.costoTotal = costoTotal;
        this.servicios = servicios;
        this.beneficiarioId = beneficiarioId;
        this.empleadoId = empleadoId;
    }

    //Metodos sets y gets
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeneficiarioId() {
        return this.beneficiarioId;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void setBeneficiarioId(String beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }


    public String getEmpleadoId() {
        return this.empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getFundacionId() {
        return this.fundacionId;
    }

    public void setFundacionId(String fundacionId) {
        this.fundacionId = fundacionId;
    }

    public prioridadEnum getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(prioridadEnum prioridad) {
        this.prioridad = prioridad;
    }

    public estadoEnum getStatus() {
        return this.status;
    }

    public void setStatus(estadoEnum status) {
        this.status = status;
    }

    public Float getCostoTotal() {
        return this.costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }
  

    public void setCedula(Object selectedItem) {
    }
}