package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import modelos.Beneficiario;
import modelos.Empleado;
import modelos.Fundacion;
import modelos.Servicio;
import modelos.Solicitud;
import modelos.Usuario;
import vistas.swing.VentanaGestionarSolicitud;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;

import DAO.BeneficiarioDao;
import DAO.EmpleadoDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.SolicitudDao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanperez
 */
public class ControladorGestionarSolicitudes extends ControladorGeneral {

    VentanaGestionarSolicitud window;
    SolicitudDao solicitudDao;
    ServicioDao servicioDao;

    public ControladorGestionarSolicitudes(Usuario user) {
        super(user);
        window = new VentanaGestionarSolicitud(this, this);
        window.setVisible(true);
        solicitudDao = new SolicitudDao();
        servicioDao = new ServicioDao();
        fillSolicitudes();
    }

    public void fillSolicitudes() {
        DefaultTableModel modelSolicitudes = new DefaultTableModel();
        List<Solicitud> solicitudes = solicitudDao.getAllPending();  
        modelSolicitudes.setColumnCount(5);
        modelSolicitudes.setColumnIdentifiers(new String[]{"Solicitud", "Fundacion", "Beneficiario", "Encargado", "Prioridad"});

        for (Solicitud s : solicitudes) {
            FundacionDao fundacionDao = new FundacionDao();
            Fundacion fundacion = fundacionDao.get(s.getFundacionId());
            BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
            Beneficiario beneficiario = beneficiarioDao.get(s.getBeneficiarioId());
            EmpleadoDao empleadoDao = new EmpleadoDao();
            Empleado empleado = empleadoDao.get(s.getEmpleadoId());
            modelSolicitudes.addRow(new Object[]{
                s.getId(), 
                fundacion.getNombre(), 
                beneficiario.getPersona().getNombre(), 
                empleado.getPersona().getNombre(),
                s.getPrioridad().toString()
            });
        }
        window.setModelSolicitudes(modelSolicitudes);
    }

    public Map<String, String> getSelectedSolicitud(){
        int row = window.getSolicitudes().getSelectedRow();
        Map<String, String> rowInfo = new HashMap<String, String>();
        String solicitudId = window.getSolicitudes().getValueAt(row, 0).toString();
        String fundacionName = window.getSolicitudes().getValueAt(row, 1).toString();
        String beneficiarioName = window.getSolicitudes().getValueAt(row, 2).toString();
        String encargadoName = window.getSolicitudes().getValueAt(row, 3).toString();
        String prioridad = window.getSolicitudes().getValueAt(row, 4).toString();
        rowInfo.put("solicitudId",solicitudId);
        rowInfo.put("fundacion",fundacionName);
        rowInfo.put("beneficiario",beneficiarioName);
        rowInfo.put("encargado",encargadoName);
        rowInfo.put("prioridad", prioridad);
        return rowInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JButton")){
            JButton btn = (JButton) e.getSource();
            String name = btn.getName();
            if(name.equals("verSolicitud")){
                if(window.getSolicitudes().getSelectedRow() != -1){
                    window.dispose();
                    Map<String, String> solicitudInfo = getSelectedSolicitud();
                    new ControladorDetalleSolicitud(user, solicitudInfo);
                }
                else{
                    window.mostrarMensaje("No se ha seleccionado ninguna solicitud");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JLabel")){
            JLabel lbl = (JLabel)e.getSource();
            if(lbl.getName() == "goHome"){
                window.dispose();
                new ControladorHome(user);
            }
        }
    }
}
