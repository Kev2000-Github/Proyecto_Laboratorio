package vistas.backOffice;

import modelos.Empleado;
import vistas.general.VentanaGeneralLista;

import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEmpleados extends VentanaGeneralLista<Empleado>{
	
    public VentanaEmpleados(ActionListener accion, List<Empleado> empleados){
        super(accion, empleados);
    }

	public String[] getItemList(){
		String[] empleadoList = new String[items.size()];
		for(int i = 0; i < empleadoList.length; i++){
			String id = items.get(i).getId();
			String nombre = items.get(i).getNombre();
			String apellido = items.get(i).getApellido();
			String item = id + " " + nombre + " " + apellido;
			empleadoList[i] = item;
		}
		return empleadoList;
	} 
	
}