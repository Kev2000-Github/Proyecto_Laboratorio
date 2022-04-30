package modelos;

public class FundacionServicio {

	private String servicio_id;
        private String fundacion_id;
	private String nombre;
	private String tipo;
        private Float costo;

    public String getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(String servicio_id) {
        this.servicio_id = servicio_id;
    }

    public String getFundacion_id() {
        return fundacion_id;
    }

    public void setFundacion_id(String fundacion_id) {
        this.fundacion_id = fundacion_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public FundacionServicio() {
    }
        
  

	

	@Override
    public String toString() {
        // TODO Auto-generated method stub
        return nombre;
    }
}
