package vistas.backOffice;

import modelos.Beneficiario;
import vistas.general.VentanaEditGeneral;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEditBeneficiario extends VentanaEditGeneral<Beneficiario>{

    public VentanaEditBeneficiario(
            ActionListener accion, 
            Beneficiario beneficiario, 
            String entity,
            ArrayList<String> inmutableFields,
            ArrayList<String> mutableFields
            ){
        super(accion, beneficiario, entity, inmutableFields, mutableFields);
    }

    public void setItemFields(Beneficiario beneficiario){
        this.item = beneficiario;
        //mock, ovewrite on child class
        inmutableInputs.forEach((key, input) -> {
            if(key.equals("id")){
                input.setValue(beneficiario.getId());
            }
            else if(key.equals("cedula")){
                input.setValue(beneficiario.getCedula());
            }
        });
        inputs.forEach((key, input) -> {
            if(key.equals("nombre")){
                input.setValue(beneficiario.getNombre());
            }
            else if(key.equals("apellido")){
                input.setValue(beneficiario.getApellido());
            }
            else if(key.equals("direccion")){
                input.setValue(beneficiario.getDireccion());
            }
            else if(key.equals("telefono")){
                input.setValue(beneficiario.getTelefono());
            }
        });
        setEnableFields(true);
    }

    public Beneficiario getItem() {
        //mock, ovewrite on child class
        inputs.forEach((key, input) -> {
            if(key.equals("nombre")){
                item.setNombre(input.getValue());
            }
            else if(key.equals("apellido")){
                item.setApellido(input.getValue());
            }
            else if(key.equals("direccion")){
                item.setDireccion(input.getValue());
            }
            else if(key.equals("telefono")){
                item.setTelefono(input.getValue());
            }
        });
        return item;
    }
}
