package modelos;
import java.util.List;

public class Gobernacion {
	
	//atributos
	private List<Fundacion> fundaciones;
	
	//metodos de inicializacion - constructores
	public Gobernacion() {};
	
	public Gobernacion(List<Fundacion> fundaciones) {
		
		this.fundaciones = fundaciones;
	};
	
	//metodos sets y gets
	public void setFundaciones(List<Fundacion> fundaciones) {
		
		this.fundaciones = fundaciones;
	}
	
	public List<Fundacion> getFundaciones() {return fundaciones; };
	
	//Metodos de procesamiento 
	//----Borrar si se harán en el controlador----//
	
	//public void asignarPresupuesto() {}
	
	//public List<Beneficiario> getBeneficiarios(){};
	
	//public List<Empleado> getEmpleados(){};
	
	//public List<Persona> getPersonas(){};


}
