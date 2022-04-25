package vistas.backOffice;

import modelos.Beneficiario;
import vistas.general.VentanaGeneralLista;

import java.awt.event.ActionListener;
import java.util.List;

public class VentanaBeneficiarios extends VentanaGeneralLista<Beneficiario>{
	
    public VentanaBeneficiarios(ActionListener accion, List<Beneficiario> beneficiario){
        super(accion, beneficiario, "beneficiario");
    }

	public void fillListModel(){
		for(int i = 0; i < items.size(); i++){
			String id = items.get(i).getId();
			String cedula = items.get(i).getCedula();
			String nombre = items.get(i).getNombre();
			String apellido = items.get(i).getApellido();
			String item = id + " " + nombre + " " + apellido;
			listModel.addElement(item);
		}
	}
	
}
