package controladores.ControladorComponente;

import controladores.Mediator.Router;
import controladores.ControladorLogin;
import controladores.Charla.ControladorCharla;
import controladores.Menu.ControladorBackOffice;
import controladores.Menu.ControladorHome;
import controladores.Menu.ControladorRegistros;
import controladores.Menu.ControladorReportes;
import controladores.Persona.Beneficiario.ControladorAddBeneficiario;
import controladores.Persona.Beneficiario.ControladorBeneficiario;
import controladores.Persona.Beneficiario.ControladorUpdateBeneficiario;
import controladores.Persona.Empleado.ControladorAddEmpleado;
import controladores.Persona.Empleado.ControladorEmpleado;
import controladores.Persona.Empleado.ControladorUpdateEmpleado;
import controladores.Reportes.ControladorListaPresupuesto;
import controladores.Reportes.ControladorListaResponsable;
import controladores.Reportes.ControladorListaSolicitante;
import controladores.Solicitud.ControladorAddSolicitud;
import controladores.Solicitud.ControladorDetalleSolicitud;
import controladores.Solicitud.ControladorGestionarSolicitudes;

public class ControladorFactory {
    public static final String[] CONTROLADORES = {
        "login",
        "charla",
        "home",
        "backOffice",
        "registros",
        "reportes",
        "beneficiario",
        "addBeneficiario",
        "updateBeneficiario",
        "empleado",
        "addEmpleado",
        "updateEmpleado",
        "listaPresupuesto",
        "listaResponsable",
        "listaSolicitante",
        "gestionarSolicitudes",
        "addSolicitud",
        "detalleSolicitud"
    };

    public ControladorGeneral getControlador(String nombre, Router router) {
		switch(nombre){
            //LOGIN
            case "login":
                return new ControladorLogin(router);
            //CHARLA
            case "charla":
                return new ControladorCharla(router);
            //MENUS
            case "home":
                return new ControladorHome(router);
            case "backOffice":
                return new ControladorBackOffice(router);
            case "registros":
                return new ControladorRegistros(router);
            case "reportes":
                return new ControladorReportes(router);
            //PERSONA
            case "beneficiario":
                return new ControladorBeneficiario(router);
            case "addBeneficiario":
                return new ControladorAddBeneficiario(router);
            case "updateBeneficiario":
                return new ControladorUpdateBeneficiario(router);
            case "empleado":
                return new ControladorEmpleado(router);
            case "addEmpleado":
                return new ControladorAddEmpleado(router);
            case "updateEmpleado":
                return new ControladorUpdateEmpleado(router);
            //REPORTES
            case "listaPresupuesto":
                return new ControladorListaPresupuesto(router);
            case "listaResponsable":
                return new ControladorListaResponsable(router);
            case "listaSolicitante":
                return new ControladorListaSolicitante(router);
            //SOLICITUD
            case "gestionarSolicitudes":
                return new ControladorGestionarSolicitudes(router);
            case "addSolicitud":
                return new ControladorAddSolicitud(router);
            case "detalleSolicitud":
                return new ControladorDetalleSolicitud(router);
        }
		return null;
	}
}