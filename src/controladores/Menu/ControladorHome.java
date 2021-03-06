package controladores.Menu;

import java.awt.event.ActionEvent;
import java.time.Year;
import java.util.List;

import DAO.FundacionDao;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.IRouter;
import modelos.Fundacion;
import utils.Utils;
import vistas.swing.VentanaHome;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorHome extends ControladorGeneral {
    VentanaHome window;

    public ControladorHome(IRouter router) {
        super("home", router);
    }

    public void initGUI(){
        router.addRoute(this.id);
        window = new VentanaHome(this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    private void asignarPartidaAnual(float partidaTotal){
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundaciones = fundacionDao.getAll();
        float presupuesto = partidaTotal;
        for(Fundacion fundacion : fundaciones){
            if(presupuesto > 0){
                float porcentajePartido = fundacion.getPorcentajePartidoAnual();
                float fondosRestantes = fundacion.getPresupuesto();
                float partida = partidaTotal * porcentajePartido/100;
                float nuevoPresupuesto = fondosRestantes + partida;
                fundacion.setPresupuesto(nuevoPresupuesto);
                fundacionDao.updateFondos(fundacion, partida);
                presupuesto -= partida;
            }
        }

    }
    
    public void mostrarMensaje(String mensaje){
        window.mostrarMensaje(mensaje);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getBack_office()) {
            router.notify(this, "go-backOffice");
        }
        if (source == window.getGestionar_solicitud()) {
            router.notify(this, "go-solicitudes");
        }
        
        if (source == window.getCrear_solicitud()) {
            router.notify(this, "go-addSolicitud");
        }
        if(source == window.getAsignar_fondos()) {
            int currentYear = Year.now().getValue();
            FundacionDao fundacionDao = new FundacionDao();
            float partidaExistente = fundacionDao.getPartidaAnnio(currentYear);
            if(partidaExistente > 0){
                window.mostrarMensaje("Ya se ha hecho la asignacion de la partida de este annio");
                return;
            }
            String fondos = window.mostrarMensajeInput("Ingrese la partida total a asignar este annio");
            if(!Utils.isNumber(fondos)){
                window.mostrarMensaje("Por favor ingrese un numero valido");
                return;
            }
            if(Float.parseFloat(fondos) <= 0){
                window.mostrarMensaje("Por favor ingrese un numero mayor a 0");
                return;
            }
            asignarPartidaAnual(Float.parseFloat(fondos));
            window.mostrarMensaje("Los fondos fueron asignados con exito");
        }
    }

}
