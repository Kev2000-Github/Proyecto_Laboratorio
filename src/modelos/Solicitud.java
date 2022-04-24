package modelos;

public class Solicitud {

    private enum prioridadEnum {
        alta, media, baja
    };

    private enum estadoEnum {
        aprobado, negado, pendiente
    }

    private String beneficiarioId;

    public String getBeneficiarioId() {
        return this.beneficiarioId;
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
    private String empleadoId;
    private String fundacionId;
    private prioridadEnum prioridad;
    private estadoEnum status;
    private Float costoTotal;

}
