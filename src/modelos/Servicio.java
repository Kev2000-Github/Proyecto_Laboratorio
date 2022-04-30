package modelos;

public class Servicio {

	private String id;
	private String nombre;
	private String tipo;

	public Servicio() {
	};

	public Servicio(String id, String nombre, String tipo) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}
        
        public Servicio(String id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
    public String toString() {
        // TODO Auto-generated method stub
        return nombre;
    }
}
