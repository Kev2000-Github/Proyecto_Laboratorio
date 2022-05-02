/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import modelos.Beneficiario;
import modelos.Fundacion;
import modelos.Solicitud;
import modelos.Usuario;
import vistas.swing.VentanaDetalleSolicitante;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import DAO.BeneficiarioDao;
import DAO.FundacionDao;
import DAO.ServicioDao;
import DAO.SolicitudDao;
import javax.swing.DefaultComboBoxModel;
import modelos.Servicio;
import vistas.general.ComboboxItem;



/**
 *
 * @author prometheus
 */
public class ControladorListaSolicitante extends ControladorGeneral {
    VentanaDetalleSolicitante window;
    SolicitudDao solicitudDao;
    
    public ControladorListaSolicitante(Usuario user) {
        super(user);
        window = new VentanaDetalleSolicitante(this, this);
        window.setVisible(true);
        solicitudDao = new SolicitudDao();
        fillSolicitantes();
        fillFundacion();
        
    }


    public void fillSolicitantes() {
        DefaultTableModel modelSolicitantes = new DefaultTableModel();
        List<Solicitud> solicitudes = solicitudDao.getAllPending();  
        modelSolicitantes.setColumnCount(3);
        modelSolicitantes.setColumnIdentifiers(new String[]{"Solicitud", "Fundacion", "Beneficiario"});

        for (Solicitud s : solicitudes) {
            FundacionDao fundacionDao = new FundacionDao();
            Fundacion fundacion = fundacionDao.get(s.getFundacionId());
            BeneficiarioDao beneficiarioDao = new BeneficiarioDao();
            Beneficiario beneficiario = beneficiarioDao.get(s.getBeneficiarioId());
            modelSolicitantes.addRow(new Object[]{s.getId(), 
                fundacion.getNombre(), 
                beneficiario.getNombre()});
        }
        window.setModelSolicitantes(modelSolicitantes);
    }

    public void fillFundacion() {
        DefaultComboBoxModel modelFundacion = new DefaultComboBoxModel();
        FundacionDao fundacionDao = new FundacionDao();
        List<Fundacion> fundacionList = fundacionDao.getAll();
        for (Fundacion fund : fundacionList) {
            modelFundacion.addElement(
                    new ComboboxItem(fund.getId(), fund.getNombre()));
        }
        window.setModelFundacioncb(modelFundacion);
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
