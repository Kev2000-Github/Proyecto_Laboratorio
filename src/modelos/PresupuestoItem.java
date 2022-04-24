package modelos;


public class PresupuestoItem {
    private String servicioId;
    private float precio;

    public PresupuestoItem(){}

    public PresupuestoItem(String servicioId, float precio){
        this.precio = precio;
        this.servicioId = servicioId;
    }

    public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

    public String getServicioId() {
		return servicioId;
	}

	public void setServicioId(String servicioId) {
		this.servicioId = servicioId;
	}
}
