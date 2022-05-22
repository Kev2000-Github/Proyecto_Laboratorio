package controladores;

import java.util.ArrayList;
import java.util.List;

import controladores.Charla.ControladorCharla;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;
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

public class ControladorPrincipal {
    Router router;
    private List<ControladorGeneral> controladores;

    public ControladorPrincipal(){
        router = new Router();
        initControladores();
        ControladorGeneral login = getControlador("login");
        login.initGUI();
    }

    private void initControladores(){
        controladores = new ArrayList<ControladorGeneral>();
        //LOGIN
        ControladorLogin login = new ControladorLogin(router);
        controladores.add(login);
        //CHARLA
        ControladorCharla charla = new ControladorCharla(router);
        controladores.add(charla);
        //MENUS
        ControladorHome home = new ControladorHome(router);
        ControladorBackOffice backoffice = new ControladorBackOffice(router);
        ControladorRegistros registros = new ControladorRegistros(router);
        ControladorReportes reportes = new ControladorReportes(router);
        controladores.add(home);
        controladores.add(backoffice);
        controladores.add(registros);
        controladores.add(reportes);
        //PERSONA
        ControladorBeneficiario beneficiario = new ControladorBeneficiario(router);
        ControladorAddBeneficiario addBeneficiario = new ControladorAddBeneficiario(router);
        ControladorUpdateBeneficiario updateBeneficiario = new ControladorUpdateBeneficiario(router);
        ControladorEmpleado empleado = new ControladorEmpleado(router);
        ControladorAddEmpleado addEmpleado = new ControladorAddEmpleado(router);
        ControladorUpdateEmpleado updateEmpleado = new ControladorUpdateEmpleado(router);
        controladores.add(beneficiario);
        controladores.add(empleado);
        controladores.add(addBeneficiario);
        controladores.add(addEmpleado);
        controladores.add(updateEmpleado);
        controladores.add(updateBeneficiario);
        //REPORTES
        ControladorListaPresupuesto listaPresupuestos = new ControladorListaPresupuesto(router);
        ControladorListaResponsable listaResponsables = new ControladorListaResponsable(router);
        ControladorListaSolicitante listaSolicitantes = new ControladorListaSolicitante(router);
        controladores.add(listaPresupuestos);
        controladores.add(listaResponsables);
        controladores.add(listaSolicitantes);
        //SOLICITUD
        ControladorGestionarSolicitudes solicitudes = new ControladorGestionarSolicitudes(router);
        ControladorAddSolicitud addSolicitud = new ControladorAddSolicitud(router);
        ControladorDetalleSolicitud detalleSolicitud = new ControladorDetalleSolicitud(router);
        controladores.add(solicitudes);
        controladores.add(addSolicitud);
        controladores.add(detalleSolicitud);

        router.setControladores(controladores);
    }

    private ControladorGeneral getControlador(String id){
        for(ControladorGeneral controlador : controladores){
            String contId = controlador.getId();
            if(id.equals(contId)){
                return controlador;
            }
        }
        return null;
    }

}
