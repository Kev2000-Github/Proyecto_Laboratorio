package vistas.general;

import java.awt.event.ActionListener;
import java.util.List;

import modelos.Empleado;
import modelos.Persona;
import vistas.VentanaHome;
import vistas.backOffice.VentanaAddPersona;
import vistas.backOffice.VentanaBeneficiarios;
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

    public VentanaGeneral getVentanaEdit(String code, ActionListener accion, Object entity){
        if(code.equals("per0E1")){
            return new VentanaEditPersona(accion, (Persona) entity);
        }
        return new VentanaGeneral();
    }

}
