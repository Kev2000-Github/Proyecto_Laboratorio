package controladores.Solicitud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.Year;

import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Servicio;
import modelos.Solicitud;
import modelos.Usuario;
import utils.Utils;
import vistas.swing.VentanaDetallesSolicitud;
import vistas.swing.VentanaGestionarSolicitud;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import DAO.BeneficiarioDao;
import DAO.CharlaDao;
import DAO.EmpleadoDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.SolicitudDao;
import controladores.ControladorComponente.ControladorDetailsGeneral;
import controladores.ControladorComponente.ControladorGeneral;
import controladores.Mediator.Router;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorDetalleSolicitud extends ControladorDetailsGeneral {

    private VentanaDetallesSolicitud window;
    private ServicioDao servicioDao;
    private Map<String,String> solicitudInfo;
    private SolicitudDao solicitudDao;

    public ControladorDetalleSolicitud(Router route) {
        super("detallesolicitud", route);
        this.servicioDao = new ServicioDao();
    }

    public void updateMap(Map<String,String> map){
        this.solicitudInfo = map;
        this.solicitudDao = new SolicitudDao();
        fillSolicitud(this.solicitudInfo);
    }

    public void initGUI(){
        window = new VentanaDetallesSolicitud(this, this);
        window.setVisible(true);
    }

    public void closeGUI(){
        window.dispose();
    }

    public void fillSolicitud(Map<String,String> solicitudInfo) {
        String id = solicitudInfo.get("solicitudId");
        String fundacionName = solicitudInfo.get("fundacion");
        String beneficiarioName = solicitudInfo.get("beneficiario");
        String encargadoName = solicitudInfo.get("encargado");

        DefaultTableModel modelSolicitudes = new DefaultTableModel();
        List<Servicio> servicios = servicioDao.getAllFromSolicitud(id);
        modelSolicitudes.setColumnCount(2);
        modelSolicitudes.setColumnIdentifiers(new String[]{"Servicio", "Costo"});

        for (Servicio s : servicios) {
            modelSolicitudes.addRow(new Object[]{s.getNombre(), s.getCosto()});
        }

        window.setSolicitudInformation(id, fundacionName, beneficiarioName, encargadoName, modelSolicitudes);
    }

    public boolean checkConditions(){
        SolicitudDao solicitudDao = new SolicitudDao();
        BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
        String solicitudId = solicitudInfo.get("solicitudId");
        Solicitud solicitud = solicitudDao.get(solicitudId);
        Beneficiario beneficiario = beneficiarioDao.get(solicitud.getBeneficiarioId());

        LocalDate now = LocalDate.now();
        LocalDate from = now.minusMonths(6);
        Date nowDate = Utils.asDate(now);
        Date fromDate = Utils.asDate(from);

        int numSolicitudesPendientes = solicitudDao.countNumberSolicitudes(beneficiario.getId(), "pendiente", fromDate, nowDate);
        if(numSolicitudesPendientes > 1){
            window.mostrarMensaje("Este beneficiario tiene mas de una solicitud pendiente");
            return false;
        }
        int numSolicitudesAprobadas = solicitudDao.countNumberSolicitudes(beneficiario.getId(), "aprobado", fromDate, nowDate);
        if(numSolicitudesAprobadas > 0){
            window.mostrarMensaje("Este beneficiario ya ha recibido beneficios en los ultimos 6 meses");
            return false;
        }
        CharlaDao charlaDao = new CharlaDao();
        int asistencias = charlaDao.countCharlasAsistidas(beneficiario.getCedula());
        if(asistencias < 3){
            window.mostrarMensaje("Este beneficiario solo ha asistido a (" + Integer.toString(asistencias) + ") charlas");
            return false;
        }
        int currentYear = Year.now().getValue();
        FundacionDao fundacionDao = new FundacionDao();
        Fundacion fundacion = fundacionDao.getFromSolicitud(solicitudId);
        float partidaAsignada = fundacionDao.getPartidaAsignada(fundacion.getId(), currentYear);
        float totalGastado = fundacionDao.getTotalGastado(fundacion.getId(), currentYear);
        float partidaRestante = partidaAsignada - totalGastado;
        float partidaRestantePorcentual = partidaRestante*100/partidaAsignada;
        float costoTotal = window.getCosto();
        int currentMonth = now.getMonth().getValue();
        String prioridad = solicitudInfo.get("prioridad");
        Solicitud highestSolicitud = solicitudDao.getHighestPriority(solicitudId);
        String highestPriority = highestSolicitud.getPrioridad().toString();

        if(partidaRestantePorcentual < 25 && currentMonth < 10 && !prioridad.equals(highestPriority)){
            window.mostrarMensaje("Existen otras solicitudes con mayor prioridad, procesa esas primero");
            return false;
        }
        if(fundacion.getPresupuesto() < costoTotal){
            window.mostrarMensaje("No hay suficientes fondos para aprobar esto");
            return false;
        }
        return true;
    }

    private void goBack(){
        router.notify(this, "go-solicitudes");
    }

    private void aprobarSolicitud(){
        String solicitudId = solicitudInfo.get("solicitudId");
        solicitudDao.updateStatus(solicitudId, "aprobado");
        
        FundacionDao fundacionDao = new FundacionDao();
        Fundacion fundacion = fundacionDao.getFromSolicitud(solicitudId);
        float costo = window.getCosto();
        float updatedPresupuesto = fundacion.getPresupuesto() - costo;
        fundacion.setPresupuesto(updatedPresupuesto);
        fundacionDao.update(fundacion);
        
        window.mostrarMensaje("La solicitud fue aprobada con exito");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JButton")){
            JButton btn = (JButton) e.getSource();
            String name = btn.getName();
            if(name.equals("aprobar")){
                if(checkConditions()){
                    aprobarSolicitud();
                    goBack();
                }
            }
            else if(name.equals("rechazar")){
                String solicitudId = solicitudInfo.get("solicitudId");
                solicitudDao.updateStatus(solicitudId, "rechazado");
                window.mostrarMensaje("La solicitud fue rechazada con exito");
                goBack();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                router.notify(this, "go-home");
            }
            if(lbl.getName() == "goBack"){
                goBack();
            }
        }
    }
}
