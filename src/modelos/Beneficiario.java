package modelos;

public class Beneficiario extends Persona {
	private String id;

	public Beneficiario(){};
	
	public Beneficiario(
		String nombre, 
		String apellido, 
		String cedula, 
		String direccion, 
		String telefono,
		String codigo, 
		String correo
		)
	{
		super(nombre,apellido,cedula,direccion,telefono,correo);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
