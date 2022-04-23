package modelos;
import java.util.List;

public class Gobernacion {
	
	//atributos
	private List<Fundacion> fundaciones;
	private String id;
	private float fondos;
	private String nombre;
	
	//metodos de inicializacion - constructores
	public Gobernacion() {};
	
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

	public Gobernacion(List<Fundacion> fundaciones, float fondos) {
		
		this.fundaciones = fundaciones;
		this.fondos = fondos;
	};
	
	//metodos sets y gets
	public void setFundaciones(List<Fundacion> fundaciones) {
		
		this.fundaciones = fundaciones;
	}
	
	public List<Fundacion> getFundaciones() {return fundaciones; };
	
	public void setFondos(float fondos) {
		
		this.fondos = fondos;
	}
	
	public float getFondos() { return fondos; };
	
	//Metodos de procesamiento 
	//----Borrar si se harán en el controlador----//
	
	//public void asignarPresupuesto() {}
	
	//public List<Beneficiario> getBeneficiarios(){};
	
	//public List<Empleado> getEmpleados(){};
	
	//public List<Persona> getPersonas(){};


}
