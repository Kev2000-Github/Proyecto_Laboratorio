package vistas.general;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import modelos.Empleado;
import modelos.Persona;
import vistas.VentanaHome;
import vistas.backOffice.VentanaAddPersona;
import vistas.backOffice.VentanaBeneficiarios;
import vistas.backOffice.VentanaEditEmpleado;
import vistas.backOffice.VentanaEditPersona;
import vistas.backOffice.VentanaEmpleados;

public class VentanaFactory {
    public VentanaGeneral getVentana(String code, ActionListener accion){
        if(code.equals("hom001")){
            return new VentanaHome(accion);
        }
        if(code.equals("per0R1")){
            return new VentanaAddPersona(accion);
        }
        return new VentanaGeneral();
    }

    public VentanaGeneral getVentanaList(String code, ActionListener accion, List<?> list){
        if(code.equals("ben001")){
            List<Persona> personas = (List<Persona>)(Object) list;
            return new VentanaBeneficiarios(accion, personas);
        }
        if(code.equals("emp001")){
            List<Empleado> empleados = (List<Empleado>)(Object) list;
            return new VentanaEmpleados(accion, empleados);
        }
        return new VentanaGeneral();
    }

    public VentanaEditGeneral<?> getVentanaEdit(
        String code, 
        ActionListener accion, 
        Object entity, 
        String entityName, 
        ArrayList<String> inmutableFields,
        ArrayList<String> mutableFields
        ){
    if(code.equals("per0E1")){
        return new VentanaEditPersona(accion, (Persona) entity, entityName, inmutableFields, mutableFields);
    }
    if(code.equals("emp0E1")){
        return new VentanaEditEmpleado(accion, (Empleado) entity, entityName, inmutableFields, mutableFields);
    }
    return new VentanaEditGeneral<Object>(accion, entity, entityName, inmutableFields, mutableFields);
}

}
