package vistas.backOffice;

import modelos.Empleado;
import vistas.general.VentanaEditGeneral;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEditEmpleado extends VentanaEditGeneral<Empleado>{

    public VentanaEditEmpleado(
            ActionListener accion, 
            Empleado empleado, 
            String entity,
            ArrayList<String> inmutableFields,
            ArrayList<String> mutableFields
            ){
        super(accion, empleado, entity, inmutableFields, mutableFields);
    }

    public void setItemFields(Empleado empleado){
        this.item = empleado;
        //mock, ovewrite on child class
        inmutableInputs.forEach((key, input) -> {
            if(key.equals("id")){
                input.setValue(empleado.getId());
            }
            else if(key.equals("cedula")){
                input.setValue(empleado.getCedula());
            }
        });
        inputs.forEach((key, input) -> {
            if(key.equals("nombre")){
                input.setValue(empleado.getNombre());
            }
            else if(key.equals("apellido")){
                input.setValue(empleado.getApellido());
            }
            else if(key.equals("direccion")){
                input.setValue(empleado.getDireccion());
            }
            else if(key.equals("telefono")){
                input.setValue(empleado.getTelefono());
            }
        });
        setEnableFields(true);
    }

    public Empleado getItem() {
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
