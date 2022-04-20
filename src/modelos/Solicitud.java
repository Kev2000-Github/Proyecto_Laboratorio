package modelos;

public class Solicitud {

    //atributos
    private enum prioridad { alta, media, baja };
    private enum tipoAyuda { medica, donacion };
    private enum estado { aprobado, negado, pendiente };

    private String fundacionDestino;

    private Servicio servicio;
    private Beneficiario beneficiario;
    private Empleado empleado;

    //Constructores
    public Solicitud(){};

    void Solicitud(String fundaciondestino){

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



}
