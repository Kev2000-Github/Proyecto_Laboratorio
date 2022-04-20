package modelos;

public class Beneficiario extends Persona {
	
	public Beneficiario(){};
	
	public Beneficiario(String nombre, 
		String apellido, 
		String cedula, 
		String direccion, 
		String telefono,
		String codigo, 
		String codigoCargo)
	{
		super(nombre,apellido,cedula,direccion,telefono);
    }
}
