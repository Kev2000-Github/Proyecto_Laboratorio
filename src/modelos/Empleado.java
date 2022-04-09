package modelos;

public class Empleado extends Persona {

	private String id;

	public Empleado(){}

	public Empleado(
		String nombre, 
		String apellido, 
		String cedula, 
		String direccion, 
		String telefono,
		String id)
	{
		super(nombre,apellido,cedula,direccion,telefono);
        this.id = id;
    }
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	}