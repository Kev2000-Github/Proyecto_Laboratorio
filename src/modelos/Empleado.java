package modelos;

public class Empleado extends Persona {

	private String id;
	
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
	
	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	}