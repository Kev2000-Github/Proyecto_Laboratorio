package vistas.backOffice;

import modelos.Empleado;
import vistas.general.VentanaGeneralLista;

import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEmpleados extends VentanaGeneralLista<Empleado>{
	
    public VentanaEmpleados(ActionListener accion, List<Empleado> empleados){
        super(accion, empleados, "empleado");
    }

	public void fillListModel(){
		for(int i = 0; i < items.size(); i++){
			String id = items.get(i).getId();
			String nombre = items.get(i).getNombre();
			String apellido = items.get(i).getApellido();
			String item = id + " " + nombre + " " + apellido;
			listModel.addElement(item);
		}
	} 
	
}