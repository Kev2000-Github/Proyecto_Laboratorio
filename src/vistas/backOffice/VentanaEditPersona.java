package vistas.backOffice;

import modelos.Persona;
import vistas.general.VentanaEditGeneral;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEditPersona extends VentanaEditGeneral<Persona>{

    public VentanaEditPersona(
            ActionListener accion, 
            Persona persona, 
            String entity,
            ArrayList<String> inmutableFields,
            ArrayList<String> mutableFields
            ){
        super(accion, persona, entity, inmutableFields, mutableFields);
    }

    public void setItemFields(Persona persona){
        this.item = persona;
        //mock, ovewrite on child class
        inmutableInputs.forEach((key, input) -> {
            if(key.equals("cedula")){
                input.setValue(persona.getCedula());
            }
        });        
        inputs.forEach((key, input) -> {
            if(key.equals("nombre")){
                input.setValue(persona.getNombre());
            }
            else if(key.equals("apellido")){
                input.setValue(persona.getApellido());
            }
            else if(key.equals("direccion")){
                input.setValue(persona.getDireccion());
            }
            else if(key.equals("telefono")){
                input.setValue(persona.getTelefono());
            }
            else if(key.equals("correo")){
                input.setValue(persona.getCorreo());
            }
        });
        setEnableFields(true);
    }

    public Persona getItem() {
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
            else if(key.equals("correo")){
                item.setCorreo(input.getValue());
            }
        });
        return item;
    }
}
