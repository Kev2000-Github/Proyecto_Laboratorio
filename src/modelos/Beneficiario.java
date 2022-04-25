package modelos;

public class Beneficiario extends Persona {

	private String id;
	
	public Beneficiario(){}

	public Beneficiario(
		String nombre, 
		String apellido, 
		String cedula, 
		String direccion, 
		String telefono,
		String id)
	{
		super(nombre,apellido,cedula,direccion,telefono, id);
        this.id = id;
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	}
