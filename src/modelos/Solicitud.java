package modelos;

public class Solicitud {

    //atributos
    private enum prioridadEnum { alta, media, baja };
    private enum tipoAyudaEnum { medica, donacion };
    private enum estadoEnum { aprobado, negado, pendiente };

    private String fundacionDestino;

    private Servicio servicio;
    private Beneficiario beneficiario;
    private Empleado empleado;
    private prioridadEnum prioridad;
    private tipoAyudaEnum tipoAyuda;
    private estadoEnum estado;

    //Constructores
    public Solicitud(){};

    public Solicitud(String fundaciondestino){

        this.fundacionDestino = fundaciondestino;
        this.servicio = new Servicio();
        this.beneficiario = new Beneficiario();//no existe este constructor
        this.empleado = new Empleado();
    }

    //Metodos sets y gets

    public void setFundacionDestino(String fundacionDestino){
        this.fundacionDestino = fundacionDestino;
    }
    
    public String getFundacionDestino(){
        return fundacionDestino;
    }

    public void setServicio(Servicio servicio){

        this.servicio = servicio;
    }

    public Servicio getServicio(){

        return servicio;
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

    public void setEstado(tipoAyudaEnum tipoAyuda){
        this.tipoAyuda = tipoAyuda;
    }

    public estadoEnum getEstado(){
        return estado;
    }

    public void setPrioridad(estadoEnum estado){
        this.estado = estado;
    }

}
