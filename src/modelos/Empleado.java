package modelos;

public class Empleado extends Persona {

	private String codigo;
    private String codigoCargo;
	
	public Empleado(String nombre, 
		String apellido, 
		String cedula, 
		String direccion, 
		String telefono,
		String codigo, 
		String codigoCargo)
	{
		super(nombre,apellido,cedula,direccion,telefono);
        this.codigo = codigo;
        this.codigoCargo = codigoCargo;
    }
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoCargo() {
		return codigoCargo;
	}

	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}


	}