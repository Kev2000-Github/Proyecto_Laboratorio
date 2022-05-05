/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import javax.swing.table.DefaultTableModel;

import modelos.Usuario;
import utils.Constants;
import vistas.swing.VentanaListaPresupuestos;

import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import DAO.SolicitudDao;
import javax.swing.DefaultComboBoxModel;
import vistas.general.ComboboxItem;
import java.awt.event.ActionEvent;
;

/**
 *
 * @author prometheus
 */
public class ControladorListaPresupuesto extends ControladorGeneral {
    VentanaListaPresupuestos window;
    SolicitudDao solicitudDao;
    
    public ControladorListaPresupuesto(Usuario user) {
        super(user);
        window = new VentanaListaPresupuestos(this, this);
        window.setVisible(true);
        solicitudDao = new SolicitudDao();
        fillPresupuestos("aprobado");
        fillStatuses();
    }

    public void fillPresupuestos(String status) {
        DefaultTableModel modelPresupuestos = new DefaultTableModel();
        List<Map<String,String>> presupuestos = solicitudDao.getListaPresupuestos(status);
        String[] columnHeader = new String[]{"Solicitud", "Fundacion", "Encargado", "Beneficiario","presupuesto"};
        modelPresupuestos.setColumnCount(5);
        modelPresupuestos.setColumnIdentifiers(columnHeader);

        for (Map<String, String> presupuesto : presupuestos) {
            String solicitudId = presupuesto.get("solicitud_id");
            String fundacion = presupuesto.get("fundacion");
            String encargado = presupuesto.get("encargado");
            String beneficiario = presupuesto.get("beneficiario");
            String costoTotal = presupuesto.get("presupuesto_total");

            modelPresupuestos.addRow(new Object[]{
                solicitudId,
                fundacion,
                encargado,
                beneficiario,
                costoTotal
            });
        }
        window.setModelPresupuestos(modelPresupuestos);
    }

    public void fillStatuses() {
        DefaultComboBoxModel<ComboboxItem> modelStatuses = new DefaultComboBoxModel<ComboboxItem>();
        List<Constants.estadoEnum> statuses = List.of(Constants.estadoEnum.values());
        for (Constants.estadoEnum status : statuses) {
            modelStatuses.addElement(
                    new ComboboxItem(status.toString(), status.toString()));
        }
        window.setModelStatuses(modelStatuses);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        var source = arg0.getSource();

        if (source == window.getFundacion()) {
            String status = ((ComboboxItem) window.getFundacion().getSelectedItem()).getId();
            fillPresupuestos(status);
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
                new ControladorReportes(user);
            }
        }
    }
}
