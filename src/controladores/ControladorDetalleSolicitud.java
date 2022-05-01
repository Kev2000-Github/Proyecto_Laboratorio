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
import vistas.swing.VentanaDetallesSolicitud;
import vistas.swing.VentanaGestionarSolicitud;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
public class ControladorDetalleSolicitud extends ControladorGeneral {

    VentanaDetallesSolicitud window;
    SolicitudDao solicitudDao;
    ServicioDao servicioDao;

    public ControladorDetalleSolicitud(Usuario user, Map<String,String> solicitudInfo) {
        super(user);
        window = new VentanaDetallesSolicitud(this, this);
        window.setVisible(true);
        solicitudDao = new SolicitudDao();
        servicioDao = new ServicioDao();
        fillSolicitud(solicitudInfo);
    }

    public void fillSolicitud(Map<String,String> solicitudInfo) {
        String id = solicitudInfo.get("solicitudId");
        String fundacionName = solicitudInfo.get("fundacion");
        String beneficiarioName = solicitudInfo.get("beneficiario");
        String encargadoName = solicitudInfo.get("encargado");

        DefaultTableModel modelSolicitudes = new DefaultTableModel();
        ServicioDao servicioDao = new ServicioDao();
        List<Servicio> servicios = servicioDao.getAllFromSolicitud(id);
        modelSolicitudes.setColumnCount(2);
        modelSolicitudes.setColumnIdentifiers(new String[]{"Servicio", "Costo"});

        for (Servicio s : servicios) {
            modelSolicitudes.addRow(new Object[]{s.getNombre(), s.getCosto()});
        }

        window.setSolicitudInformation(id, fundacionName, beneficiarioName, encargadoName, modelSolicitudes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getSource().getClass().getName();
        if(source.equals("javax.swing.JButton")){
            JButton btn = (JButton) e.getSource();
            String name = btn.getName();
            if(name.equals("aprobar")){
                
            }
            if(name.equals("rechazar")){
                
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
            if(lbl.getName() == "goBack"){
                window.dispose();
                new ControladorGestionarSolicitudes(user);
            }
        }
    }
}
