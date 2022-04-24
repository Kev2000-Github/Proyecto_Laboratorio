package vistas.backOffice;

import modelos.Persona;
import vistas.general.VentanaGeneralLista;

import java.awt.event.ActionListener;
import java.util.List;

public class VentanaBeneficiarios extends VentanaGeneralLista<Persona>{
	
    public VentanaBeneficiarios(ActionListener accion, List<Persona> personas){
        super(accion, personas, "Beneficiario");
    }

	public void fillListModel(){
		for(int i = 0; i < items.size(); i++){
			String cedula = items.get(i).getCedula();
			String nombre = items.get(i).getNombre();
			String apellido = items.get(i).getApellido();
			String item = cedula + " " + nombre + " " + apellido;
			listModel.addElement(item);
		}
	}
	
}
