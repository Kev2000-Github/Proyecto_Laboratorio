package vistas.general;

import java.awt.event.ActionListener;
import java.util.List;

import modelos.Empleado;
import modelos.Persona;
import modelos.Usuario;
import vistas.VentanaHome;
import vistas.backOffice.VentanaBeneficiarios;
import vistas.backOffice.VentanaEmpleados;

public class VentanaFactory {
    public VentanaGeneral getVentana(String code, ActionListener accion){
        if(code.equals("hom001")) return new VentanaHome(accion);
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
}
